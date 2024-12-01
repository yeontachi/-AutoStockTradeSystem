package org.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 예제 회사 및 사용자 생성
        List<AbstractCompany> companies = new ArrayList<>();
        CompanyData CompanyDataFile = new CompanyData();
        UserPriceData userDataFile = new UserPriceData();
        CompanyDataFile.CreateCompanyFile();

        companies.add(new ConcreteCompany("BTC", "Bitcoin", 50000));
        companies.add(new ConcreteCompany("ETH", "Ethereum", 3000));
        companies.add(new ConcreteCompany("XRP", "Ripple", 100));
        for (AbstractCompany comData : companies) {
            CompanyDataFile.AddInitialCompany(comData.getCompanyId(), comData.getName(), comData.getPrice());
        }

        User user = new User(1, "Alice", 600000); // 초기 자본 100,000
        userDataFile.CreateUserFile(user.getUserId(), user.getName(), user.getMoney());

        // 사용자로부터 조건 입력받기
        Scanner scanner = new Scanner(System.in);
        for (AbstractCompany company : companies) {
            System.out.println("Enter Low and High conditions for " + company.getName() + ": ");
            int lowCondition = scanner.nextInt();
            int highCondition = scanner.nextInt();
            user.setConditionsForCompany(company, lowCondition, highCondition);
        }

        // Manager 스레드 실행
        Manager manager = new Manager(List.of(user), companies);
        manager.start();

        // CompanyDataFile.ShowCompanyLog();
    }
}
