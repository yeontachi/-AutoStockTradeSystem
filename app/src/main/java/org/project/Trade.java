package org.project;
public class Trade {
    // 주식 구매 메서드
    public void tradeType_Buy(ConcreteUser user, ConcreteCompany company, int amount) {
        int totalCost = company.getPrice() * amount;
        if (user.getMoney() < totalCost) {
            System.out.println(user.getName() + " cannot afford to buy " + amount + " stocks of " + company.getName());
            return;
        }
        user.setMoney(user.getMoney() - totalCost);
        user.addStock(company, amount);
        user.addTradeList("buy", company, company.getPrice());
    }

    // 주식 판매 메서드
    public void tradeType_Sell(ConcreteUser user, ConcreteCompany company, int amount) {
        int ownedStocks = user.getStockCount(company);
        if (ownedStocks < amount) {
            System.out.println(user.getName() + " does not have enough stocks to sell from " + company.getName());
            return;
        }
        int totalRevenue = company.getPrice() * amount;
        user.setMoney(user.getMoney() + totalRevenue);
        user.removeStock(company, amount);
        user.addTradeList("sell", company, company.getPrice());
    }
}
