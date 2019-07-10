public class Match {
    private ICountry team1, team2;
    private Integer goals1, goals2;

    public Match(ICountry team1, ICountry team2, int goals1, int goals2) {
        if(team1.getName().equals(team2.getName()))
            throw new IllegalArgumentException("Land kann nicht gegen sich selbst spielen");
        System.out.println("team1 = " + team1);
        System.out.println("goals1 = " + goals1);


        this.team1 = team1;
        this.team2 = team2;
        this.goals1 = goals1;
        this.goals2 = goals2;
    }

    public void update() {
        team1.update(goals1, goals2);
        team2.update(goals2, goals1);
    }

    public ICountry getTeam1() {
        return team1;
    }

    public void setTeam1(ICountry team1) {
        this.team1 = team1;
    }

    public ICountry getTeam2() {
        return team2;
    }

    public void setTeam2(ICountry team2) {
        this.team2 = team2;
    }

    public int getGoals1() {
        return goals1;
    }

    public void setGoals1(int goals1) {
        this.goals1 = goals1;
    }

    public int getGoals2() {
        return goals2;
    }

    public void setGoals2(int goals2) {
        this.goals2 = goals2;
    }
}
