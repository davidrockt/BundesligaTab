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
        return "<thead><tr><th></th><th>Spiele</th><th>Gewonnen</th><th>Tore</th><th>Punkte</th></tr></thead>\n" +
                "    <tfoot><tr><td colspan=\"5\"><div id=\"no-paging\">&nbsp;</div></tr></tfoot>\n" +
                "    <tbody><tr><td>1</td><td>data</td><td>data</td><td>data</td><td></td></tr>\n" +
                "    <tr class=\"alt\"><td>2</td><td>data</td><td>data</td><td></td><td></td></tr>\n" +
                "    <tr><td>3</td><td>data</td><td>data</td><td>data</td><td></td></tr>\n" +
                "    <tr class=\"alt\"><td>4</td><td>data</td><td>data</td><td>data</td><td></td></tr>\n" +
                "    </tbody>";
    }
}
