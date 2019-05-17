package JobOonja.Controller;

import JobOonja.itemException.itemNotFoundException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Services.AddUserSkills.addUserSkills;

@Controller
public class AddUserSkillsCtl {
    @RequestMapping(value = "/user/{id}/skill",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserSkillHandeler(@PathVariable("id") String uid, @RequestParam(value = "skillName",required = false) String sname, HttpServletRequest request)  {
        String userName = request.getAttribute("userName").toString();
        String msg;
        Integer code;
        Boolean success;
        if(sname == null){
            msg = "داده ی نامعتبر";
            code = 400;
            success = false;
        }else {
            try {
                addUserSkills(userName,uid, sname);
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
