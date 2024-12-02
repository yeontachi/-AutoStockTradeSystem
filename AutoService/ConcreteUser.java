import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConcreteUser {
    private final int userId;
    private final String name;
    private int money;
    private int highCondition;
    private int lowCondition;

    // 회사별 주식 보유 수를 관리
    private final Map<ConcreteCompany, Integer> stockHoldings = new HashMap<>();
    private final LinkedList<String> tradeHistory = new LinkedList<>();

    public ConcreteUser(int userId, String name, int initialMoney) {
        this.userId = userId;
        this.name = name;
        this.money = initialMoney;
    }

    // 초기 보유 주식 설정
    public void initializeStocks(List<ConcreteCompany> companies) {
        for (ConcreteCompany company : companies) {
            stockHoldings.put(company, 10); // 초기 보유 수를 10주로 설정
        }
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

    // 주식 보유 여부 확인 메서드
    public boolean hasCompany(ConcreteCompany company) {
        return getStockCount(company) > 0;
    }

    // 주식 보유 수 관련 메서드
    public int getStockCount(ConcreteCompany company) {
        return stockHoldings.getOrDefault(company, 0);
    }

    public void addStock(ConcreteCompany company, int amount) {
        stockHoldings.put(company, getStockCount(company) + amount);
    }

    public void removeStock(ConcreteCompany company, int amount) {
        int currentCount = getStockCount(company);
        if (currentCount >= amount) {
            stockHoldings.put(company, currentCount - amount);
        }
    }

    // 거래 내역 추가
    public void addTradeList(String type, ConcreteCompany company, int price) {
        tradeHistory.add(this.getName() + "   |   " + type + "   |   " + company.getName() + "   |   $" + price);
    }

    public LinkedList<String> getTradeHistory() {
        return tradeHistory;
    }
}
