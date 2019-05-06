package JobOonja.Services;

import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.UserMapper.getAllUserFromDB;
import static JobOonja.Entities.JDB.accessDataBase;

public class GetAllUsers {

    public ArrayList<User> get(){
        ArrayList<User> users = new ArrayList<>();
        try {
            ArrayList<User> users2 = getAllUserFromDB();
            for(User u:  users2){
                if(!u.getId().equals("1"))
                    users.add(u);
            }
            return users;
        }catch (SQLException s){return null;}
    }
}
