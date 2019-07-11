import org.json.simple.JSONObject;

import java.util.Map;

public interface ITable {
    /**
     * TODO sortCountries()
     * Sortiert die Länder nach ihrer gottgegebenen Ordnung :)
     */
    void sortCountries();
    Map<String, ICountry> getCountries();
    ICountry getCountryOnPosition(int position);

    // TODO Methode getPositionof(Country);

    /**
     * Gibt den beteiligten Teams ein Update ihrer Statistik-Werte
     *
     * @param match Die Instanz eines fertigen Matches
     */
    void update(Match match);

    /**
     * Gibt den beteiligten Teams ein Live-Update ihrer Statistik-Werte während eines laufenden Spiels
     *
     * @param simMatch
     * @param alreadyStarted
     * @return JSONObject mit dem aktuellen Spielstand
     */
    JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyStarted);
}