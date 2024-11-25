public class AutoHandler{//자동 거래를 위한 클래스
    /*
     * 사용자 조건에 맞춰서 한개의 회사, 한명의 유저 대응하여 자동 거래
     * 주식의 현재 가격이 high_condition보다 높으면 판매
     * 주식의 현재 가격이 low_condition보다 낮으면 구매
     * 
     */
    private User user;
    private Company com;

    AutoHandler(User user, Company com){
        this.user = user;
        this.com = com;
    }

    public void AutoTrade(User user, Company com){
        if(com.getCurrentPrice() > user.getHighCondition(com)){
            //현재 주식 가격이 유저가 설정한 최고 조건 가격보다 높을 때
            //주식을 팔아야 함
            Trade trade = new Trade();
            trade.tradType_Sell(user,com,1);//일단 주식 1주 판매로 설정
            // 자동 거래는 유저가 산 주 만큼 모두 판매
            user.deleteCompany(com);//유저가 가지고 있는 주식 리스트에서 삭제
            user.addTradeList(com,com.getCurrentPrice());// 거래 내역에 추가
        }
        else if(com.getCurrentPrice() < user.getHighCondition() &&/*user가 해당 회사 주식을 갖지 않을 떄 */){
            Trade trade = new Trade();
            trade.tradeType_Buy(user, com, 1);//일단 주식 1주 구매로 설정
            user.addCompany(com);
            user.addTradeList(com, com.getCurrentPrice());
        }
        else continue;
    }

    //public int getCurrentReturn() 현재 수익률 반환
}