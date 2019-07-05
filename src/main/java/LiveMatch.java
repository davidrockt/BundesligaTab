public class LiveMatch extends Thread{
    private Country country1, country2;
    private int goals1, goals2;

    LiveMatch(Country country1, Country country2) {
        this.country1 = country1;
        this.country2 = country2;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                sleep(5000);
                goals1++;
            }
            catch(InterruptedException e) {
            }
            System.out.println("Demo-Thread");
        }
    }
}
