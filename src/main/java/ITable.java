import org.json.simple.JSONObject;

import java.util.List;

public interface ITable {
    void sortCountries();
    List<Country> getCountries();
    Country getCountryOnPosition(int position);
    void update(Match match);
    JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyStarted);
}
