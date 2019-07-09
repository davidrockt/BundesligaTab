import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimulatedLiveMatch extends Thread{
    private Country country0, country1;
    private Map<Integer, Integer> goals;
    private Map<Integer, Integer> newGoals;
    private Random r;
    private boolean matchFinished = false;
    private boolean newGoalsUpdated = false;
    private int idxNextGoal;

    SimulatedLiveMatch(Country country0, Country country1) {
        this.country0 = country0;
        this.country1 = country1;
        r = new Random();
        goals = new HashMap<Integer, Integer>(){{ put(0, 0); put(1, 0); }};
        newGoals = new HashMap<Integer, Integer>(){{ put(0, 0); put(1, 0); }};
    }

    public Country getCountry0() {
        return country0;
    }

    public Country getCountry1() {
        return country1;
    }

    public int getGoals0() {
        return newGoals.get(0);
    }

    public int getGoals1() {
        return newGoals.get(1);
    }

    public Map<Integer, Integer> getGoals() {
        return goals;
    }

    public boolean isFinished() {
        return matchFinished;
    }

    public int getIdxNextGoal() {
        return idxNextGoal;
    }

    public void update() {
        // System.out.println("----- LIVE ----");
        country0.update(goals.get(0), goals.get(1));
        country1.update(goals.get(1), goals.get(0));
    }

    public void liveUpdate(boolean alreadyStarted) {
        if(newGoalsUpdated) return;
        System.out.println("***");
        System.out.println("Alter Spielstand: DE - GB ----> " + goals.get(0) + " - " + goals.get(1));
        System.out.println("Neuer Spielstand: DE - GB jetzt " + newGoals.get(0) + " - " + newGoals.get(1));
        country0.liveUpdate(goals.get(0), goals.get(1), newGoals.get(0), newGoals.get(1), alreadyStarted);
        country1.liveUpdate(goals.get(1), goals.get(0), newGoals.get(1), newGoals.get(0), alreadyStarted);
        newGoalsUpdated = true;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            try {
                sleep(1000 * r.nextInt(8));
                idxNextGoal = r.nextInt(2);
                System.out.println("idx = " + idxNextGoal);
                if(newGoalsUpdated) {
                    goals.put(0, newGoals.get(0));
                    goals.put(1, newGoals.get(1));
                }
                newGoals.put(idxNextGoal, goals.get(idxNextGoal) + 1);
                //System.out.println("goals.get(0) = " + newGoals.get(0));
                //System.out.println("goals.get(1) = " + newGoals.get(1));
                newGoalsUpdated = false;
            }
            catch(InterruptedException e) {
                System.out.println("e.getStackTrace() = " + Arrays.toString(e.getStackTrace()));
            }
        }
        matchFinished = true;
    }
}
