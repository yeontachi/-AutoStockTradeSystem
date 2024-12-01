package org.project;

import java.util.List;

public class Manager extends Thread {
    private boolean isRunning = true;
    private final List<User> users;
    private final List<AbstractCompany> companies;
    private CompanyData comData = new CompanyData();
    // private UserPriceData UserData = new UserPriceData();

    public Manager(List<User> users, List<AbstractCompany> companies) {
        this.users = users;
        this.companies = companies;
    }

    public void stopRunning() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("\n--- Company Price Updates ---");

            // 회사 가격 업데이트
            for (AbstractCompany company : companies) {
                company.updatePrice();
                comData.UpdateCompanyPrice(company.getCompanyId(), company.getPrice());
                System.out.println(company.getName() + " new price: " + company.getPrice());
            }

            // 사용자 조건 확인 및 매매 실행
            for (User user : users) {
                for (AbstractCompany company : companies) {
                    User.Conditions conditions = user.getConditionsForCompany(company);
                    if (conditions == null)
                        continue;

                    Trade trade = new Trade();

                    // 매수 조건 확인
                    if (company.getPrice() < conditions.lowCondition) {
                        trade.tradeType_Buy(user, company, 1);
                        // UserData.UserTradeLog(user, company.getName(), 1, company.getPrice());
                    }

                    // 매도 조건 확인
                    if (company.getPrice() > conditions.highCondition) {
                        trade.tradeType_Sell(user, company, 1);
                    }
                }
            }

            // 1초 대기
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
                stopRunning();
            }
        }

        System.out.println("Manager stopped.");
    }
}
