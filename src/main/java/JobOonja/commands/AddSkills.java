package JobOonja.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import static JobOonja.Entities.JDB.accessDataBase;

public class AddSkills {


    private String data;
    public AddSkills(String data){
        this.data = data;
    }

    public ArrayList<String> run() {
        Gson g = new Gson();
        JsonArray jsonArray = g.fromJson(this.data,JsonArray.class);
        ArrayList<String> skillName = new ArrayList<>();
        for (JsonElement je : jsonArray){
            JsonObject jsonObject = g.fromJson(je,JsonObject.class);
            String name = jsonObject.get("name").getAsString();
//            accessDataBase().addSkills(name);
            skillName.add(name);
        }
        return skillName;
    }
}
