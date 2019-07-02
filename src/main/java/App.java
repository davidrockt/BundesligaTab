import io.javalin.Javalin;
import io.javalin.websocket.WsSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class App {
    private static Map<WsSession, String> sessions = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .enableStaticFiles("/public")
                .start(7000);


        Country de = new Country("Deutschland"), gb = new Country("England"),
                es = new Country("Spanien"), br = new Country("Brasilien");
        Map<String, Country> countries = new HashMap<String, Country>() {{
            put("de", de);
            put("es", es);
            put("gb", gb);
            put("br", br);
        }};
        Table table = new Table(countries);
        Match match = new Match(de, br, 7, 1);
        Match match2 = new Match(gb, es, 3, 2);

        app.ws("/livematch", ws -> {
            ws.onConnect(session -> {

                sessions.put(session, "" + sessions.size());
                System.out.println("Connected: " + sessions.get(session));
            });

            ws.onMessage((session, message) -> {
                System.out.println("Hello");
                System.out.println("message = " + message);
                // "{\"country1\":\"de\",\"country2\":\"br\",\"goals1\":\"2\",\"goals2\":\"1\"}"

                String msg = message.replaceAll("(\\{)|(\\\\)|(})|(\")", "");
                System.out.println("msg = " + msg);
                String[] msgs = msg.split("[,:]");
                Arrays.stream(msgs).forEach(System.out::println);

                String country1 = msgs[1];
                String country2 = msgs[3];
                int goals1 = Integer.parseInt(msgs[5]);
                int goals2 = Integer.parseInt(msgs[7]);
                Match newMatch = new Match(countries.get(country1), countries.get(country2), goals1, goals2);
                System.out.println("table.toString() = " + table.toString());
                broadcastMessage(table.toString());
            });
            ws.onClose((session, status, message) -> {
                System.out.println("Disconnected: " + sessions.get(session));
                sessions.remove(session);
            });
        });

        app.get("/start", ctx -> ctx.result(table.toString()));

        app.get("/addgame", ctx -> {
            System.out.println(Arrays.toString(ctx.queryParamMap().values().toArray()));
            String country1 = (Objects.requireNonNull(ctx.queryParam("country1")));
            String country2 = (Objects.requireNonNull(ctx.queryParam("country2")));
            int goals1 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals1")));
            int goals2 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals2")));
            //Match match3 = new Match(countries.get(country1), countries.get(country2), goals1, goals2);
            ctx.result(table.toString());
        });
    }

    private static void broadcastMessage(String message) {
        sessions.keySet().forEach(ses -> {
            System.out.println("sessions.get(ses) = " + sessions.get(ses));
            ses.send(message);
        });
    }
}