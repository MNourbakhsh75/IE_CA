package commands;
import JoboonjaDB.*;
import com.google.gson.*;
import itemException.ItemAlreadyExistsException;

import static JoboonjaDB.JDB.accessDataBase;

public class AddProject implements Instruction {

    private String projectData;
    public AddProject(String projectData){
        this.projectData = projectData;
    }

    public void run() {
        Gson gson = new Gson();
        JsonArray jsonObject = gson.fromJson(this.projectData,JsonArray.class);
        for(JsonElement jo : jsonObject){
            Project project = gson.fromJson(jo, Project.class);
            try {
                accessDataBase().checkForUniqueProjectId(project.getId());
                accessDataBase().addProject(project);
            }catch (ItemAlreadyExistsException ie){
                System.out.println("There is a project with this id.Try again.");
            }
        }
    }
}
