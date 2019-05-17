package JobOonja.Controller;


import JobOonja.Services.EndorseUserSkill;
import JobOonja.Services.ShowOneProject;
import JobOonja.itemException.itemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.Functions.Functions.*;

@Controller
public class EndorseUserSkillsCtl {

    @RequestMapping(value = "/user/{id}/skill",method= RequestMethod.PUT,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String endorseSkillHandeler(@PathVariable("id") String uid, @RequestParam(value = "skillName",required = false) String sname, HttpServletRequest request) {
        String userName = request.getAttribute("userName").toString();
        ShowOneProject showOneProject = new ShowOneProject();
        HashMap<String,ArrayList<String>> endorsedSkill = showOneProject.getEndorserUserSkill(userName);
        EndorseUserSkill endorseUserSkill = new EndorseUserSkill();
        String res;
        Boolean success;
        Integer code;
//        System.out.println(sname);
        if(sname == null){
            res = "invalid parameter";
            code = 400;
            success = false;
        }else {
            Boolean isFirst = true;
            if (endorsedSkill.containsKey(uid)) {
                ArrayList<String> sn = endorsedSkill.get(uid);
                for (String s : sn) {
                    if (s.equals(sname)) {
                        isFirst = false;
                    }
                }
            }
            if (isFirst) {
                try {
                    endorseUserSkill.endorseSkills(userName,uid, sname);
                    if (endorsedSkill.containsKey(uid)) {
                        ArrayList<String> endList = endorsedSkill.get(uid);
                        endList.add(sname);
                    } else {
                        ArrayList<String> endList2 = new ArrayList<>();
                        endList2.add(sname);
                        endorsedSkill.put(uid, endList2);
                    }
                    res = "عملیات موفق آمیز بود!";
                    code = 200;
                    success = true;
                    ArrayList <String> userEndorsedSkill = endorsedSkill.get(uid);
                    return createJsonResponseForEndorse(res,code,success,userEndorsedSkill).toString();
                } catch (itemNotFoundException ie) {
                    res = ie.getMessage();
                    if (res.equals("permission denied"))
                        code = 403;
                    else
                        code = 406;
                    success = false;
                }
            } else {
                res = "شما قبلا این مهارت را تایید کرده اید.";
                code = 406;
                success = false;
            }
        }
        return createJsonResponse(res,code,success).toString();
    }

}
