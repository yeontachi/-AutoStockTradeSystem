import java.util.ArrayList;

public class Trade {
    //거래 내역 기록
    //거래 완료 시 User의 거래 내역에 추가
    //Company com = new Company();
    //User user = new User();

    Trade(){}

    public void tradeType_Buy(User user, Company com, int amount){
        //User, Company 객체를 인자로 받음
        //amount는 거래량(몇 주 살지)
        user.setMoney(user.getMoney() - com.getPrice() * amount);//유저의 돈 차감(주식 가격 * 주식 수량)   
        user.addCompany(com);//유저가 가지고 있는 주식 리스트에 추가
        user.addTradeList(com, com.getCurrentPrice());//거래 내역에 추가
    }

    public void tradType_Sell(User user, Company com, int amount){
        //User, Company 객체를 인자로 받음
        //amount는 거래량(몇 주 팔지)
        user.setMoney(user.getMoney() + com.getPrice() * amount);//유저의 돈 증가(주식 가격 * 주식 수량)
        user.deleteCompany(com);//유저가 가지고 있는 주식 리스트에서 삭제
        user.addTradeList(com, com.getCurrentPrice());//거래 내역에 추가
    }
}
