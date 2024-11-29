import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        // 사용자 생성
        AbstractUser userA = new ConcreteUser(1, "UserA", 1000000);
        userA.setHighCondition(80000);
        userA.setLowCondition(60000);

        AbstractUser userB = new ConcreteUser(2, "UserB", 1500000);
        userB.setHighCondition(75000);
        userB.setLowCondition(65000);

        // 회사 생성
        AbstractCompany company1 = new ConcreteCompany("C001", "Samsung Elec.", 77777);
        AbstractCompany company2 = new ConcreteCompany("C002", "Apple.Corp", 95840);

        // 매니저 생성 및 스레드 시작
        Manager manager = new Manager(Arrays.asList(userA, userB), Arrays.asList(company1, company2));
        manager.start();

        // 10초 후 매니저 스레드 종료
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        manager.stopRunning();

        // 거래 내역 출력
        System.out.println("\n--- Trade History ---");
        for (String record : userA.getTradeHistory()) {
            System.out.println(record);
        }
        System.out.println("------------------------------------------------------------------");
        for (String record : userB.getTradeHistory()) {
            System.out.println(record);
        }
    }
}