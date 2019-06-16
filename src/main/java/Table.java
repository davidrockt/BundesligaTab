import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Table {
    private int size;
    private Comparator<Country> comp = Comparator.comparingInt(Country::getPoints);
    private Set<Country> countries = new TreeSet<>(comp);

    public Table(Country[] countryArr) {
        countries.addAll(Arrays.asList(countryArr));
    }

    @Override
    public String toString() {
        return "<thead>\n" +
                "        <tr>\n" +
                "            <th></th> <th>Land</th> <th>Spiele</th> <th>Gewonnen</th> <th>Tore</th> <th>Punkte</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tfoot>\n" +
                "        <tr>\n" +
                "            <td colspan=\"6\"><div>&nbsp;</div>\n" +
                "        </tr>\n" +
                "    </tfoot>\n" +
                "    <tbody>\n" +
                "        <tr>\n" +
                String.format("<td>1</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td>\n") +
                "        </tr>\n" +
                "        <tr class=\"alt\">\n" +
                "            <td>2</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>3</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td>\n" +
                "        </tr>\n" +
                "        <tr class=\"alt\">\n" +
                "            <td>4</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td> <td>data</td>\n" +
                "        </tr>\n" +
                "    </tbody>";
    }
}
