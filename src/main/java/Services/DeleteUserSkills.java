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
            System.out.println("uid : "+uid);
            if(user.deleteSkills(name)){
                ArrayList<Skills> s = user.getSkills();
                for(Skills ss : s)
                    System.out.println(ss.getName());
                success = true;
            }else {
                success = false;
            }
        }catch (itemNotFoundException ie){
            success = false;
        }

        return success;
    }
}
