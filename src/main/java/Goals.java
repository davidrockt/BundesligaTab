public class Goals {
    private int goals;
    private int goalsAgainst;
    private int goalDiff;

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void addGoals(int numberOfGoals) {
        this.goals += numberOfGoals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void addGoalsAgainst(int numberOfGoals) {
        this.goalsAgainst += numberOfGoals;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public void addGoalDiff(int numberOfGoals) {
        this.goalDiff += numberOfGoals;
    }

    @Override
    public String toString() {
        return "Goals{" +
                "goals=" + goals +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDiff=" + goalDiff +
                '}';
    }
}
