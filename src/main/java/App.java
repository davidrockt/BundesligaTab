import io.javalin.Javalin;
import io.javalin.websocket.WsSession;
import org.json.simple.JSONObject;

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
        Map<String, ICountry> countries = new HashMap<String, ICountry>() {{
            put("de", de);
            put("es", es);
            put("gb", gb);
            put("br", br);
        }};
        ITable table = new Table(countries);

        app.ws("/livematch", ws -> {
            ws.onConnect(session -> {
                sessions.put(session, "" + sessions.size());
                System.out.println("Connected: " + sessions.get(session));
            });

            ws.onMessage((session, message) -> {
                System.out.println("message = " + message);
                // "{\"country1\":\"de\",\"country2\":\"br\"}"

                // TODO JSON Mapper
                // JA ICH WEIß, DAS IST GANZ SCHLECHTER STIL WAS ICH HIER IN DEN NÄCHSTEN ZEILEN MACHE :-( ZEITMANGEL....
                String msg = message.replaceAll("(\\{)|(\\\\)|(})|(\")", "");
                System.out.println("msg = " + msg);
                String[] msgs = msg.split("[,:]");
                Arrays.stream(msgs).forEach(System.out::println);

                String country1 = msgs[1];
                String country2 = msgs[3];

                SimulatedLiveMatch simMatch = new SimulatedLiveMatch(countries.get(country1), countries.get(country2));
                simMatch.start();
                table.liveUpdate(simMatch, false);
//17
                for(int i = 0; !simMatch.isFinished() && i < 50; i++) {
                    try {
                        Thread.sleep(2000);
                        JSONObject json = new JSONObject();
                        json.put("livematch", table.liveUpdate(simMatch, true));
                        json.put("table", table.toString());
                        broadcastMessage(json);
                    }
                    catch(InterruptedException e) {
                        System.out.println("e.getStackTrace() = " + Arrays.toString(e.getStackTrace()));
                    }
                }


                // System.out.println("table.toString() = " + table.toString());
            });
            ws.onClose((session, status, message) -> {
                System.out.println("Disconnected: " + sessions.get(session));
                sessions.remove(session);
            });
        });

        app.get("/start", ctx -> ctx.result(table.toString()));

        app.get("/addgame", ctx -> {
            System.out.println(Arrays.toString(ctx.queryParamMap().values().toArray()));
            String country1 = (Objects.requireNonNull(ctx.queryParam("country0")));
            String country2 = (Objects.requireNonNull(ctx.queryParam("country1")));
            int goals1 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals1")));
            int goals2 = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("goals2")));
            Match match3 = new Match(countries.get(country1), countries.get(country2), goals1, goals2);
            match3.update();
            ctx.result(table.toString());
        });
    }

    private static void broadcastMessage(JSONObject message) {
        sessions.keySet().forEach(ses -> {
            System.out.println("sessions.get(ses) = " + sessions.get(ses));
            ses.send(message.toJSONString());
        });
    }
}