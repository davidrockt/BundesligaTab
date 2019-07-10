// import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Country implements ICountry {

    private String name;
    private int gamesPlayed = 0;
    private WinLooseTie winLooseTie = new WinLooseTie();
    private Goals goals = new Goals();
    private int points = 0;

    Country(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public WinLooseTie getWinLooseTie() {
        return winLooseTie;
    }

    @Override
    public Goals getGoals() {
        return goals;
    }

    @Override
    public int getPoints() {
        return points;
    }


    @Override
    public void update(int newGoals, int newGoalsAgainst) {
        System.out.println("Country = " + name);
        System.out.println("This just in: goals = " + newGoals + " - " + newGoalsAgainst);
        this.goals.addGoals(newGoals);
        System.out.println("goals.getGoals() = " + goals.getGoals());
        this.goals.addGoalsAgainst(newGoalsAgainst);
        this.goals.addGoalDiff(newGoals - newGoalsAgainst);
        gamesPlayed++;
        switch (points(newGoals, newGoalsAgainst)) {
            case 0:
                winLooseTie.addOneLoose();
                break;
            case 1:
                winLooseTie.addOneTie();
                points++;
                break;
            case 3:
                winLooseTie.addOneWin();
                points += 3;
                break;
        }
    }

    @Override
    public void liveUpdate(int oldGoals, int oldGoalsAgainst, int newGoals, int newGoalsAgainst, boolean alreadyStarted) {
        System.out.println("" + getName() + " hat jetzt ...");
        if(!alreadyStarted) {
            winLooseTie.addOneTie();
            points++;
            System.out.println("... 1 Punkt mehr ...");
            gamesPlayed++;
        }
        this.goals.addGoals(newGoals - oldGoals);
        System.out.println("...  " + (newGoals - oldGoals) + " Tor(e) mehr ...");
        this.goals.addGoalsAgainst(newGoalsAgainst - oldGoalsAgainst);
        this.goals.addGoalDiff(newGoals - oldGoals - newGoalsAgainst + oldGoalsAgainst);
        switch (points(newGoals, newGoalsAgainst) - points(oldGoals, oldGoalsAgainst)) {
            case 3:
                winLooseTie.addOneWin();
                winLooseTie.minusOneLoose();
                points += 3;
                System.out.println("... 3 Punkte mehr ...");
                break;
            case 2:
                winLooseTie.addOneWin();
                winLooseTie.minusOneTie();
                points += 2;
                System.out.println("... 2 Punkte mehr ...");
                break;
            case 1:
                winLooseTie.addOneTie();
                winLooseTie.minusOneLoose();
                points += 1;
                System.out.println("... 1 Punkt mehr ...");
                break;
            case -1:
                winLooseTie.addOneLoose();
                winLooseTie.minusOneTie();
                points -= 1;
                System.out.println("... 1 Punkt weniger ...");
                break;
            case -2:
                winLooseTie.addOneTie();
                winLooseTie.minusOneWin();
                points -= 2;
                System.out.println("... 2 Punkte weniger ...");
                break;
            case -3:
                winLooseTie.addOneLoose();
                winLooseTie.minusOneWin();
                points -= 3;
                System.out.println("... 3 Punkte weniger ...");
                break;
        }
        System.out.println(toString());
    }

    @Override
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
                ", winLooseTie=" + (winLooseTie) +
                ", goals=" + (goals) +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(@NotNull ICountry o) {
        System.out.println("" + o.getName() + " o.getPoints() = " + o.getPoints() + " - " + o.getGoals().getGoalDiff());
        System.out.println("" + getName() + " getPoints() = " + getPoints() + " - " + getGoals().getGoalDiff());
        if (o.getPoints() != points)
            return o.getPoints() - points;
        // TODO zusätzliche Sortierungskriterien
        return o.getGoals().getGoalDiff() - goals.getGoalDiff();
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
