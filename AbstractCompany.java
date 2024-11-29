public abstract class AbstractCompany {
    private final String companyId;
    private final String name;
    protected int price;

    public AbstractCompany(String companyId, String name, int initialPrice) {
        this.companyId = companyId;
        this.name = name;
        this.price = initialPrice;
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

    public abstract void updatePrice();
}