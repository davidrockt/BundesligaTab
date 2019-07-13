import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Table implements ITable{
    private List<ICountry> countryList;

    public Table(ICountry[] countries) {
        countryList = new ArrayList<>(Arrays.asList(countries));
    }

    public int getSize() {
        return countryList.size();
    }

    @Override
    public void sortCountries() {
        Collections.sort(countryList);
    }

    @Override
    public List<ICountry> getCountries() {
        return countryList;
    }

    @Override
    public List<ICountry> addCountry(ICountry country) {
        countryList.add(country);
        return countryList;
    }

    @Override
    public ICountry getCountryOnPosition(int position) {
        ICountry[] arr = new ICountry[countryList.size()];
        int i = 0;
        for(ICountry c: countryList) {
            arr[i++] = c;
        }
        return arr[position - 1];
    }

    @Override
    public void update(Match match) {
        if(!countryList.contains(match.getTeam1()) || !countryList.contains(match.getTeam2()))
            throw new IllegalArgumentException("Land ist nicht in der Tabelle enthalten");
        match.update();
    }

    @Override
    public JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyUpdated) {
        if(!countryList.contains(simMatch.getCountry0()) || !countryList.contains(simMatch.getCountry1()))
            throw new IllegalArgumentException("Land ist nicht in der Tabelle enthalten");

        simMatch.liveUpdate(alreadyUpdated);
        JSONObject json = new JSONObject();
        json.put("goals1", simMatch.getGoals0());
        json.put("goals2", simMatch.getGoals1());
        json.put("isfinished", simMatch.isFinished());
        return json;
    }



    @Override
    public String toString() {
        sortCountries();
        // TODO Pebble
        StringBuilder str = new StringBuilder("<thead>\n" +
                "        <tr>\n" +
                "            <th></th> <th>Land</th> <th>Spiele</th> <th>Tore</th> <th>Diff.</th> <th>Siege (U, N)</th> <th>Punkte</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tfoot>\n" +
                "        <tr>\n" +
                "            <td colspan=\"7\"><div>&nbsp;</div>\n" +
                "        </tr>\n" +
                "    </tfoot>\n" +
                "    <tbody>\n");
        int i = 1;
        for (ICountry c : countryList) {
            str.append("<tr>\n").append(String.format("<td>%d</td> <td>%s</td> <td>%d</td> <td>%d:%d</td> <td>%d</td> <td>%d (%d, %d)</td> <td>%d</td>\n",
                    i++, c.getName(), c.getGamesPlayed(), c.getGoals().getGoals(), c.getGoals().getGoalsAgainst(), c.getGoals().getGoalDiff(),
                    c.getWinLooseTie().getWin(),
                    c.getWinLooseTie().getTie(), c.getWinLooseTie().getLoose(), c.getPoints())).append("</tr>\n");
        }
        str.append("    </tbody>");

        return str.toString();
    }
}
