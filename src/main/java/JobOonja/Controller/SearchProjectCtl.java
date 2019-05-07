package JobOonja.Controller;

import JobOonja.Entities.Project;
import JobOonja.Entities.User;
import JobOonja.Services.SearchProject;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class SearchProjectCtl {

    @RequestMapping(value = "/search/project",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchProjectHandler(@RequestParam(value = "name") String name){

        SearchProject searchProject = new SearchProject();
        ArrayList<Project> projects  =searchProject.getSearchReasult(name);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for(Project p : projects){
            JsonElement jsonElement = gson.toJsonTree(p);
            jsonArray.add(jsonElement);
        }
        return jsonArray.toString();
    }
}
