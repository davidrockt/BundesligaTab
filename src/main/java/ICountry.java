public interface ICountry {
    String getName();
    int getGamesPlayed();
    int[] getWinLoose();
    int[] getGoals();
    int getPoints();
    void update(int goals, int goalsAgainst);
}
