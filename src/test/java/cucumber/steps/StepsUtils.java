package cucumber.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.dd.demoapp.App;
import org.fluentlenium.core.FluentAdapter;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StepsUtils {
    
    static FluentAdapter fluentBrowser;
    static FirefoxDriver driver;
    
    public synchronized static FluentAdapter getBrowser() {
        if(fluentBrowser == null) {
            driver = new FirefoxDriver();
            fluentBrowser = new FluentAdapter(driver);
            
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    cleanup();
                }
            });
        }
        
        return fluentBrowser;
    }
    
    public synchronized static void cleanup() {
        if(driver != null) {
            try {
                driver.quit();
                driver = null;
                fluentBrowser = null;
                Thread.sleep(10 * 1000);
            } catch (org.openqa.selenium.remote.UnreachableBrowserException ex) {
                // This is expected
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static App app;
    
    @Before
    public void startServer() throws Exception {
        if(app == null) {
            app = new App();
            app.run("server", "./app-test-config.yml");
        }
    }
    
    @After
    public void cucumberCleanup() {
        synchronized(StepsUtils.class) {
            if(fluentBrowser != null) {
                fluentBrowser.goTo("about:");
                fluentBrowser.getDriver().manage().deleteAllCookies();
            }
        }
    }
    
}
