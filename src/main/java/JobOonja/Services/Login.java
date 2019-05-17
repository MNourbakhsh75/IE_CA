package JobOonja.Services;

import JobOonja.Entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLException;

import static JobOonja.DataLayer.DataMapper.UserMapper.insertUserToDB;
import static JobOonja.DataLayer.DataMapper.UserMapper.loggedInUser;

public class Login {

    public String login(JsonObject data){

        System.out.println(data.get("userName").getAsString());

        try {
            String userName = loggedInUser(data.get("userName").getAsString(),data.get("password").getAsString());
            System.out.println(userName);
            if(userName == null){
                return null;
            }
            else{
                return userName;
            }
        }catch (SQLException s){
            System.out.println(s);
            return null;
        }
    }
}
