import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Table implements ITable{
    private Map<String, ICountry> countries;
    private List<ICountry> countryList;

    public Table(Map<String, ICountry> countries) {
        this.countries = countries;
        countryList = new ArrayList<>(countries.values());
        System.out.println("countries = " + countries);
    }

    public int getSize() {
        return countries.size();
    }

    @Override
    public void sortCountries() {
        // TODO
        Collections.sort(countryList);
    }

    @Override
    public Map<String, ICountry> getCountries() {
        return countries;
    }

    @Override
    public Country getCountryOnPosition(int position) {
        return countries.values().toArray(new Country[0])[position];
    }

    @Override
    public void update(Match match) {
        if(!countries.values().contains(match.getTeam1()) || !countries.values().contains(match.getTeam2()))
            throw new IllegalArgumentException("Land ist nicht in der Tabelle enthalten");
        match.update();
    }

    @Override
    public JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyStarted) {
        if(!countries.values().contains(simMatch.getCountry0()) || !countries.values().contains(simMatch.getCountry1()))
            throw new IllegalArgumentException("Land ist nicht in der Tabelle enthalten");

        simMatch.liveUpdate(alreadyStarted);
        JSONObject json = new JSONObject();
        json.put("goals1", simMatch.getGoals0());
        json.put("goals2", simMatch.getGoals1());
        return json;
    }



    @Override
    public String toString() {
        sortCountries();
        // TODO Pebble
        StringBuilder str = new StringBuilder("<thead>\n" +
                "        <tr>\n" +
                "            <th></th> <th>Land</th> <th>Spiele</th> <th>Gewonnen</th> <th>Tore</th> <th>Punkte</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tfoot>\n" +
                "        <tr>\n" +
                "            <td colspan=\"6\"><div>&nbsp;</div>\n" +
                "        </tr>\n" +
                "    </tfoot>\n" +
                "    <tbody>\n");
        int i = 1;
        for (ICountry country : countries.values()) {
            str.append("<tr>\n").append(String.format("<td>%d</td> <td>%s</td> <td>%d</td> <td>%d</td> <td>%d</td> <td>%d</td>\n",
                    i, country.getName(), country.getGamesPlayed(), country.getWinLooseTie().getWin(), country.getGoals().getGoals(), country.getPoints())).append("</tr>\n");
        }
        str.append("    </tbody>");

        return str.toString();
    }
}
