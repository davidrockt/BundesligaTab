public class LiveMatch extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                sleep(5000);
            }
            catch(InterruptedException e) {
            }
            System.out.println("Demo-Thread");
        }
    }
}
