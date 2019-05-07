package JobOonja.Services;

import JobOonja.Entities.Project;
import JobOonja.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.ProjectMapper.searchBetweenProjects;

public class SearchProject {

    public ArrayList<Project> getSearchReasult(String name){
        ArrayList<Project> projects = null;
        try {
            projects = searchBetweenProjects(name);
        }catch (SQLException e){
            System.out.println(e);
        }

        return projects;
    }
}
