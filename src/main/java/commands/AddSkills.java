package commands;

import AuctionData.MyAuction;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AddSkills implements Instruction {

    private MyAuction myAuction;
    private String data;
    public AddSkills(MyAuction myAuction,String data){
        this.myAuction = myAuction;
        this.data = data;
    }

    public void run() {
        Gson g = new Gson();
        JsonArray jsonArray = g.fromJson(this.data,JsonArray.class);
        for (JsonElement je : jsonArray){
            JsonObject jsonObject = g.fromJson(je,JsonObject.class);
            String name = jsonObject.get("name").getAsString();
            this.myAuction.addSkills(name);
        }
    }
}
