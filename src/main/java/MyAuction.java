import java.util.ArrayList;
//Receiver
public class MyAuction {
    private ArrayList <User> users = new ArrayList<User>();

    public void addUser(User user){

        this.users.add(user);
    }

    public ArrayList<User> getUsers(){

        return this.users;
    }

}
