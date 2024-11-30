package org.project;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final int userId;
    private final String name;
    private int seed; // 초기 자본
    private int money; // 현재 잔고
    private final Map<AbstractCompany, Conditions> companyConditions = new HashMap<>();

    public User(int userId, String name, int money) {
        this.userId = userId;
        this.name = name;
        this.seed = money;
        this.money = seed;
    }

    // 회사별 조건 설정
    public void setConditionsForCompany(AbstractCompany company, int lowCondition, int highCondition) {
        companyConditions.put(company, new Conditions(lowCondition, highCondition));
    }

    // 회사별 조건 가져오기
    public Conditions getConditionsForCompany(AbstractCompany company) {
        return companyConditions.get(company);
    }

    // 거래 조건 확인
    public boolean shouldBuy(AbstractCompany company) {
        Conditions conditions = companyConditions.get(company);
        return conditions != null && company.getPrice() < conditions.lowCondition;
    }

    public boolean shouldSell(AbstractCompany company) {
        Conditions conditions = companyConditions.get(company);
        return conditions != null && company.getPrice() > conditions.highCondition;
    }

    // 잔고 업데이트
    public void updateMoney(int amount) {
        money += amount;
    }

    // Getters
    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
    public static class Conditions {
        int lowCondition;
        int highCondition;

        public Conditions(int lowCondition, int highCondition) {
            this.lowCondition = lowCondition;
            this.highCondition = highCondition;
        }

        public int getLowCondition() {
            return lowCondition;
        }

        public int getHighCondition() {
            return highCondition;
        }
    }
}
