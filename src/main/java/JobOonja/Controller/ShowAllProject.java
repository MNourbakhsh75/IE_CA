package JobOonja.Controller;


import JobOonja.JoboonjaDB.Project;
import JobOonja.Services.GetAllProject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ShowAllProject {

    @RequestMapping(value = "/project",method=RequestMethod.GET,
    produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String allProjectHandler() {
        GetAllProject getAllProject = new GetAllProject();
        ArrayList<Project> projects = getAllProject.getProjects();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        for (Project p : projects){
            JsonElement jsonElement = gson.toJsonTree(p);
            jsonArray.add(jsonElement);
        }
        return jsonArray.toString();
    }
}
