import java.util.Random;

public class ConcreteCompany extends AbstractCompany {
    public ConcreteCompany(String companyId, String name, int initialPrice) {
        super(companyId, name, initialPrice);
    }

    @Override
    public void updatePrice() {
        Random random = new Random();
        double changeRate = 0.8 + (0.4 * random.nextDouble());
        price = (int) (price * changeRate);
        System.out.println("Updated price for " + getName() + ": " + price);
    }
}