package JobOonja.Controller;

import JobOonja.itemException.itemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Services.DeleteUserSkills.deleteUserSkills;

@Controller
public class DelUserSkillsCtl {
    @RequestMapping(value = "/user/{id}/skill/delete",method= RequestMethod.DELETE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delUserSkillHandeler(@PathVariable("id") String uid, @RequestParam("skillName") String sname) {
        String msg;
        Boolean success;
        Integer code;
        try {
            deleteUserSkills(uid,sname);
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
