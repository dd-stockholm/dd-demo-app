package cucumber.steps;

import java.util.ArrayList;
import java.util.List;
import org.fluentlenium.core.FluentAdapter;
import static org.openqa.selenium.By.cssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CapybaraDSL {
    
    
    
    static FluentAdapter browser = StepsUtils.getBrowser();
    static SearchContext searchContext;

    private static SearchContext getSearchContext() {
        if(searchContext == null) {
            searchContext = browser.getDriver();
        }

        return searchContext;
    }

    public interface ReturningCallback<T> {
        public T call() throws Exception;
    }

    public interface Callback {
        public void call() throws Exception;
    }

    public static void visit(String url) throws Exception {
        retryFor(5, () -> browser.getDriver().navigate().to(url));
    }

    public static void clickButton(String text) throws Exception {
        retryFor(5, () -> {
            List<WebElement> foundElements = new ArrayList();

            for(WebElement webElement : getSearchContext().findElements(cssSelector("button"))) {
                if(webElement.getText().contains(text)) {
                    foundElements.add(webElement);
                }
            }

            if(foundElements.size() == 1) {
                foundElements.get(0).click();
            } else if(foundElements.size() == 0) {
                throw new FindException("Could not find button with text:\"" + text + "\"");
            } else if(foundElements.size() > 0) {
                throw new FindException("Found more than one button with text:\"" + text + "\"");
            }
        });
    }

    public static void clickLink(String text) throws Exception {
        retryFor(5, () -> {
            List<WebElement> foundElements = new ArrayList();

            for(WebElement webElement : getSearchContext().findElements(cssSelector("a"))) {
                if(webElement.getText().contains(text) && webElement.isDisplayed()) {
                    foundElements.add(webElement);
                }
            }

            if(foundElements.size() == 0) {
                throw new FindException("Could not find link with text:\"" + text + "\"");
            } else if(foundElements.size() > 1) {
                throw new FindException("Found more than one link with text:\"" + text + "\"");
            }

            foundElements.get(0).click();
        });
    }

    private static <T> T retryFor(double secondsToRetry, ReturningCallback<T> callback) throws Exception {
        long startTime = System.currentTimeMillis();
        Exception exception = new Exception("Should never be thrown");
        while(startTime + (secondsToRetry * 1000) > System.currentTimeMillis()) {
            try {
                return callback.call();
            } catch (Exception ex) {
                exception = ex;
            }
            Thread.sleep(100);
        }

        throw exception;
    }

    private static void retryFor(double secondsToRetry, Callback callback) throws Exception {
        long startTime = System.currentTimeMillis();
        Exception exception = new Exception("Should never be thrown");
        while(startTime + (secondsToRetry * 1000) > System.currentTimeMillis()) {
            try {
                callback.call();
                return;
            } catch (Exception ex) {
                exception = ex;
            }
            Thread.sleep(100);
        }

        throw exception;
    }

    public static void within(String css, Callback callback) throws Exception {
        SearchContext currentSearchContext = getSearchContext();
        searchContext = retryFor(5, () -> { return currentSearchContext.findElement(cssSelector(css)); });
        System.out.println("Setting search context to css: \"" + css + "\"");
        callback.call();
        System.out.println("Un setting search context from css: \"" + css + "\"");
        searchContext = currentSearchContext;
    }

    public static class With {

        String text;

        public With(String text) {
            this.text = text;
        }
    }

    public static class WithText {
        String text;

        public WithText(String text) {
            this.text = text;
        }
    }

    public static With with(String text) {
        return new With(text);
    }

    public static void fillIn(String labelText, With with) throws Exception {
        String labelIsFor = find("label", withText(labelText)).getAttribute("for");
        find("#" + labelIsFor).sendKeys(with.text);
    }

    public static class FindException extends RuntimeException {
        public FindException(String message, Exception cause) {
            super(message, cause);
        }

        public FindException(String message) {
            super(message);
        }
    }

    public static WebElement find(String css, WithText withText) throws Exception {
        return retryFor(5, () -> {
            try {
                List<WebElement> foundElements = new ArrayList();

                for (WebElement webElement : getSearchContext().findElements(cssSelector(css))) {
                    if (webElement.getText().contains(withText.text)) {
                        foundElements.add(webElement);
                    }
                }

                if (foundElements.size() == 1) {
                    return foundElements.get(0);
                } else if (foundElements.size() > 0) {
                    throw new FindException("Found more than one css:\"" + css + "\" with text:\"" + withText.text + "\"");
                }
            } catch (NoSuchElementException ex) {
                throw new FindException("Could not find css:\"" + css + "\" with text:\"" + withText.text + "\"", ex);
            }

            throw new FindException("Could not find css:\"" + css + "\" with text:\"" + withText.text + "\"");
        });
    }

    public static WebElement find(String css) throws Exception {
        return retryFor(5, () -> {
            try {
                List<WebElement> foundElements = getSearchContext().findElements(cssSelector(css));

                if (foundElements.size() == 1) {
                    return foundElements.get(0);
                } else if (foundElements.size() > 0) {
                    throw new FindException("Found more than one css:\"" + css + "\"");
                }
            } catch (NoSuchElementException ex) {
                throw new FindException("Could not find css:\"" + css + "\"", ex);
            }

            throw new FindException("Could not find css:\"" + css + "\"");
        });
    }

    public static WithText withText(String text) {
        return new WithText(text);
    }
    
}
