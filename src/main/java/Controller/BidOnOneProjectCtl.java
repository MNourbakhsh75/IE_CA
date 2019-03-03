package Controller;

import JoboonjaDB.Project;
import JoboonjaDB.User;
import Services.ShowOneProject;
import itemException.NotEnoughSkillsException;
import itemException.itemNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static Functions.Functions.getTokenizeUrl;

@WebServlet("/bidproject/*")
public class BidOnOneProjectCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> token = getTokenizeUrl(request.getPathInfo());
        ShowOneProject showOneProject = new ShowOneProject();
        String display;
        try {
            Project p = showOneProject.getProjectData(token.get(0));
            if(showOneProject.checkForFirstBiding("1",p)){
//                System.out.println("yes");
                display = "";
            }else{
                display = "none";
//                System.out.println("no");
            }
            request.setAttribute("display",display);
            request.setAttribute("project",p);
            request.getRequestDispatcher("/BidProject.jsp").forward(request,response);
        }catch (itemNotFoundException | NotEnoughSkillsException e){
            System.out.println(e.getMessage());
        }

    }
}
