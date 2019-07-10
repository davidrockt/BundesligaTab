public interface ICountry extends Comparable<Country> {
    String getName();
    int getGamesPlayed();
    WinLooseTie getWinLooseTie();
    Goals getGoals();
    int getPoints();

    /**
     *  Gibt dem Land ein Update der Werte (Tore, Punkte, usw) gemäß dem endgültigen
     *  Ergebnis eines beendeten Spiels
     *
     * @param goals Eigene Tore
     * @param goalsAgainst  Tore der Gegenmannschaft
     */
    void update(int goals, int goalsAgainst);

    /**
     * Gibt dem Land ein Update der Werte (Tore, Punkte, usw) gemäß dem aktuellen Spielstand eines Live-Spiels
     *
     * @param oldGoals alter, bereits verbuchter Spielstand
     * @param oldGoalsAgainst
     * @param newGoals neuer, zu verbuchender Spielstand
     * @param newGoalsAgainst
     * @param alreadyStarted Hat das Spiel bereits begonnen?
     */
    void liveUpdate(int oldGoals, int oldGoalsAgainst, int newGoals, int newGoalsAgainst, boolean alreadyStarted);

    /**
     * Wie viele Punkte würde der eingegebene Spielstand ergeben?
     *
     * @param goals Tore
     * @param goalsAgainst Gegentore
     * @return Punkte
     */
    int points(int goals, int goalsAgainst);
}
