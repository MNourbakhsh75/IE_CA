package JobOonja.Services;

import JobOonja.Entities.Skills;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;

import java.util.ArrayList;

import static JobOonja.Entities.JDB.accessDataBase;

public class EndorseUserSkill {

    public void endorseSkills(String uid,String sName) throws itemNotFoundException{

        if(!uid.equals("1")) {
            try {
                User user = accessDataBase().getUserBaseOnId(uid);
                accessDataBase().checkForValidSkill(sName);
                ArrayList<Skills> uSkills = user.getSkills();
                Boolean cv = false;
                for(Skills s : uSkills){
                    if(s.getName().equals(sName)){
                        cv = true;
                    }
                }
                if(cv)
                    user.addPointToSkill(sName);
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
