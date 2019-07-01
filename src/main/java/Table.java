import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Table {
    private int size;
    private List<Country> countries = new ArrayList<>();

    public Table(Map<String, Country> countryArr) {
        countries.addAll(countryArr.values());
        System.out.println("countries = " + countries);
    }

    @Override
    public String toString() {
        Collections.sort(countries);
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
        for(Country country: countries) {
            str.append("<tr>\n").append(String.format("<td>%d</td> <td>%s</td> <td>%d</td> <td>%d</td> <td>%d</td> <td>%d</td>\n",
                    i, country.getName(), country.getGamesPlayed(), country.getWinLoose()[0], country.getGoals()[0], country.getPoints())).append("</tr>\n");
        }
        str.append("    </tbody>");

        return str.toString();
    }
}
