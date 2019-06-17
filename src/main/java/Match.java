public class Match {
    private Country team1, team2;
    private int goals1, goals2;
    public Match(Country team1, Country team2, int goals1, int goals2) {
        team1.update(goals1, goals2);
        team2.update(goals2, goals1);

        this.team1 = team1;
        this.team2 = team2;
        this.goals1 = goals1;
        this.goals2 = goals2;
    }
}
