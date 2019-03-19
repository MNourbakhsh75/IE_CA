package JobOonja.Services;

import java.util.ArrayList;
import JobOonja.JoboonjaDB.*;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import static JobOonja.Functions.Functions.checkForEnoughSkills;
import static JobOonja.JoboonjaDB.JDB.accessDataBase;

public class GetAllProject {

    public ArrayList<Project> getProjects() {
        ArrayList<Project> showProjects = new ArrayList<>();
        ArrayList<Project> projects = accessDataBase().getProjects();
        Integer counter = 0;
        Integer counter1 = 0;
        try {
            User user = accessDataBase().getUserBaseOnId("1");
            for(Project p : projects){
                try {
                    counter1++;
                    checkForEnoughSkills(user.getSkills(),p.getSkills());
                    showProjects.add(p);
                    counter++;
                } catch (NotEnoughSkillsException e) {
                    if((counter1 == projects.size()) && (counter==0)){
                        String m = "there is no project with your skills :(";
//                        response.sendError(404, m);
                    }
                }
            }
        }catch (itemNotFoundException ie){
//            response.sendError(404, ie.getMessage());
        }
        return showProjects;
    }
}

