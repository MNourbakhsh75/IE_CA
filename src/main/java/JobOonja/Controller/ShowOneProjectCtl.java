package JobOonja.Controller;

import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.Project;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static JobOonja.Functions.Functions.createJsonResponse;

@WebServlet("/project/")
public class ShowOneProjectCtl extends HttpServlet {

//    @RequestMapping(value = "/project/{id}",method= RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String oneProjectHandeler(@PathVariable("id") String pid) {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pid = request.getParameter("id");
    ShowOneProject showOneProject = new ShowOneProject();
    Gson gson = new Gson();
    JsonElement jsonElement = null;
    try {
        Project p = showOneProject.getProjectData(pid);
        jsonElement = gson.toJsonTree(p);
    }catch (itemNotFoundException | NotEnoughSkillsException ne){
        String out = createJsonResponse(ne.getMessage(),406,false).toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }
    String out = jsonElement.toString();
    PrintWriter outStream = response.getWriter();
    outStream.println(out);
    }
}
