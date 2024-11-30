package org.project;

public class Trade {
    public void tradeType_Buy(User user, AbstractCompany company, int amount) {
        int totalPrice = company.getPrice() * amount;

        // 구매 가능 여부 확인
        if (user.getMoney() < totalPrice) {
            System.out.println(user.getName() + " cannot afford to buy from " + company.getName());
            return;
        }

        // 거래 실행
        user.updateMoney(-totalPrice);
        System.out.println(user.getName() + " bought " + amount + " stock(s) of " + company.getName() + " at price " + company.getPrice());
    }

    public void tradeType_Sell(User user, AbstractCompany company, int amount) {
        // 회사에 대한 조건 확인
        User.Conditions conditions = user.getConditionsForCompany(company);
        if (conditions == null) {
            System.out.println(user.getName() + " has no conditions set for " + company.getName());
            return;
        }

        // 거래 실행
        int totalPrice = company.getPrice() * amount;
        user.updateMoney(totalPrice);
        System.out.println(user.getName() + " sold " + amount + " stock(s) of " + company.getName() + " at price " + company.getPrice());
    }
}
