import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().enableStaticFiles("/public").start(7000);
        Country de = new Country("Deutschland"),gb = new Country("England"),
                es = new Country("Spanien"), br = new Country("Brasilien");
        Country[] countries = {de, gb, es, br};
        Table table = new Table(countries);
        Match match = new Match(de, br, 7, 1);
        Match match2 = new Match(gb, es, 3, 2);

        app.get("/start", ctx -> {
            ctx.result(table.toString());
        });
    }
}