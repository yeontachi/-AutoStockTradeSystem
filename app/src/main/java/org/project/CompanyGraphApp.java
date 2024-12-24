package org.project;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
            selectedCompany[0] = company1;
            selectedPrices[0] = prices1;
            graphPanel.setPrices(prices1);
            graphPanel.repaint();
        });
        menuPanel.add(company1Button);

        // Company 2 버튼
        JButton company2Button = new JButton("Samsung");
        company2Button.addActionListener(e -> {
            selectedCompany[0] = company2;
            selectedPrices[0] = prices2;
            graphPanel.setPrices(prices2);
            graphPanel.repaint();
        });
        menuPanel.add(company2Button);

        // Company 3 버튼
        JButton company3Button = new JButton("Naver");
        company3Button.addActionListener(e -> {
            selectedCompany[0] = company3;
            selectedPrices[0] = prices3;
            graphPanel.setPrices(prices3);
            graphPanel.repaint();
        });
        menuPanel.add(company3Button);

        // 사용자 버튼
        JButton userButton = new JButton("Open User Window");
        userButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            ConcreteUser user = new ConcreteUser(1, "JaeHyuk", 100000);

            // 초기 사용자 주식 설정
            user.addStock(company1, 10);
            user.addStock(company2, 10);
            user.addStock(company3, 10);

            // UserWindow 실행
            new UserWindow(user, List.of(company1, company2, company3));
        }));
        menuPanel.add(userButton);

        // 타이머로 모든 회사 가격 업데이트 및 선택된 회사 그래프 갱신
        Timer timer = new Timer(1000, e -> {
            company1.updatePrice();
            prices1.add(company1.getPrice());

            company2.updatePrice();
            prices2.add(company2.getPrice());

            company3.updatePrice();
            prices3.add(company3.getPrice());

            graphPanel.setPrices(selectedPrices[0]);
            graphPanel.repaint();
        });
        timer.start();

        frame.setVisible(true);
    }
}
