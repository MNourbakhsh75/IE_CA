package JobOonja.Controller;

import javax.servlet.http.HttpServlet;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static JobOonja.Functions.Functions.createJsonResponse;


@Controller
public class ShowOneUserCtl extends HttpServlet {

    @RequestMapping(value = "/user/{id}",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String oneUserHandler(@PathVariable("id") String uid) {

        ShowOneProject showOneProject = new ShowOneProject();
        JsonElement jsonElement = null;
        Gson gson = new Gson();
        try {
            User user = showOneProject.getUserData(uid);
            jsonElement = gson.toJsonTree(user);
        }catch (itemNotFoundException ie){
            return createJsonResponse(ie.getMessage(),406,false).toString();
        }
        return jsonElement.toString();
    }
}
