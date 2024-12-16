import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GraphPanel extends JPanel {
    private final int MAX_DATA_POINTS = 50;
    private final int PADDING = 500; // y축 여백
    private List<Integer> prices = new LinkedList<>();

    public void setPrices(List<Integer> newPrices) {
        this.prices = newPrices;
        repaint(); // 그래프 갱신
    }

    public void addPrice(int price) {
        if (prices.size() >= MAX_DATA_POINTS) {
            prices.remove(0); // 오래된 데이터를 제거
        }
        prices.add(price);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 배경
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (prices.isEmpty()) {
            return;
        }

        // y축 범위 동적 계산
        int minPrice = prices.stream().min(Integer::compare).orElse(0) - PADDING;
        int maxPrice = prices.stream().max(Integer::compare).orElse(0) + PADDING;

        // y축 눈금 그리기
        g2d.setColor(Color.LIGHT_GRAY);
        int width = getWidth();
        int height = getHeight();

        for (int i = minPrice; i <= maxPrice; i += 1000) {
            int y = height - (i - minPrice) * height / (maxPrice - minPrice);
            g2d.drawLine(0, y, width, y);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(i), 5, y - 5);
            g2d.setColor(Color.LIGHT_GRAY);
        }

        // 그래프 그리기
        g2d.setColor(Color.BLUE);
        int step = width / (prices.size() - 1);
        for (int i = 1; i < prices.size(); i++) {
            int x1 = (i - 1) * step;
            int y1 = height - (prices.get(i - 1) - minPrice) * height / (maxPrice - minPrice);
            int x2 = i * step;
            int y2 = height - (prices.get(i) - minPrice) * height / (maxPrice - minPrice);
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
