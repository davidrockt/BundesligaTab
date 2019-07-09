import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class FootballTest {

    @Test
    public void test_match() {
        Country de = new Country("Deutschland");
        assertEquals(0, de.getGamesPlayed());
    }
}
