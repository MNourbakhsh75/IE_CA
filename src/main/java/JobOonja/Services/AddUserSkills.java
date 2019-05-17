package JobOonja.Services;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Entities.Skills;

import static JobOonja.DataLayer.DataMapper.UserMapper.addNewSkillToUser;
import static JobOonja.Entities.JDB.accessDataBase;

import java.sql.SQLException;
import java.util.ArrayList;
public class AddUserSkills {

    public static void addUserSkills(String loggedIn,String id,String name) throws itemNotFoundException {
        if (id.equals(loggedIn)){
            try {
                addNewSkillToUser(id,name);
//                User user = accessDataBase().getUserBaseOnId(id);
//                accessDataBase().checkForValidSkill(name);
//                ArrayList<Skills> us = user.getSkills();
//                Boolean cv = true;
//                for (Skills s : us) {
//                    if (s.getName().equals(name)) {
//                        cv = false;
//                    }
//                }
//                if (cv) {
//                    user.addSkill(name, 0);
//                } else {
//                    throw new itemNotFoundException("این مهارت وجود دارد");
//                }
            } catch (SQLException i) {
                System.out.println(i);
                throw new itemNotFoundException("این مهارت وجود دارد");
            }
    }else{
            throw new itemNotFoundException("permission denied");
        }
    }
}
