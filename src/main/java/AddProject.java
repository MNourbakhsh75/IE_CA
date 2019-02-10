import com.google.gson.*;

public class AddProject implements Instruction {

    private MyAuction myAuction;
    private String projectData;
    public AddProject(MyAuction myAuction,String projectData){
        this.myAuction = myAuction;
        this.projectData = projectData;
    }


    public void run() {

        JsonObject jsonObject = new JsonParser().parse(this.projectData).getAsJsonObject();
        String titleObject = jsonObject.get("title").getAsString();
        Project project = new Project(titleObject);
        Integer budgetObject = jsonObject.get("budget").getAsInt();
        project.setBudget(budgetObject);
        JsonArray projectSkills = jsonObject.get("skills").getAsJsonArray();
        for (JsonElement je : projectSkills){
            JsonObject projectSkill = je.getAsJsonObject();
            String name = projectSkill.get("name").getAsString();
            Integer points = projectSkill.get("points").getAsInt();
            project.addSkill(name,points);
        }
        this.myAuction.addProject(project);
    }
}
