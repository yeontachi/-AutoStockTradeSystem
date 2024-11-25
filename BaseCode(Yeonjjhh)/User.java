import java.util.LinkedList;

public class User {
    private int User_id;
    public String name;
    public int money=0;
    public int high_condition=0;
    public int low_condition=0;
    LinkedList<Company> company_list = new LinkedList<>(); //유저가 가지고 있는 주식 리스트

    User(int User_id, String name, int money){
        this.User_id = User_id;
        this.name = name;
        this.money = money;
    }

    public int getMoney(){
        return money;
    }
    public String getName(){
        return name;
    }
    public int getUser_id(){
        return User_id;
    }
    public void Show_Company_List(){
        for(int i=0; i<company_list.size(); i++){
            System.out.println(company_list.get(i).getName());
        }
    }
    public void addCompany(Company com){
        company_list.add(com);
    }
    public void deleteCompany(Company com){
        company_list.remove(com);
    }
    public void SetHighCondition(int high){
        high_condition = high;
    }
    public void GetHighCondition(){
        return high_condition;
    }
    public void SetLowCondition(int low){
        low_condition = low;
    }
    public void GetLowCondition(){
        return low_condition;
    }
}
