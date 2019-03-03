package Services;

import JoboonjaDB.User;
import itemException.*;

import static JoboonjaDB.JDB.accessDataBase;

public class EndorseUserSkill {

    public Boolean endorseSkills(String uid,String sName){
        Boolean end;
        System.out.println("uid : "+uid + " sname : "+sName);
        try {
            User user = accessDataBase().getUserBaseOnId(uid);
            user.addPointToSkill(sName);
            end = true;
        }catch (itemNotFoundException ie){
            end = false;
        }
        return end;
    }
}
