package JobOonja.Controller;

import JobOonja.Entities.Project;
import JobOonja.Entities.User;
import JobOonja.Services.SearchProject;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/search/project")
public class SearchProjectCtl extends HttpServlet {

//    @RequestMapping(value = "/search/project",method= RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String searchProjectHandler(@RequestParam(value = "q") String name){
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("q");
    SearchProject searchProject = new SearchProject();
        ArrayList<Project> projects  =searchProject.getSearchReasult(name);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for(Project p : projects){
            JsonElement jsonElement = gson.toJsonTree(p);
            jsonArray.add(jsonElement);
        }
        String out =  jsonArray.toString();
    PrintWriter outStream = response.getWriter();
    outStream.println(out);
    }
}
