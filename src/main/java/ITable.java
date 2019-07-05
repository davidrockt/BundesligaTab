import java.util.List;

public interface ITable {
    void sortCountries();
    List<Country> getCountries();
    Country getCountryOnPosition(int position);
}
