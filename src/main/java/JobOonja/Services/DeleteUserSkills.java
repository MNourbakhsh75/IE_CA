package JobOonja.Services;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.JoboonjaDB.User;

import static JobOonja.JoboonjaDB.JDB.accessDataBase;

public class DeleteUserSkills {

    public static Boolean deleteUserSkills(String uid,String name){
        Boolean success;
        try {
            User user = accessDataBase().getUserBaseOnId(uid);
            user.deleteSkills(name);
            success = true;
        }catch (itemNotFoundException ie){
            success = false;
        }
        return success;
    }
}
