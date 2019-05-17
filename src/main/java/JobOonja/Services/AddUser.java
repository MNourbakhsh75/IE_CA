package JobOonja.Services;

import JobOonja.Entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLException;

import static JobOonja.DataLayer.DataMapper.UserMapper.insertUserToDB;

public class AddUser {

    public Boolean addUSer(JsonObject data){

        Gson g = new Gson();
        User user = g.fromJson(data,User.class);
//        System.out.println(user.getUserName());
        try {
            insertUserToDB(user);
            return true;
        }catch (SQLException s){
            System.out.println(s);
            return false;
        }
    }
}
