public class WinLooseTie {
    private int win = 0;
    private int loose = 0;
    private int tie = 0;

    public int getWin() {
        return win;
    }

    public int getLoose() {
        return loose;
    }

    public int getTie() {
        return tie;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void addOneWin() {
        this.win++;
    }

    public void minusOneWin() {
        this.win--;
    }

    public void setLoose(int loose) {
        this.loose = loose;
    }

    public void addOneLoose() {
        this.loose++;
    }

    public void minusOneLoose() {
        this.loose--;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public void addOneTie() {
        this.tie++;
    }

    public void minusOneTie() {
        this.tie--;
    }
}
