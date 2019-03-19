package JobOonja.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static JobOonja.Services.AddUserSkills.addUserSkills;

@WebServlet("/adduserskills")
public class AddUserSkillsCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String sName = request.getParameter("addsname");
        String res;
        if(addUserSkills(userId,sName)){
            res = "Done :)";
        }else{
            res = "you already have this skill";
        }
        request.setAttribute("res",res);
        request.getRequestDispatcher("/Response.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
