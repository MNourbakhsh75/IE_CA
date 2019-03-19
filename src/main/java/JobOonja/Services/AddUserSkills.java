package JobOonja.Services;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.JoboonjaDB.User;
import JobOonja.JoboonjaDB.Skills;

import static JobOonja.JoboonjaDB.JDB.accessDataBase;

import java.util.ArrayList;
public class AddUserSkills {

    public static Boolean addUserSkills(String id,String name){
        Boolean sc = false;

        try {
            User user = accessDataBase().getUserBaseOnId(id);
            ArrayList<Skills> us = user.getSkills();
            Boolean cv = true;
            for(Skills s : us){
                if(s.getName().equals(name)){
                    cv = false;
                }
            }
            if(cv) {
                user.addSkill(name, 0);
                sc = true;
            }
        }catch (itemNotFoundException i){
            sc = false;
        }

        return sc;
    }
}
