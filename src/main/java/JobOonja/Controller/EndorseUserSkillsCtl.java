package JobOonja.Controller;


import JobOonja.Services.EndorseUserSkill;
import JobOonja.itemException.itemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Functions.Functions.endorsedSkill;

@Controller
public class EndorseUserSkillsCtl {

    @RequestMapping(value = "/user/{id}/skill/endorse",method= RequestMethod.PUT,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String endorseSkillHandeler(@PathVariable("id") String uid, @RequestParam("skillName") String sname) {

        EndorseUserSkill endorseUserSkill = new EndorseUserSkill();
        String res;
        Boolean success;
        Integer code;
        Boolean isFirst = true;
        if(endorsedSkill.containsKey(uid)){
            ArrayList<String> sn = endorsedSkill.get(uid);
            for(String s : sn){
                if(s.equals(sname)){
                    isFirst = false;
                }
            }
        }
        if(isFirst){
            try {
                endorseUserSkill.endorseSkills(uid,sname);
                if(endorsedSkill.containsKey(uid)){
                    ArrayList<String> endList = endorsedSkill.get(uid);
                    endList.add(sname);
                }else {
                    ArrayList<String> endList2 = new ArrayList<>();
                    endList2.add(sname);
                    endorsedSkill.put(uid, endList2);
                }
                res = "Done :)";
                code = 200;
                success = true;
            }catch (itemNotFoundException ie){
                res = ie.getMessage();
                if(res.equals("permission denied"))
                    code = 403;
                else
                    code = 406;
                success = false;
            }
        }else{
            res = "you can't endorse this skill again";
            code = 406;
            success = true;
        }
        return createJsonResponse(res,code,success).toString();
    }

}
