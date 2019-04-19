package JobOonja.Services;

import JobOonja.Entities.Skills;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;

import java.util.ArrayList;

import static JobOonja.Entities.JDB.accessDataBase;

public class DeleteUserSkills {

    public static void deleteUserSkills(String uid,String name) throws itemNotFoundException {
        if (uid.equals("1")) {
            try {
                User user = accessDataBase().getUserBaseOnId(uid);
                accessDataBase().checkForValidSkill(name);
                ArrayList<Skills> uSkills = user.getSkills();
                Boolean cv = false;
                for(Skills s : uSkills){
                    if(s.getName().equals(name)){
                        cv = true;
                    }
                }
                if(cv)
                    user.deleteSkills(name);
                else
                    throw new itemNotFoundException("کاربر این مهارت را ندارد");
            } catch (itemNotFoundException ie) {
                throw new itemNotFoundException(ie.getMessage());
            }
        }else{
            throw new itemNotFoundException("permission denied");
        }
    }
}
