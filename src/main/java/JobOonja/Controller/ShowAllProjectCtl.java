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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static JobOonja.Functions.Functions.createJsonResponse;

@Controller
public class ShowAllProjectCtl {

    @RequestMapping(value = "/project",method=RequestMethod.GET,
    produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String allProjectHandler(HttpServletRequest request) {
        GetAllProject getAllProject = new GetAllProject();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        String userName = request.getAttribute("userName").toString();
        try {
            ArrayList<Project> projects = getAllProject.getProjects(userName);
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
