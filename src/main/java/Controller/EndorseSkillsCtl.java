package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import JoboonjaDB.Project;
import JoboonjaDB.User;
import Services.EndorseUserSkill;
import Services.GetAllUsers;
import Services.ShowOneProject;
import itemException.*;

import static Functions.Functions.endorsedSkill;
import static Functions.Functions.getTokenizeUrl;
import itemException.*;
@WebServlet("/endorsskills")
public class EndorseSkillsCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String skillName = request.getParameter("sName");
        EndorseUserSkill endorseUserSkill = new EndorseUserSkill();
        String res;
        Boolean isFirst = true;
        if(endorsedSkill.containsKey(userId)){
            for(String ui : endorsedSkill.values()){
                if(ui.equals(skillName)){
                    isFirst = false;
                }
            }
        }
        if(isFirst){
            if(endorseUserSkill.endorseSkills(userId,skillName)){
                endorsedSkill.put(userId,skillName);
                res = "Done :)";
            }else{
                res = "Failed :(";
            }
        }else{
            res = "you can't endorse this skill again";
        }
        request.setAttribute("res",res);
        request.getRequestDispatcher("/Response.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
