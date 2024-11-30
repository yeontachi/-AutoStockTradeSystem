package org.project;

public class AutoHandler {
    private final User user;
    private final AbstractCompany company;

    public AutoHandler(User user, AbstractCompany company) {
        this.user = user;
        this.company = company;
    }

    public void AutoTrade() {
        if (user.shouldSell(company)) {
            new Trade().tradeType_Sell(user, company, 1);
        } else if (user.shouldBuy(company)) {
            new Trade().tradeType_Buy(user, company, 1);
        }
    }
}