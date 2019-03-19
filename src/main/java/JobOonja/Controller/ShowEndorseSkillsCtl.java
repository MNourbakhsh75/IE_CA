package JobOonja.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.JoboonjaDB.User;
import JobOonja.Services.ShowOneProject;

import static JobOonja.Functions.Functions.endorsedSkill;
import static JobOonja.Functions.Functions.getTokenizeUrl;
import static JobOonja.Services.GetAllSkills.getAllSkills;

@WebServlet("/endorse/*")
public class ShowEndorseSkillsCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> token = getTokenizeUrl(request.getPathInfo());
        if(token.size() != 0){
            ShowOneProject showOneProject = new ShowOneProject();
            try {
                User user = showOneProject.getUserData(token.get(0));
                request.setAttribute("u",user);
                request.setAttribute("uskills",user.getSkills());
                request.setAttribute("ends",endorsedSkill);
                if(user.getId().equals("1")){
                    ArrayList<String> skills = getAllSkills();
                    request.setAttribute("skills",skills);
                    request.getRequestDispatcher("/UserSingleLoggedIn.jsp").forward(request,response);
                }else {

                    request.setAttribute("display","");
                    request.getRequestDispatcher("/UserSingleGuest.jsp").forward(request,response);
                }
            }catch (itemNotFoundException ie){
                response.sendError(404, ie.getMessage());
            }
        }else{
            response.sendError(404, "incorrect url");
        }
    }
}
