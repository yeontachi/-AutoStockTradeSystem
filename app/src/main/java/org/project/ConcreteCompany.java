package org.project;
import java.util.Random;

public class ConcreteCompany {
    private final String companyId;
    private final String name;
    private int price;

    public ConcreteCompany(String companyId, String name, int initialPrice) {
        this.companyId = companyId;
        this.name = name;
        this.price = initialPrice; // 초기 가격 설정
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void updatePrice() {
        Random random = new Random();
        double changeFactor = 0.9 + (1.1 - 0.9) * random.nextDouble(); // 10% 변동
        price = (int) (price * changeFactor);
    }
}
