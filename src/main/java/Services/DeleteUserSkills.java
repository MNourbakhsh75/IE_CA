package Services;

import JoboonjaDB.Skills;
import JoboonjaDB.User;

import static JoboonjaDB.JDB.accessDataBase;
import itemException.*;

import java.util.ArrayList;

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
