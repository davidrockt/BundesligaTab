import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class SimulatedLiveMatch extends Thread{
    private Country country0, country1;
    private int goals0, goals1;
    private Map<Integer, Integer> goals;
    private Random r;

    SimulatedLiveMatch(Country country0, Country country1) {
        this.country0 = country0;
        this.country1 = country1;
        r = new Random();
    }

    public Country getCountry0() {
        return country0;
    }

    public Country getCountry1() {
        return country1;
    }

    public int getGoals0() {
        return goals.get(0);
    }

    public int getGoals1() {
        return goals.get(1);
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                sleep(2000);
                int idx = r.nextInt(2);
                goals.put(idx, goals.get(idx) + 1);
            }
            catch(InterruptedException e) {
                System.out.println("e.getStackTrace() = " + Arrays.toString(e.getStackTrace()));
            }
            // System.out.println("Demo-Thread");
        }
    }
}
