package JobOonja.Services;

import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.UserMapper.searchBetweenUsers;

public class SearchUser {

    public ArrayList<User> getSearchReasult(String name){

        try {
            searchBetweenUsers(name);
        }catch (SQLException e){
            System.out.println(e);
        }

        return null;
    }
}
