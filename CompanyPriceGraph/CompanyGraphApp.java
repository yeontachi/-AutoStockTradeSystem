import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 3개의 컴퍼니를 생성하고 각 컴퍼니의 주가를 그래프로 표시
 * 좌측 상단의 버튼을 누르면 해당 컴퍼니의 주가 그래프로 변경
 * 실시간 그래프 업데이트 유지
 */

public class CompanyGraphApp {
    public static void main(String[] args) {
        // 회사 객체 생성
        ConcreteCompany company1 = new ConcreteCompany("001", "Apple", 7500);
        ConcreteCompany company2 = new ConcreteCompany("002", "Samsung", 5000);
        ConcreteCompany company3 = new ConcreteCompany("003", "Naver", 10000);

        // 각 회사의 가격 데이터를 저장할 리스트
        List<Integer> prices1 = new ArrayList<>();
        List<Integer> prices2 = new ArrayList<>();
        List<Integer> prices3 = new ArrayList<>();

        prices1.add(company1.getPrice());
        prices2.add(company2.getPrice());
        prices3.add(company3.getPrice());

        // 선택된 회사와 가격 데이터
        ConcreteCompany[] selectedCompany = {company1}; // 기본 선택된 회사
        List<Integer>[] selectedPrices = new List[]{prices1};

        // Swing 프레임 설정
        JFrame frame = new JFrame("Company Price Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // 그래프 패널
        GraphPanel graphPanel = new GraphPanel();
        graphPanel.setPrices(prices1); // 초기 회사 데이터 설정
        frame.add(graphPanel, BorderLayout.CENTER);

        // 메뉴 패널
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        frame.add(menuPanel, BorderLayout.WEST);

        // Company 1 버튼
        JButton company1Button = new JButton("Apple");
        company1Button.addActionListener(e -> {
            selectedCompany[0] = company1; // 선택된 회사 변경
            selectedPrices[0] = prices1; // 선택된 가격 데이터 변경
            graphPanel.setPrices(prices1); // 그래프 데이터 변경
        });
        menuPanel.add(company1Button);

        // Company 2 버튼
        JButton company2Button = new JButton("Samsung");
        company2Button.addActionListener(e -> {
            selectedCompany[0] = company2; // 선택된 회사 변경
            selectedPrices[0] = prices2; // 선택된 가격 데이터 변경
            graphPanel.setPrices(prices2); // 그래프 데이터 변경
        });
        menuPanel.add(company2Button);

        // Company 3 버튼
        JButton company3Button = new JButton("Naver");
        company3Button.addActionListener(e -> {
            selectedCompany[0] = company3; // 선택된 회사 변경
            selectedPrices[0] = prices3; // 선택된 가격 데이터 변경
            graphPanel.setPrices(prices3); // 그래프 데이터 변경
        });
        menuPanel.add(company3Button);

        // 타이머로 모든 회사 가격 업데이트 및 선택된 회사 그래프 갱신
        Timer timer = new Timer(1000, e -> {
            // 모든 회사 가격 업데이트
            company1.updatePrice();
            prices1.add(company1.getPrice());

            company2.updatePrice();
            prices2.add(company2.getPrice());

            company3.updatePrice();
            prices3.add(company3.getPrice());

            // 선택된 회사 그래프 업데이트
            graphPanel.setPrices(selectedPrices[0]);
        });
        timer.start();

        frame.setVisible(true);
    }
}

class ConcreteCompany {
    private final String companyId;
    private final String name;
    private int price;

    public ConcreteCompany(String companyId, String name, int initialPrice) {
        this.companyId = companyId;
        this.name = name;
        this.price = initialPrice;
    }

    public void updatePrice() {
        Random random = new Random();
        double factor = 0.8 + 0.4 * random.nextDouble(); // 0.8 ~ 1.2 범위의 난수
        this.price = (int) (this.price * factor);
        System.out.println("Updated price for " + name + ": " + price);
    }

    public int getPrice() {
        return price;
    }
}
