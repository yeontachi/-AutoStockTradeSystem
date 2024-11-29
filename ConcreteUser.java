public class ConcreteUser extends AbstractUser {
    public ConcreteUser(int userId, String name, int initialMoney) {
        super(userId, name, initialMoney);
    }

    @Override
    public void showDetails() {
        System.out.println("User ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Money: " + getMoney());
    }
}
