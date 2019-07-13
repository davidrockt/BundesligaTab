import org.json.simple.JSONObject;

import java.util.List;

public interface ITable {
    /**
     *
     * Sortiert die Länder nach ihrer gottgegebenen Ordnung
     */
    void sortCountries();
    List<ICountry> getCountries();
    List<ICountry> addCountry(ICountry country);
    ICountry getCountryOnPosition(int position);

    // TODO evtll Methode getPositionof(Country);

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
     * @param alreadyUpdated
     * @return JSONObject mit dem aktuellen Spielstand
     */
    JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyUpdated);
}