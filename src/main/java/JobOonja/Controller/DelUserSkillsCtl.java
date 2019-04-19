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
    @RequestMapping(value = "/user/{id}/skill",method= RequestMethod.DELETE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delUserSkillHandeler(@PathVariable("id") String uid, @RequestParam(value = "skillName",required = false) String sname) {
        String msg;
        Boolean success;
        Integer code;
        if(sname == null){
            msg = "invalid parameter";
            code = 400;
            success = false;
        }else {
            try {
                deleteUserSkills(uid, sname);
                msg = "عملیات موفق آمیز بود!";
                code = 200;
                success = true;
            } catch (itemNotFoundException ie) {
                msg = ie.getMessage();
                if (msg.equals("permission denied"))
                    code = 403;
                else
                    code = 406;
                success = false;
            }
        }
        return createJsonResponse(msg,code,success).toString();
    }
}
