package Services;

import JoboonjaDB.User;

import java.util.ArrayList;

import static JoboonjaDB.JDB.accessDataBase;

public class GetAllUsers {

    public ArrayList<User> get(){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> users2 = accessDataBase().getUsers();
        for(User u:  users2){
            if(!u.getId().equals("1"))
                users.add(u);
        }
        return users;
    }
}
