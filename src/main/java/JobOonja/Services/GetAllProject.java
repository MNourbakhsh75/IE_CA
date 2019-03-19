package JobOonja.Services;

import java.util.ArrayList;
import JobOonja.Entities.*;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import static JobOonja.Functions.Functions.checkForEnoughSkills;
import static JobOonja.Entities.JDB.accessDataBase;

public class GetAllProject {

    public ArrayList<Project> getProjects() throws NotEnoughSkillsException,itemNotFoundException {
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
                        throw new NotEnoughSkillsException(m);
                    }
                }
            }
        }catch (itemNotFoundException ie){
            throw new itemNotFoundException(ie.getMessage());
        }
        return showProjects;
    }
}

