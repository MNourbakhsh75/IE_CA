package JobOonja.Controller;

import javax.servlet.http.HttpServlet;

import JobOonja.Services.UniqueUser;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.Functions.Functions.createJsonResponse;



@Controller
public class UniqueUserCtl extends HttpServlet {

    @RequestMapping(value = "/user/unique",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uniqueUserHandler(@RequestParam String username) {


        JsonElement jsonElement = null;
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        UniqueUser uniqueUser = new UniqueUser();
        Boolean resp = uniqueUser.uniqueUser(username);
        if(resp)
            return createJsonResponse("نام کاربری معتبر",200,true).toString();
        else
            return createJsonResponse("نام کاربری تکراری",406,false).toString();
    }
}
