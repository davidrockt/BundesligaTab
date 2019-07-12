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
        this.goals.addGoals(newGoals);
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
        if(!alreadyStarted) {
            winLooseTie.addOneTie();
            points++;
            gamesPlayed++;
            // TODO von hier aus simMatch.start() ????
        }
        this.goals.addGoals(newGoals - oldGoals);
        this.goals.addGoalsAgainst(newGoalsAgainst - oldGoalsAgainst);
        this.goals.addGoalDiff(newGoals - oldGoals - newGoalsAgainst + oldGoalsAgainst);
        switch (points(newGoals, newGoalsAgainst) - points(oldGoals, oldGoalsAgainst)) {
            case 3:
                winLooseTie.addOneWin();
                winLooseTie.minusOneLoose();
                points += 3;
                break;
            case 2:
                winLooseTie.addOneWin();
                winLooseTie.minusOneTie();
                points += 2;
                break;
            case 1:
                winLooseTie.addOneTie();
                winLooseTie.minusOneLoose();
                points += 1;
                break;
            case -1:
                winLooseTie.addOneLoose();
                winLooseTie.minusOneTie();
                points -= 1;
                break;
            case -2:
                winLooseTie.addOneTie();
                winLooseTie.minusOneWin();
                points -= 2;
                break;
            case -3:
                winLooseTie.addOneLoose();
                winLooseTie.minusOneWin();
                points -= 3;
                break;
        }
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
                "name='" + name + "'}" +
                ", gamesPlayed=" + gamesPlayed +
                ", winLooseTie=" + (winLooseTie) +
                ", goals=" + (goals) +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(ICountry o) {
        if (o.getPoints() != points) {
            return o.getPoints() - points;
        }
        // TODO evtll zusätzliche Sortierungskriterien
        if (o.getGoals().getGoalDiff() != goals.getGoalDiff())
            return o.getGoals().getGoalDiff() - goals.getGoalDiff();
        return getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        if (country.getPoints() != points) {
            return points == country.getPoints();
        }
        if (country.getGoals().getGoalDiff() != goals.getGoalDiff())
            return country.getGoals().getGoalDiff() == goals.getGoalDiff();
        return country.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
