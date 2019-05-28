package JobOonja.Controller;


import JobOonja.Entities.Project;
import JobOonja.Entities.Skills;
import JobOonja.Services.GetAllProject;
import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.itemException.itemNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
//import org.springframework.stereotype.Controller;
////import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Services.GetAllSkills.getAllSkills;

@WebServlet("/skills")
public class GetAllSkillsCtl extends HttpServlet {

//    @RequestMapping(value = "/skills",method=RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String allSkillsHandler() {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        ArrayList<String> skills = getAllSkills();
        for (String s : skills){
            JsonElement jsonElement = gson.toJsonTree(s);
            jsonArray.add(jsonElement);
        }
        String out = jsonArray.toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }
}

