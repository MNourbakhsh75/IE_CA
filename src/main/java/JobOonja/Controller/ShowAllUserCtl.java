package JobOonja.Controller;

import java.util.ArrayList;

import JobOonja.Entities.User;
import JobOonja.Services.GetAllUsers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowAllUserCtl{

    @RequestMapping(value = "/user",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUsersHandler() {

        GetAllUsers getAllUsers = new GetAllUsers();
        ArrayList<User> users  = getAllUsers.get();
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for(User u : users){
            JsonElement jsonElement = gson.toJsonTree(u);
            jsonArray.add(jsonElement);
        }

        return jsonArray.toString();
    }
}