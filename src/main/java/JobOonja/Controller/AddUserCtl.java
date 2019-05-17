package JobOonja.Controller;


import JobOonja.Entities.User;
import JobOonja.Services.AddUser;
import JobOonja.Services.GetAllUsers;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static JobOonja.Functions.Functions.createJsonResponse;

@Controller
public class AddUserCtl {

    @RequestMapping(value = "/register",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserHandler(HttpServletRequest request){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(request.getParameter("data"),JsonObject.class);
        AddUser addUser = new AddUser();
        Boolean resp = addUser.addUSer(jsonObject);
        if(resp)
            return createJsonResponse("عملیات موفقیت آمیز بود",200,true).toString();
        else
            return createJsonResponse("خطا در ثبت نام...دوباره تلاش کنید",406,false).toString();

    }
}
