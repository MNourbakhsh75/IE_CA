package JobOonja.Controller;


import JobOonja.Entities.Project;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import static JobOonja.Functions.Functions.createJsonResponse;

@Controller
public class ShowAllProjectCtl {

    @RequestMapping(value = "/project",method=RequestMethod.GET,
    produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String allProjectHandler(@RequestParam String userName) {
        GetAllProject getAllProject = new GetAllProject();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        System.out.println("req : "+userName);
        try {
            ArrayList<Project> projects = getAllProject.getProjects();
            for (Project p : projects){
                JsonElement jsonElement = gson.toJsonTree(p);
                jsonArray.add(jsonElement);
            }
        }catch (NotEnoughSkillsException | itemNotFoundException ie){
            return createJsonResponse(ie.getMessage(),406,false).toString();
        }
        return jsonArray.toString();
    }
}
