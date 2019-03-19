package JobOonja.Controller;

import JobOonja.itemException.itemNotFoundException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Services.AddUserSkills.addUserSkills;

@Controller
public class AddUserSkillsCtl {
    @RequestMapping(value = "/user/{id}/skill/add",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserSkillHandeler(@PathVariable("id") String uid, @RequestParam("skillName") String sname)  {
        System.out.println("addUserSkill");
        String msg;
        Integer code;
        Boolean success;
        try {
            addUserSkills(uid,sname);
            msg = "Done :)";
            code = 200;
            success = true;
        }catch (itemNotFoundException ie){
            msg = ie.getMessage();
            if(msg.equals("permission denied"))
                code = 403;
            else
                code = 406;
            success = false;
        }
        return createJsonResponse(msg,code,success).toString();
    }

}
