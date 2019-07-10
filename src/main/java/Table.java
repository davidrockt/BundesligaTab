import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Table implements ITable{
    private Map<String, Country> countries;
    private ArrayList<Country> countryList;

    public Table(Map<String, Country> countries) {
        this.countries = countries;
        countryList = (ArrayList<Country>) countries.values();
        System.out.println("countries = " + countries);
    }

    public int getSize() {
        return countries.size();
    }

    @Override
    public void sortCountries() {
        // TODO
        // countries.values().toArray();
    }

    @Override
    public Map<String, Country> getCountries() {
        return countries;
    }

    public Country getCountry(Country c) {
        return countryList.get(countryList.indexOf(c));
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
        for (Country country : countries.values()) {
            str.append("<tr>\n").append(String.format("<td>%d</td> <td>%s</td> <td>%d</td> <td>%d</td> <td>%d</td> <td>%d</td>\n",
                    i, country.getName(), country.getGamesPlayed(), country.getWinLoose()[0], country.getGoals()[0], country.getPoints())).append("</tr>\n");
        }
        str.append("    </tbody>");

        return str.toString();
    }
}
