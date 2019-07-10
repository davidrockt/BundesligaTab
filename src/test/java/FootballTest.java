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
    public void test_one_game_at_a_time() {
        ICountry de = new Country("Deutschland");
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
    public void test_table() {
        ICountry de = new Country("Deutschland");
        ICountry es = new Country("Spanien");
        // TODO Besser: über table.add() Länder hinzufügen
        Map<String, ICountry> countries = new HashMap<String, ICountry>() {{
            put("de", de);
            put("es", es);
        }};
        // ITable table = new Table(countries);
    }
}
