import io.javalin.Javalin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().enableStaticFiles("/public").start(7000);
        Country de = new Country("Deutschland"),gb = new Country("England"),
                es = new Country("Spanien"), br = new Country("Brasilien");
        Map<String, Country> countries = new HashMap<String, Country>(){{
            put("de", de);
            put("es", es);
            put("gb", gb);
            put("br", br);
        }};
        Table table = new Table(countries);
        Match match = new Match(de, br, 7, 1);
        Match match2 = new Match(gb, es, 3, 2);

        app.get("/start", ctx -> ctx.result(table.toString()));

        app.get("/addgame", ctx -> {
            System.out.println(Arrays.toString(ctx.queryParamMap().values().toArray()));
            String country1 = (Objects.requireNonNull(ctx.queryParam("country1")));
            String country2 = (Objects.requireNonNull(ctx.queryParam("country2")));
            int goals1 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals1")));
            int goals2 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals2")));
            Match match3 = new Match(countries.get(country1), countries.get(country2), goals1, goals2);
            ctx.result(table.toString());
        });
    }
}