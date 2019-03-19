package JobOonja.Services;

import java.util.ArrayList;

import static JobOonja.JoboonjaDB.JDB.accessDataBase;

public class GetAllSkills {

    public static ArrayList<String> getAllSkills(){
        ArrayList<String> skillsName;
        skillsName = accessDataBase().getAllSkills();
        return skillsName;
    }
}
