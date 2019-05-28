package JobOonja.Services;

import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.UserMapper.searchBetweenUsers;

public class SearchUser {

    public ArrayList<User> getSearchReasult(String userName,String name){
        ArrayList<User> users = null;
        System.out.println("getSearchReasult");
        try {
            users = searchBetweenUsers(userName,name);
            System.out.println("user size"+users.size());
        }catch (SQLException e){
            System.out.println(e);
        }
        return users;
    }
}
