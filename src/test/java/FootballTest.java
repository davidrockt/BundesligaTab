import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public final class FootballTest {

    @Test
    public void test_no_game() {
        ICountry de = new Country("Deutschland");
        assertEquals(0, de.getGamesPlayed());
        assertEquals(0, de.getWinLooseTie().getWin());
        assertEquals(0, de.getWinLooseTie().getLoose());
        assertEquals(0, de.getWinLooseTie().getTie());
        assertEquals(0, de.getGoals().getGoals());
        assertEquals(0, de.getGoals().getGoalsAgainst());
        assertEquals(0, de.getGoals().getGoalDiff());
        assertEquals(0, de.getPoints());
    }

    @Test
    public void test_one_country() {
        ICountry de = new Country("Deutschland");
        // TODO Land kann nicht gegen sich selbst spielen
        de.update(1, 1);
        assertEquals(1, de.getGamesPlayed());
        assertEquals(0, de.getWinLooseTie().getWin());
        assertEquals(0, de.getWinLooseTie().getLoose());
        assertEquals(1, de.getWinLooseTie().getTie());
        assertEquals(1, de.getGoals().getGoals());
        assertEquals(1, de.getGoals().getGoalsAgainst());
        assertEquals(0, de.getGoals().getGoalDiff());
        assertEquals(1, de.getPoints());

        de.update(3, 0);
        assertEquals(2, de.getGamesPlayed());
        assertEquals(1, de.getWinLooseTie().getWin());
        assertEquals(0, de.getWinLooseTie().getLoose());
        assertEquals(1, de.getWinLooseTie().getTie());
        assertEquals(4, de.getGoals().getGoals());
        assertEquals(1, de.getGoals().getGoalsAgainst());
        assertEquals(3, de.getGoals().getGoalDiff());
        assertEquals(4, de.getPoints());

        de.update(2, 3);
        assertEquals(3, de.getGamesPlayed());
        assertEquals(1, de.getWinLooseTie().getWin());
        assertEquals(1, de.getWinLooseTie().getLoose());
        assertEquals(1, de.getWinLooseTie().getTie());
        assertEquals(6, de.getGoals().getGoals());
        assertEquals(4, de.getGoals().getGoalsAgainst());
        assertEquals(2, de.getGoals().getGoalDiff());
        assertEquals(4, de.getPoints());
    }

    @Test
    public void test_table_with_two() {
        ICountry de = new Country("Deutschland");
        ICountry es = new Country("Spanien");
        // TODO Besser: über table.add() Länder hinzufügen
        Map<String, ICountry> countries = new HashMap<String, ICountry>() {{
            put("de", de);
            put("es", es);
        }};
        ITable table = new Table(countries);
        table.sortCountries();
        assertEquals(de, table.getCountryOnPosition(1));
        assertEquals(es, table.getCountryOnPosition(2));

        table.update(new Match(es, de, 2, 1));
        table.sortCountries();
        assertEquals(es, table.getCountryOnPosition(1));
        assertEquals(de, table.getCountryOnPosition(2));

        table.update(new Match(es, de, 0, 1));
        table.sortCountries();
        assertEquals(de, table.getCountryOnPosition(1));
        assertEquals(es, table.getCountryOnPosition(2));

        table.update(new Match(es, de, 1, 1));
        table.sortCountries();
        assertEquals(de, table.getCountryOnPosition(1));
        assertEquals(es, table.getCountryOnPosition(2));

        assertEquals(3, es.getGamesPlayed());
        assertEquals(1, es.getWinLooseTie().getWin());
        assertEquals(1, es.getWinLooseTie().getLoose());
        assertEquals(1, es.getWinLooseTie().getTie());
        assertEquals(3, es.getGoals().getGoals());
        assertEquals(3, es.getGoals().getGoalsAgainst());
        assertEquals(0, es.getGoals().getGoalDiff());
        assertEquals(4, es.getPoints());

        assertEquals(3, de.getGamesPlayed());
        assertEquals(1, de.getWinLooseTie().getWin());
        assertEquals(1, de.getWinLooseTie().getLoose());
        assertEquals(1, de.getWinLooseTie().getTie());
        assertEquals(3, de.getGoals().getGoals());
        assertEquals(3, de.getGoals().getGoalsAgainst());
        assertEquals(0, de.getGoals().getGoalDiff());
        assertEquals(4, de.getPoints());
    }

    @Test
    public void test_livematch() {
        // TODO
        ICountry de = new Country("Deutschland");
        ICountry es = new Country("Spanien");
        Map<String, ICountry> countries = new HashMap<String, ICountry>() {{
            put("de", de);
            put("es", es);
        }};
        ITable table = new Table(countries);
        SimulatedLiveMatch simMatch = new SimulatedLiveMatch(de, es);
        simMatch.start();
        table.liveUpdate(simMatch, false);
        // Da das Spiel am Anfang 0:0 steht, müssen ohne zeitliche Verzögerung beide Mannschaften 1 Punkt haben
        table.sortCountries();
        assertEquals(1, de.getWinLooseTie().getTie());
        assertEquals(1, es.getWinLooseTie().getTie());
        assertEquals(1, de.getPoints());
        assertEquals(1, es.getPoints());
    }
}
