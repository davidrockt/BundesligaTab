public class Country {

    private String name;
    private int gamesPlayed = 0;
    private int winLoose[] = {0, 0, 0};
    private int goals[] = {0, 0, 0};
    private int points = 0;

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

    public Country(String name) {
        this.name = name;
    }
}
