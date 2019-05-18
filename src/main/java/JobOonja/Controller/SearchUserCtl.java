package JobOonja.Controller;


import JobOonja.Entities.User;
import JobOonja.Services.GetAllUsers;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class SearchUserCtl {

    @RequestMapping(value = "/user/search",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchUserHandler(@RequestParam(value = "q") String name, HttpServletRequest request){
        String userName = request.getAttribute("userName").toString();
        SearchUser searchUser = new SearchUser();
        ArrayList<User> users  = searchUser.getSearchReasult(userName,name);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();

        for(User u : users){
            JsonElement jsonElement = gson.toJsonTree(u);
            jsonArray.add(jsonElement);
        }
        return jsonArray.toString();
    }
}
