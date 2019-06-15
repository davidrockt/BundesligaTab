import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Table {
    private Comparator<Country> comp = Comparator.comparingInt(Country::getPoints);
    private Set<Country> countries = new TreeSet<>(comp);
}
