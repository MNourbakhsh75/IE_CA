package commands;
import AuctionData.*;
import com.google.gson.*;
import commands.Instruction;
import itemException.ItemAlreadyExistsException;

public class AddProject implements Instruction {

    private MyAuction myAuction;
    private String projectData;
    public AddProject(MyAuction myAuction, String projectData){
        this.myAuction = myAuction;
        this.projectData = projectData;
    }

    public void run() {
        Gson gson = new Gson();
        JsonArray jsonObject = gson.fromJson(this.projectData,JsonArray.class);
        for(JsonElement jo : jsonObject){
            Project project = gson.fromJson(jo, Project.class);
//            System.out.println(project.getTitle());
            try {
                this.myAuction.checkForUniqueProjectId(project.getId());
                this.myAuction.addProject(project);
            }catch (ItemAlreadyExistsException ie){
                System.out.println("There is a project with this id.Try again.");
            }
        }
    }
}
