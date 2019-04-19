package JobOonja.Controller;


import JobOonja.Entities.Project;
import JobOonja.Entities.Skills;
import JobOonja.Services.GetAllProject;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Services.GetAllSkills.getAllSkills;

@Controller
public class GetAllSkillsCtl {

    @RequestMapping(value = "/skills",method=RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String allSkillsHandler() {
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        ArrayList<String> skills = getAllSkills();
        for (String s : skills){
            JsonElement jsonElement = gson.toJsonTree(s);
            jsonArray.add(jsonElement);
        }
        return jsonArray.toString();
    }
}

