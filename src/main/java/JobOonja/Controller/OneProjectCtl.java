//package JobOonja.Controller;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import JobOonja.itemException.NotEnoughSkillsException;
//import JobOonja.itemException.itemNotFoundException;
//import JobOonja.JoboonjaDB.Project;
//import JobOonja.Services.ShowOneProject;
//
//import static JobOonja.Functions.Functions.getTokenizeUrl;
//
//@WebServlet("/project/*")
//public class OneProjectCtl extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = request.getPathInfo();
//        ArrayList<String> token = getTokenizeUrl(path);
//        if(token.size() !=0){
//            ShowOneProject showOneProject = new ShowOneProject();
//            try {
//                Project p = showOneProject.getProjectData(token.get(0));
//                request.setAttribute("project",p);
//                request.getRequestDispatcher("/OneProject.jsp").forward(request,response);
//            }catch (itemNotFoundException | NotEnoughSkillsException ne){
//                response.sendError(403,ne.getMessage());
//            }
//        }else{
//            response.sendError(404, "incorrect url");
//        }
//
//    }
//}
