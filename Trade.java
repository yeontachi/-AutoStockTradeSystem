public class Trade {
    public void tradeType_Buy(AbstractUser user, AbstractCompany company, int amount) {
        if (user.getMoney() < company.getPrice() * amount) {
            System.out.println(user.getName() + " cannot afford to buy from " + company.getName());
            return;
        }
        user.setMoney(user.getMoney() - company.getPrice() * amount);
        user.addCompany(company);
        user.addTradeList("buy", company, company.getPrice());
    }

    public void tradeType_Sell(AbstractUser user, AbstractCompany company, int amount) {
        if (!user.hasCompany(company)) {
            System.out.println(user.getName() + " has no stocks to sell from " + company.getName());
            return;
        }
        user.setMoney(user.getMoney() + company.getPrice() * amount);
        user.deleteCompany(company);
        user.addTradeList("sell", company, company.getPrice());
    }
}
