import org.json.simple.JSONObject;

import java.util.Map;

public interface ITable {
    void sortCountries();
    Map<String, Country> getCountries();
    Country getCountryOnPosition(int position);
    void update(Match match);
    JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyStarted);
}