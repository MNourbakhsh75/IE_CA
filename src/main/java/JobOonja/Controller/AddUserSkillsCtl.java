package JobOonja.Controller;

import JobOonja.Services.EndorseUserSkill;
import JobOonja.Services.ShowOneProject;
import JobOonja.itemException.itemNotFoundException;
import com.google.gson.JsonObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.Functions.Functions.createJsonResponse;
import static JobOonja.Functions.Functions.createJsonResponseForEndorse;
import static JobOonja.Services.AddUserSkills.addUserSkills;
import static JobOonja.Services.DeleteUserSkills.deleteUserSkills;

@WebServlet("/user/skill")
public class AddUserSkillsCtl extends HttpServlet {
//    @RequestMapping(value = "/user/{id}/skill",method= RequestMethod.POST,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String addUserSkillHandeler(@PathVariable("id") String uid, @RequestParam(value = "skillName",required = false) String sname, HttpServletRequest request)  {
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("id");
        String sname = request.getParameter("skillName");
        System.out.println("userSkill : "+sname);
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
        String out = createJsonResponse(msg,code,success).toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getAttribute("userName").toString();
        String sname = request.getParameter("skillName");
        System.out.println("deleteSkill : "+sname);
        String uid = request.getParameter("id");
        System.out.println("deleteSkill : "+uid);

        String msg;
        Boolean success;
        Integer code;
        if(sname == null){
            msg = "invalid parameter";
            code = 400;
            success = false;
        }else {
            try {
                deleteUserSkills(userName,uid, sname);
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
        String out = createJsonResponse(msg,code,success).toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getAttribute("userName").toString();
        String sname = request.getParameter("skillName");
        System.out.println("endorseSkill username : "+userName);
        System.out.println("endorseSkill : "+sname);
        String uid = request.getParameter("id");
        System.out.println("endorseSkillUid : "+uid);
        ShowOneProject showOneProject = new ShowOneProject();
        HashMap<String, ArrayList<String>> endorsedSkill = showOneProject.getEndorserUserSkill(userName);
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
                    System.out.println("endorse done");
                    res = "عملیات موفق آمیز بود!";
                    code = 200;
                    success = true;
                    ArrayList <String> userEndorsedSkill = endorsedSkill.get(uid);
                    String out =  createJsonResponseForEndorse(res,code,success,userEndorsedSkill).toString();
                    PrintWriter outStream = response.getWriter();
                    outStream.println(out);
                    return;
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
        String out = createJsonResponse(res,code,success).toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }

    }
