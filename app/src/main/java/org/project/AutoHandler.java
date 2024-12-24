package org.project;

public class AutoHandler {
    private final ConcreteUser user;
    private final ConcreteCompany company;

    public AutoHandler(ConcreteUser user, ConcreteCompany company) {
        this.user = user;
        this.company = company;
    }

    public void AutoTrade() {
        int currentPrice = company.getPrice();
        if (currentPrice > user.getHighCondition()) {
            // 매도 조건 충족
            Trade trade = new Trade();
            trade.tradeType_Sell(user, company, 1);
        } else if (currentPrice < user.getLowCondition() && !user.hasCompany(company)) {
            // 매수 조건 충족
            Trade trade = new Trade();
            trade.tradeType_Buy(user, company, 1);
        }
    }
}
