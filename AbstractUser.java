import java.util.LinkedList;

public abstract class AbstractUser {
    private final int userId;
    private final String name;
    private int money;
    private int highCondition;
    private int lowCondition;
    private final LinkedList<AbstractCompany> companyList = new LinkedList<>();
    private final LinkedList<String> tradeHistory = new LinkedList<>();

    public AbstractUser(int userId, String name, int initialMoney) {
        this.userId = userId;
        this.name = name;
        this.money = initialMoney;
    }

    // Getter and Setter methods
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHighCondition() {
        return highCondition;
    }

    public void setHighCondition(int highCondition) {
        this.highCondition = highCondition;
    }

    public int getLowCondition() {
        return lowCondition;
    }

    public void setLowCondition(int lowCondition) {
        this.lowCondition = lowCondition;
    }

    // AbstractCompany 관련 메서드
    public void addCompany(AbstractCompany company) {
        companyList.add(company);
    }

    public void deleteCompany(AbstractCompany company) {
        companyList.remove(company);
    }

    public boolean hasCompany(AbstractCompany company) {
        return companyList.contains(company);
    }

    public void addTradeList(String type, AbstractCompany company, int price) {
        tradeHistory.add(this.getName() + "   |   " + type + "   |   " + company.getName() + "   |   " + price);
    }

    public LinkedList<String> getTradeHistory() {
        return tradeHistory;
    }

    public abstract void showDetails();
}
