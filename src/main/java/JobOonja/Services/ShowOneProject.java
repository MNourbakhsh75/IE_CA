package JobOonja.Services;

import JobOonja.Entities.Project;
import JobOonja.Entities.User;
import JobOonja.itemException.ItemAlreadyExistsException;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.DataLayer.DataMapper.ProjectMapper.getSingleProjectFromDB;
import static JobOonja.DataLayer.DataMapper.UserMapper.gerEndorsedUserSkill;
import static JobOonja.DataLayer.DataMapper.UserMapper.getSingleUserFromDB;
import static JobOonja.Entities.JDB.accessDataBase;
public class ShowOneProject {

    public Project getProjectData(String id) throws NotEnoughSkillsException,itemNotFoundException{
            Project p = null;
        ArrayList<User> projects2 = accessDataBase().getUsers();
        try {
            p = getSingleProjectFromDB(id);

//                User user = getUserData("1");
//                Project project = accessDataBase().getProjectBaseOnId(id);
//                try {
//                    checkForEnoughSkills(user.getSkills(),project.getSkills());
//                    p = project;
//                }catch (NotEnoughSkillsException e){
//                    String m = "برای دیدن این پروژه مهارت کافی ندارید";
//                    throw new NotEnoughSkillsException(m);
//                }
            }catch (SQLException ie){
                System.out.println(ie);
                String ms = "پروژه ای با این id وجود ندارد!";
                throw new itemNotFoundException(ms);
            }
            return p;
    }
    public User getUserData(String id) throws itemNotFoundException{
        User u = null;
        try {
            u = getSingleUserFromDB(id);
        }catch (SQLException ie){
            System.out.println(ie);
            String ms = "کاربری ای با این id وجود ندارد!";
            throw new itemNotFoundException(ms);
        }
        return u;
    }
    public HashMap<String,ArrayList<String>> getEndorserUserSkill(String uid){

        HashMap<String,ArrayList<String>> endorsedSkill2 = null;
        try {
            endorsedSkill2 = gerEndorsedUserSkill(uid);

        }catch (Exception s){
            System.out.println(s);
            return null;
        }
//        for(String k : endorsedSkill2.keySet()){
//            for(String s: endorsedSkill2.get(k))
//                System.out.println("kkkkk "+s);
//        }
        return endorsedSkill2;
    }
}
