public class Match {
    private Country team1, team2;
    private Integer goals1, goals2;

    public Match() {
    }

    public Match(Country team1, Country team2, int goals1, int goals2) {
        System.out.println("team1 = " + team1);
        System.out.println("goals1 = " + goals1);
        team1.update(goals1, goals2);
        team2.update(goals2, goals1);

        this.team1 = team1;
        this.team2 = team2;
        this.goals1 = goals1;
        this.goals2 = goals2;
    }

    /* "{\"country1\":\"de\",\"country2\":\"br\",\"goals1\":\"3\",\"goals2\":\"1\"}"
    public Match(String input) {
        this.bla = input;
    }*/

    public Country getTeam1() {
        return team1;
    }

    public void setTeam1(Country team1) {
        this.team1 = team1;
    }

    public Country getTeam2() {
        return team2;
    }

    public void setTeam2(Country team2) {
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
