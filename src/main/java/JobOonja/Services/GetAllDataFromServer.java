package JobOonja.Services;

import JobOonja.DataLayer.DataMapper.SkillsMapper;
import JobOonja.Request.Request;
import JobOonja.commands.*;
import JobOonja.commands.Instruction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllDataFromServer {
    private Request request = new Request();
    private Instruction instruction;

    public GetAllDataFromServer(){

    }
    public void getAllProjectsMethod() {
        try {
            String getProjects = request.getReq(new URL("http://142.93.134.194:8000/joboonja/project"));
            instruction = new AddProject(getProjects);
            instruction.run();
        } catch (IOException e1) {
            System.out.println("request failed.");
        }
    }
    public void getAllSkillsMethod(){

        try {

            String getSkills = request.getReq(new URL("http://142.93.134.194:8000/joboonja/skill"));
            AddSkills addSkills = new AddSkills(getSkills);
            ArrayList<String> skills = addSkills.run();
            SkillsMapper skillsMapper = new SkillsMapper();
            skillsMapper.insertSkill(skills);
            System.out.println("add to table");
        }catch (IOException | SQLException e){
            System.out.println(e);
        }
    }
}
