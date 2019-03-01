package Services;

import Request.Request;
import commands.*;
import commands.Instruction;

import java.io.IOException;
import java.net.URL;

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
            instruction = new AddSkills(getSkills);
            instruction.run();
        }catch (IOException e){
            System.out.println("request failed.");
        }
    }
}
