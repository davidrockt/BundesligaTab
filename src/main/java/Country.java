// import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class Country implements Comparable<Country> {

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
        switch (points(goals, goalsAgainst)) {
            case 0:
                winLoose[1] += 1;
                break;
            case 1:
                winLoose[2] += 1;
                points++;
                break;
            case 3:
                winLoose[0] += 1;
                points += 3;
                break;
        }
    }

    public void liveUpdate(int oldGoals, int oldGoalsAgainst, int newGoals, int newGoalsAgainst, boolean alreadyStarted) {
        System.out.println("" + getName() + " hat jetzt ...");
        if(!alreadyStarted) {
            winLoose[2]++;
            points++;
            System.out.println("... 1 Punkt mehr ...");
            gamesPlayed++;
        }
        this.goals[0] += newGoals - oldGoals;
        System.out.println("...  " + (newGoals - oldGoals) + " Tor(e) mehr ...");
        this.goals[1] += newGoalsAgainst - oldGoalsAgainst;
        this.goals[2] += newGoals - oldGoals - newGoalsAgainst + oldGoalsAgainst;
        switch (points(newGoals, newGoalsAgainst) - points(oldGoals, oldGoalsAgainst)) {
            case 3:
                winLoose[0] += 1;
                winLoose[1] -= 1;
                points += 3;
                System.out.println("... 3 Punkte mehr ...");
                break;
            case 2:
                winLoose[0] += 1;
                winLoose[2] -= 1;
                points += 2;
                System.out.println("... 2 Punkte mehr ...");
                break;
            case 1:
                winLoose[2] += 1;
                winLoose[1] -= 1;
                points += 1;
                System.out.println("... 1 Punkt mehr ...");
                break;
            case -1:
                winLoose[1] += 1;
                winLoose[2] -= 1;
                points -= 1;
                System.out.println("... 1 Punkt weniger ...");
                break;
            case -2:
                winLoose[2] += 1;
                winLoose[0] -= 1;
                points -= 2;
                System.out.println("... 2 Punkte weniger ...");
                break;
            case -3:
                winLoose[1] += 1;
                winLoose[0] -= 1;
                points -= 3;
                System.out.println("... 3 Punkte weniger ...");
                break;
        }
        System.out.println(toString());
    }

    public int points(int goals, int goalsAgainst) {
        int result = 0;
        switch (Long.signum(goals - goalsAgainst)) {
            case 0:
                result++;
                break;
            case 1:
                result += 3;
                break;
        }
        return result;
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
    public int compareTo(@NotNull Country o) {
        if (o.points != points)
            return o.points - points;
        return o.goals[2] - goals[2];
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
