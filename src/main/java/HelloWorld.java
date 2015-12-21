import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        externalStaticFileLocation("./angularjs/app/");
        get("/hello", (req, res) -> "Hello World");
    }
}