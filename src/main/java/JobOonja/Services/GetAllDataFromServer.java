package JobOonja.Services;

import JobOonja.DataLayer.DataMapper.ProjectMapper;
import JobOonja.DataLayer.DataMapper.SkillsMapper;
import JobOonja.Entities.Project;
import JobOonja.Request.Request;
import JobOonja.commands.*;
import JobOonja.commands.Instruction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.ProjectMapper.insertProjecsToDB;
import static JobOonja.DataLayer.DataMapper.ProjectMapper.insertProjectSkillToDB;

public class GetAllDataFromServer {
    private Request request = new Request();
    private Instruction instruction;
    private Long createDate = 0L;
    public GetAllDataFromServer(){

    }
    public void getAllProjectsMethod() {
        try {
            String getProjects = request.getReq(new URL("http://142.93.134.194:8000/joboonja/project"));
            AddProject addProject = new AddProject(getProjects);
            ArrayList<Project> projects = addProject.run();
            ProjectMapper projectMapper = new ProjectMapper();
            ArrayList<Project> newList = new ArrayList<>();
            for(Project p: projects){
                if(p.getCreationDate() > this.createDate)
                    this.createDate = p.getCreationDate();
            }
            insertProjecsToDB(projects);
            for(Project p : projects){
                insertProjectSkillToDB(p);
            }
        } catch (IOException | SQLException e1) {
            System.out.println(e1);
        }
    }
    public void getAllProjectsMethodRunable() {
        System.out.println("ffff");
        try {
            String getProjects = request.getReq(new URL("http://142.93.134.194:8000/joboonja/project"));
            AddProject addProject = new AddProject(getProjects);
            ArrayList<Project> projects = addProject.run();
            ArrayList<Project> newList = new ArrayList<>();
            for(Project p : projects){
                if(p.getCreationDate() > this.createDate){
                    newList.add(p);
                    this.createDate = p.getCreationDate();
                }
            }
            for(Project p : newList){
                System.out.println(p.getTitle());
            }
            insertProjecsToDB(newList);
            for (Project pp : newList){
                insertProjectSkillToDB(pp);
            }

        }catch (IOException | SQLException e1){
            System.out.println(e1);
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
