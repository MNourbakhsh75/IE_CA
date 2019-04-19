package JobOonja.Services;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Entities.Skills;

import static JobOonja.Entities.JDB.accessDataBase;

import java.util.ArrayList;
public class AddUserSkills {

    public static void addUserSkills(String id,String name) throws itemNotFoundException {
        if (id.equals("1")){
            try {
                User user = accessDataBase().getUserBaseOnId(id);
                accessDataBase().checkForValidSkill(name);
                ArrayList<Skills> us = user.getSkills();
                Boolean cv = true;
                for (Skills s : us) {
                    if (s.getName().equals(name)) {
                        cv = false;
                    }
                }
                if (cv) {
                    user.addSkill(name, 0);
                } else {
                    throw new itemNotFoundException("این مهارت وجود دارد");
                }
            } catch (itemNotFoundException i) {
                throw new itemNotFoundException(i.getMessage());
            }
    }else{
            throw new itemNotFoundException("permission denied");
        }
    }
}
