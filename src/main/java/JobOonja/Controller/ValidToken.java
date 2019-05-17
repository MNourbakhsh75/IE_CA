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
public class ValidToken extends HttpServlet {

    @RequestMapping(value = "/validtoken",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String validToken() {
        return createJsonResponse("احراز هویت با موفقیت انجام شده است",200,true).toString();
    }
}
