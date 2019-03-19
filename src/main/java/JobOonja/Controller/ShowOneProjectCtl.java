package JobOonja.Controller;

import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.JoboonjaDB.Project;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowOneProjectCtl {

    @RequestMapping(value = "/project/{id}",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String oneProjectHandeler(@PathVariable("id") String pid) {
            ShowOneProject showOneProject = new ShowOneProject();
            Gson gson = new Gson();
            JsonElement jsonElement = null;
            try {
                Project p = showOneProject.getProjectData(pid);
               jsonElement = gson.toJsonTree(p);
            }catch (itemNotFoundException | NotEnoughSkillsException ne){
                System.out.println(ne.getMessage());
            }
    return jsonElement.toString();
    }
}
