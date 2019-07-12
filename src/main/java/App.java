import io.javalin.Javalin;
import io.javalin.websocket.WsSession;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class App {
    private static Map<WsSession, String> sessions = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .enableStaticFiles("/public")
                .start(7000);


        ICountry de = new Country("Deutschland"), gb = new Country("England"),
                es = new Country("Spanien"), br = new Country("Brasilien");
        Map<String, ICountry> countries = new HashMap<String, ICountry>() {{
            put("de", de);
            put("es", es);
            put("gb", gb);
            put("br", br);
        }};
        ITable table = new Table(new ICountry[]{de, gb, es, br});

        app.ws("/livematch", ws -> {
            ws.onConnect(session -> {
                sessions.put(session, "" + sessions.size());
                System.out.println("Connected: " + sessions.get(session));
            });

            ws.onMessage((session, message) -> {
                // "{\"country1\":\"de\",\"country2\":\"br\"}"

                // TODO JSON Mapper
                // Ja ich bin mir bewusst, dass diese Zeilen ganz schlechter Stil sind. Leider hat ein Versuch eines Json-Mappers nicht funktioniert
                String msg = message.replaceAll("(\\{)|(\\\\)|(})|(\")", "");
                String[] msgs = msg.split("[,:]");
                String country1 = msgs[1];
                String country2 = msgs[3];

                SimulatedLiveMatch simMatch = new SimulatedLiveMatch(countries.get(country1), countries.get(country2));
                simMatch.start();
                table.liveUpdate(simMatch, false);

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
            });
            ws.onClose((session, status, message) -> {
                System.out.println("Disconnected: " + sessions.get(session));
                sessions.remove(session);
            });
        });

        app.get("/start", ctx -> {
            ctx.result(table.toString());
        });

        app.get("/addgame", ctx -> {
            System.out.println(Arrays.toString(ctx.queryParamMap().values().toArray()));
            String country1 = (ctx.queryParam("country0"));
            String country2 = (ctx.queryParam("country1"));
            int goals1 = Integer.parseInt(ctx.queryParam("goals1"));
            int goals2 = Integer.parseInt(ctx.queryParam("goals2"));
            table.update(new Match(countries.get(country1), countries.get(country2), goals1, goals2));
            ctx.result(table.toString());
        });
    }

    private static void broadcastMessage(JSONObject message) {
        sessions.keySet().forEach(ses -> {
            ses.send(message.toJSONString());
        });
    }
}