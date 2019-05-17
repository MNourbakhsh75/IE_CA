package JobOonja.Services;

import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.UserMapper.getAllUserFromDB;
import static JobOonja.Entities.JDB.accessDataBase;

public class GetAllUsers {

    public ArrayList<User> get(String userName){
        ArrayList<User> users = new ArrayList<>();
        try {
            ArrayList<User> users2 = getAllUserFromDB();
//            System.out.println("users2 "+users2);
            for(User u:  users2){
                if(!u.getUserName().equals(userName))
                    users.add(u);
            }
            return users;
        }catch (SQLException s){return null;}
    }
}
