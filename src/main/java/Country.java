import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class Country implements ICountry, Comparable {

    private String name;
    private int gamesPlayed = 0;
    private int[] winLoose = {0, 0, 0};
    private int[] goals = {0, 0, 0};
    private int points = 0;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int[] getWinLoose() {
        return winLoose;
    }

    public int[] getGoals() {
        return goals;
    }

    public int getPoints() {
        return points;
    }


    public void update(int goals, int goalsAgainst) {
        System.out.println("Country = " + name);
        System.out.println("This just in: goals = " + goals + " - " + goalsAgainst);
        this.goals[0] += goals;
        this.goals[1] += goalsAgainst;
        this.goals[2] += goals - goalsAgainst;
        gamesPlayed++;
        switch (Long.signum(goals - goalsAgainst)) {
            case 0:
                winLoose[2] += 1;
                points++;
                getGoals().equals(new int[]{0, 0, 0});
                break;
            case -1:
                winLoose[1] += 1;
                break;
            case 1:
                winLoose[0] += 1;
                points += 3;
                break;
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", gamesPlayed=" + gamesPlayed +
                ", winLoose=" + Arrays.toString(winLoose) +
                ", goals=" + Arrays.toString(goals) +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(@NotNull Object o) {
        if (((Country) o).points != points)
            return ((Country) o).points - points;
        return ((Country) o).goals[2] - goals[2];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
