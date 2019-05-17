package JobOonja.Services;

import JobOonja.Entities.Skills;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.UserMapper.deleteUserSkill;
import static JobOonja.Entities.JDB.accessDataBase;

public class DeleteUserSkills {

    public static void deleteUserSkills(String userName,String uid,String name) throws itemNotFoundException {
        if (uid.equals(userName)) {
            try {
                deleteUserSkill(uid,name);
//                User user = accessDataBase().getUserBaseOnId(uid);
//                accessDataBase().checkForValidSkill(name);
//                ArrayList<Skills> uSkills = user.getSkills();
//                Boolean cv = false;
//                for(Skills s : uSkills){
//                    if(s.getName().equals(name)){
//                        cv = true;
//                    }
//                }
//                if(cv)
//                    user.deleteSkills(name);
//                else
//                    throw new itemNotFoundException("کاربر این مهارت را ندارد");
            } catch (SQLException ie) {
                System.out.println(ie);
                throw new itemNotFoundException("کاربر این مهارت را ندارد");
            }
        }else{
            throw new itemNotFoundException("permission denied");
        }
    }
}
