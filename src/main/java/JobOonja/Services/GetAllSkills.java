package JobOonja.Services;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.SkillsMapper.getAllSkillsFromDB;
import static JobOonja.Entities.JDB.accessDataBase;
import JobOonja.DataLayer.DataMapper.*;
public class GetAllSkills {



    public static ArrayList<String> getAllSkills(){
        ArrayList<String> skillsName = new ArrayList<>();
        try
        {
            skillsName = getAllSkillsFromDB();
            return skillsName;
        }catch(SQLException se){
            System.out.println(se);
        }
        return skillsName;
    }
}
