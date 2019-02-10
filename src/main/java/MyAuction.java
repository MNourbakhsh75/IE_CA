import java.util.ArrayList;
//Receiver
public class MyAuction {
    private ArrayList <User> users = new ArrayList<User>();
    private ArrayList <Project> projects = new ArrayList<Project>();

    public void addUser(User user){

        this.users.add(user);
    }

    public ArrayList<User> getUsers(){

        return this.users;
    }
    public void addProject(Project project){

        this.projects.add(project);
    }

    public ArrayList<Project> getProjects(){

        return this.projects;
    }

}
