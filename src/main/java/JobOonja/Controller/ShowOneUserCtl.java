package JobOonja.Controller;

import javax.servlet.http.HttpServlet;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.Functions.Functions.createJsonResponse;



@Controller
public class ShowOneUserCtl extends HttpServlet {

    @RequestMapping(value = "/user/{id}",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String oneUserHandler(@PathVariable("id") String uid) {

        ShowOneProject showOneProject = new ShowOneProject();
        JsonElement jsonElement = null;
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        try {
            User user = showOneProject.getUserData(uid);
            jsonElement = gson.toJsonTree(user);
            jsonObject.add("user",jsonElement);
            HashMap<String,ArrayList<String>> endus= new HashMap<>();
            endus = showOneProject.getEndorserUserSkill("1");
            if (endus.containsKey(uid)){
                ArrayList<String> es = endus.get(uid);
                for(String s: es){
                    jsonArray.add(s);
                }
            }
            jsonObject.add("endorse",jsonArray);
        }catch (itemNotFoundException ie){
            return createJsonResponse(ie.getMessage(),406,false).toString();
        }
        return jsonObject.toString();
    }
}
