package JobOonja.commands;
import JobOonja.Entities.*;
import com.google.gson.*;
import JobOonja.itemException.ItemAlreadyExistsException;

import java.util.ArrayList;

import static JobOonja.Entities.JDB.accessDataBase;

public class AddProject {

    private String projectData;
    public AddProject(String projectData){
        this.projectData = projectData;
    }

    public ArrayList<Project> run() {
        Gson gson = new Gson();
        JsonArray jsonObject = gson.fromJson(this.projectData,JsonArray.class);
        ArrayList<Project> projects = new ArrayList<>();
        for(JsonElement jo : jsonObject){
            Project project = gson.fromJson(jo, Project.class);
            projects.add(project);
//            try {
//                accessDataBase().checkForUniqueProjectId(project.getId());
//                accessDataBase().addProject(project);
//            }catch (ItemAlreadyExistsException ie){
//                System.out.println("There is a project with this id.Try again.");
//            }
        }
        return projects;
    }
}
