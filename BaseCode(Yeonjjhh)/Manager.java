public class Manager extends Thread{
    /*private boolean isRunning = true;
    private User user;
    private Company company;

    public Manager(User user, Company company){
        this.user = user;
        this.company = company;
    }

    public void STOP_RUNNING(){
        isRunning = false;
    }
    @Override
    public void run(){
        AutoHandler auto = new AutoHandler();
        while(isRunning){
            auto.AutoTrade(user, company); //자동 거래 실행

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Thread interrupted");
                STOP_RUNNING();
            }
        }
        System.out.println("Thread stopped");
    }*/

    public void SetUser_Trade_Condition(User user, Company com, int high, int low){
        user.SetHighCondition(com,high);
        user.SetLowCondition(com,low);
    }//유저가 주식을 사거나 팔기 위한 조건 설정
    
    /* 
    public void Run_AutoHandler(User user, Company com){//스레드 실행 동안 계속 실행
        AutoHandler auto = new AutoHandler();
        auto.AutoTrade(user,com);
    }//자동 거래 실행 
    */

    public void Show_User_TradeList(User user){//유저의 거래 내역 출력
        user.ShowTradeList();
    }
    public void Show_UserInfo(User user){//유저 정보 출력
        System.out.println("User ID: "+user.getUser_id());
        System.out.println("User Name: "+user.getName());
        System.out.println("User Money: "+user.getMoney());
        System.out.println("User Company List: ");
        user.Show_Company_List();
    }
    public void Show_CompanyInfo(Company com){//회사 정보 출력
        //Company는 추상클래스로 나중에 여러개의 회사 클래스 생성 예정
        //여러개인 경우 반목문으로 출력 수정 필요
        System.out.println("Company ID: "+com.getCompany_id());
        System.out.println("Company Name: "+com.getName());
        System.out.println("Company Price: "+com.getPrice());
    }
}
