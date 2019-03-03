package Services;

import JoboonjaDB.Project;
import JoboonjaDB.User;
import itemException.ItemAlreadyExistsException;
import itemException.NotEnoughSkillsException;
import itemException.itemNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static Functions.Functions.*;
import static Functions.Functions.writeError;
import static JoboonjaDB.JDB.accessDataBase;
public class ShowOneProject {

    public Project getProjectData(String id) throws NotEnoughSkillsException,itemNotFoundException{
            Project p = null;
        ArrayList<User> projects2 = accessDataBase().getUsers();
        try {
                User user = getUserData("1");
                Project project = accessDataBase().getProjectBaseOnId(id);
                try {
                    checkForEnoughSkills(user.getSkills(),project.getSkills());
                    p = project;
                }catch (NotEnoughSkillsException e){
                    String m = "you dont have enough skills to see this project :(";
                    throw new NotEnoughSkillsException(m);
                }
            }catch (itemNotFoundException ie){
                throw new itemNotFoundException(ie.getMessage());
            }
            return p;
    }
    public User getUserData(String id) throws itemNotFoundException{
        User u = null;
        try {
            u = accessDataBase().getUserBaseOnId(id);
        }catch (itemNotFoundException ie){
            String ms = "oops...there is no project with this id !!";
            throw new itemNotFoundException(ms);
        }
        return u;
    }
    public Boolean checkForFirstBiding(String uid,Project project){
        Boolean isFirst;
        try {
            project.checkForUniqueBidingUser(uid);
            isFirst = true;
        }catch (ItemAlreadyExistsException ie){
            isFirst = false;
        }
        return isFirst;
    }
}
