import com.google.gson.*;

public class Register implements Instruction{

    private MyAuction myAuction;
    private String userData;
    public Register(MyAuction myAuction,String userData){
        this.myAuction = myAuction;
        this.userData = userData;
    }

    public void run() {

        JsonObject jsonObject = new JsonParser().parse(this.userData).getAsJsonObject();
        String userObject = jsonObject.get("username").getAsString();
        if (this.myAuction.checkForUniqueUser(userObject)){
            User user = new User(userObject);
            JsonArray skillsObject = jsonObject.get("skills").getAsJsonArray();
            for (JsonElement je : skillsObject) {
                JsonObject skillObject = je.getAsJsonObject();
                String name = skillObject.get("name").getAsString();
                Integer points = skillObject.get("points").getAsInt();
                user.addSkill(name, points);
            }
        this.myAuction.addUser(user);
        }else
            System.out.println("There is an user with this username.Try again.");
    }
}
