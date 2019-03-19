package JobOonja.Controller;

import JobOonja.JoboonjaDB.Project;
import JobOonja.Services.ShowOneProject;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static JobOonja.Functions.Functions.getTokenizeUrl;

@WebServlet("/bidproject/*")
public class BidOnOneProjectCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> token = getTokenizeUrl(request.getPathInfo());
        ShowOneProject showOneProject = new ShowOneProject();
        String display;
        if (token.size() != 0){
            try {
                Project p = showOneProject.getProjectData(token.get(0));
                if (showOneProject.checkForFirstBiding("1", p)) {
                    display = "";
                } else {
                    display = "none";
                }
                request.setAttribute("display", display);
                request.setAttribute("project", p);
                request.getRequestDispatcher("/BidProject.jsp").forward(request, response);
            } catch (itemNotFoundException | NotEnoughSkillsException e) {
                response.sendError(404, e.getMessage());
            }
    }else {
            response.sendError(404, "incorrect url");
        }

    }
}
