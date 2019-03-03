package Services;

import JoboonjaDB.Skills;

import java.util.ArrayList;

import static JoboonjaDB.JDB.accessDataBase;

public class GetAllSkills {

    public static ArrayList<String> getAllSkills(){
        ArrayList<String> skillsName;
        skillsName = accessDataBase().getAllSkills();
        return skillsName;
    }
}
