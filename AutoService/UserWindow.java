import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserWindow extends JFrame {
    private final ConcreteUser user;
    private final List<ConcreteCompany> companies;
    private JTextArea tradeLogArea;
    private JTextArea tradeHistoryArea;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel moneyLabel;
    private JLabel profitRateLabel;
    private Timer autoServiceTimer;
    private int totalSoldStocks = 0;
    private int initialMoney;

    public UserWindow(ConcreteUser user, List<ConcreteCompany> companies) {
        this.user = user;
        this.companies = companies;
        this.initialMoney = user.getMoney();

        setTitle("User Management - " + user.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단: 금액 및 수익률
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        moneyLabel = new JLabel("Money: $" + user.getMoney(), SwingConstants.RIGHT);
        profitRateLabel = new JLabel("Profit Rate: 0.00%", SwingConstants.RIGHT);
        infoPanel.add(moneyLabel);
        infoPanel.add(profitRateLabel);
        add(infoPanel, BorderLayout.NORTH);

        // 카드 레이아웃 설정
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 각 화면 추가
        cardPanel.add(createAutoServicePanel(), "AutoService");
        cardPanel.add(createTradeHistoryPanel(), "TradeHistory");
        add(cardPanel, BorderLayout.CENTER);

        // 하단: 버튼으로 화면 전환
        JPanel buttonPanel = new JPanel();
        JButton autoServiceButton = new JButton("Auto Service");
        autoServiceButton.addActionListener(e -> cardLayout.show(cardPanel, "AutoService"));
        buttonPanel.add(autoServiceButton);

        JButton tradeHistoryButton = new JButton("Trade History");
        tradeHistoryButton.addActionListener(e -> {
            updateTradeHistory(); // 거래 내역 업데이트
            cardLayout.show(cardPanel, "TradeHistory");
        });
        buttonPanel.add(tradeHistoryButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createAutoServicePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 상단: 회사 선택 및 조건 입력
        JPanel topPanel = new JPanel(new GridLayout(3, 1));

        // 회사 선택
        JPanel companyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        companyPanel.add(new JLabel("Select Company:"));
        JComboBox<String> companySelector = new JComboBox<>();
        companies.forEach(company -> companySelector.addItem(company.getName()));
        companyPanel.add(companySelector);
        topPanel.add(companyPanel);

        // 상한 조건 입력
        JPanel highConditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        highConditionPanel.add(new JLabel("High Condition:"));
        JTextField highConditionField = new JTextField(10);
        highConditionPanel.add(highConditionField);
        topPanel.add(highConditionPanel);

        // 하한 조건 입력
        JPanel lowConditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lowConditionPanel.add(new JLabel("Low Condition:"));
        JTextField lowConditionField = new JTextField(10);
        lowConditionPanel.add(lowConditionField);
        topPanel.add(lowConditionPanel);

        panel.add(topPanel, BorderLayout.NORTH);

        // 중앙: 거래 로그 및 메시지
        tradeLogArea = new JTextArea(10, 50);
        tradeLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tradeLogArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 하단: 자동 서비스 버튼
        JButton startAutoServiceButton = new JButton("Start Auto Service");
        startAutoServiceButton.addActionListener(e -> startAutoService(companySelector, highConditionField, lowConditionField));
        panel.add(startAutoServiceButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTradeHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 거래 내역 화면
        tradeHistoryArea = new JTextArea();
        tradeHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tradeHistoryArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void startAutoService(JComboBox<String> companySelector, JTextField highField, JTextField lowField) {
        String selectedCompanyName = (String) companySelector.getSelectedItem();
        ConcreteCompany selectedCompany = companies.stream()
                .filter(company -> company.getName().equals(selectedCompanyName))
                .findFirst()
                .orElse(null);
    
        if (selectedCompany == null) {
            tradeLogArea.append("Please select a valid company.\n");
            return;
        }
    
        try {
            int highCondition = Integer.parseInt(highField.getText());
            int lowCondition = Integer.parseInt(lowField.getText());
    
            user.setHighCondition(highCondition);
            user.setLowCondition(lowCondition);
    
            totalSoldStocks = 0; // 판매된 주식 수 초기화
    
            if (autoServiceTimer != null) {
                autoServiceTimer.stop();
            }
    
            autoServiceTimer = new Timer(1000, e -> {
                int currentPrice = selectedCompany.getPrice();
    
                // 종료 조건: 10개 주식 모두 판매 완료
                if (totalSoldStocks >= 10) {
                    tradeLogArea.append("Auto service stopped: 10 stocks sold.\n");
                    autoServiceTimer.stop();
                    return;
                }
    
                int currentStockCount = user.getStockCount(selectedCompany);
    
                // 판매 조건: 현재 가격이 상한선을 초과
                if (currentPrice > user.getHighCondition() && currentStockCount > 0) {
                    Trade trade = new Trade();
                    trade.tradeType_Sell(user, selectedCompany, 1);
                    totalSoldStocks++;
                    tradeLogArea.append("Sold 1 stock of " + selectedCompany.getName() + " at price: " + currentPrice + "\n");
                    updateUserInfo();
                } 
                // 구매 조건: 주식이 9개 이하이고 가격이 하한선 이하
                else if (currentPrice < user.getLowCondition() && currentStockCount < 10) {
                    Trade trade = new Trade();
                    trade.tradeType_Buy(user, selectedCompany, 1);
                    tradeLogArea.append("Bought 1 stock of " + selectedCompany.getName() + " at price: " + currentPrice + "\n");
                    updateUserInfo();
                } 
                // 아무 조건도 충족하지 않는 경우
                else {
                    tradeLogArea.append("... Current price of " + selectedCompany.getName() + ": $" + currentPrice + "\n");
                }
            });
            autoServiceTimer.start();
    
            tradeLogArea.append("Auto service started for " + selectedCompany.getName() + ".\n");
        } catch (NumberFormatException ex) {
            tradeLogArea.append("Invalid input for conditions.\n");
        }
    }
    

    private void updateUserInfo() {
        moneyLabel.setText("Money: $" + user.getMoney());

        double profitRate = ((double) user.getMoney() - initialMoney) / initialMoney * 100;
        profitRateLabel.setText(String.format("Profit Rate: %.2f%%", profitRate));

        if (profitRate >= 0) {
            profitRateLabel.setForeground(Color.RED);
        } else {
            profitRateLabel.setForeground(Color.BLUE);
        }
    }

    private void updateTradeHistory() {
        tradeHistoryArea.setText("");
        user.getTradeHistory().forEach(trade -> tradeHistoryArea.append(trade + "\n"));
    }
}
