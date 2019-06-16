import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().enableStaticFiles("/public").start(7000);
        Country[] countries = {new Country("Deutschland"), new Country("England"),
                                new Country("Spanien"), new Country("Brasilien")};
        Table table = new Table(countries);

        app.get("/start", ctx -> {
            ctx.result(table.toString());
        });
    }
}