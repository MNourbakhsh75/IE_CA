package JobOonja.Services;

import JobOonja.Entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLException;

import static JobOonja.DataLayer.DataMapper.UserMapper.checkForUniqueUserName;
import static JobOonja.DataLayer.DataMapper.UserMapper.insertUserToDB;

public class UniqueUser {

    public Boolean uniqueUser(String userName){

        try {
            if(checkForUniqueUserName(userName)){
                return true;
            }else
                return false;
        }catch (SQLException s){
            System.out.println(s);
            return false;
        }
    }

}
