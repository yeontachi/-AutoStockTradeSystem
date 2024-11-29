import java.util.List;

public class Manager extends Thread {
    private boolean isRunning = true;
    private final List<AbstractUser> users;
    private final List<AbstractCompany> companies;

    public Manager(List<AbstractUser> users, List<AbstractCompany> companies) {
        this.users = users;
        this.companies = companies;
    }

    public void stopRunning() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("\n--- Company Price Updates ---");
            for (AbstractCompany company : companies) {
                company.updatePrice();
            }

            for (AbstractUser user : users) {
                for (AbstractCompany company : companies) {
                    AutoHandler autoHandler = new AutoHandler(user, company);
                    autoHandler.AutoTrade();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
                stopRunning();
            }
        }
        System.out.println("Manager stopped.");
    }
}
