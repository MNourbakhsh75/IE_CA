package Services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import JoboonjaDB.*;
import Request.*;
import commands.AddProject;
import commands.Instruction;
import itemException.NotEnoughSkillsException;
import itemException.itemNotFoundException;

import static Functions.Functions.*;
import static JoboonjaDB.JDB.accessDataBase;

@WebServlet("/project")
public class ShowAllProject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                        response.sendError(404, m);
                    }
                }
            }
        }catch (itemNotFoundException ie){
            response.sendError(404, ie.getMessage());
        }
        request.setAttribute("showProjects", showProjects);
        request.getRequestDispatcher("/AllProject.jsp").forward(request, response);

    }
}

