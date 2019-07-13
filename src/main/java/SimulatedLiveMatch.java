import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimulatedLiveMatch extends Thread{
    private ICountry country0, country1;
    private Map<Integer, Integer> oldGoals;
    private Map<Integer, Integer> newGoals;
    private Random r;
    private boolean matchFinished = false;
    private boolean newGoalsUpdated = false;

    SimulatedLiveMatch(ICountry country0, ICountry country1) {
        if(country0.getName().equals(country1.getName()))
            throw new IllegalArgumentException("Land kann nicht gegen sich selbst spielen");
        this.country0 = country0;
        this.country1 = country1;
        r = new Random();
        oldGoals = new HashMap<Integer, Integer>(){{ put(0, 0); put(1, 0); }};
        newGoals = new HashMap<Integer, Integer>(){{ put(0, 0); put(1, 0); }};
    }

    public ICountry getCountry0() {
        return country0;
    }

    public ICountry getCountry1() {
        return country1;
    }

    public int getGoals0() {
        return newGoals.get(0);
    }

    public int getGoals1() {
        return newGoals.get(1);
    }

    public Map<Integer, Integer> getOldGoals() {
        return oldGoals;
    }

    public boolean isFinished() {
        return matchFinished;
    }

    public void update() {
        country0.update(oldGoals.get(0), oldGoals.get(1));
        country1.update(oldGoals.get(1), oldGoals.get(0));
    }

    public void liveUpdate(boolean alreadyStarted) {
        if(newGoalsUpdated) return;
        country0.liveUpdate(oldGoals.get(0), oldGoals.get(1), newGoals.get(0), newGoals.get(1), alreadyStarted);
        country1.liveUpdate(oldGoals.get(1), oldGoals.get(0), newGoals.get(1), newGoals.get(0), alreadyStarted);
        newGoalsUpdated = true;
    }

    @Override
    public void run() {
        int idxNextGoal;
        int[] nextGoal = new int[]{0, 0, 0, 0, 1, 1};
        for(int i = 0; i < 10; i++) {
            try {
                sleep(1000);
                idxNextGoal = r.nextInt(2);
                if(newGoalsUpdated) {
                    oldGoals.put(0, newGoals.get(0));
                    oldGoals.put(1, newGoals.get(1));
                }
                newGoals.put(idxNextGoal, oldGoals.get(idxNextGoal) + nextGoal[r.nextInt(6)]);
                newGoalsUpdated = false;
            }
            catch(InterruptedException e) {
                System.out.println("e.getStackTrace() = " + Arrays.toString(e.getStackTrace()));
            }
        }
        matchFinished = true;
    }
}
