import java.util.ArrayList;
//Receiver
public class MyAuction {
    private ArrayList <User> users = new ArrayList<User>();
    private ArrayList <Project> projects = new ArrayList<Project>();
    private ArrayList <User> biddingUser = new ArrayList<User>();

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

    public void addBiddingUser(User user){

        this.biddingUser.add(user);
    }

    public ArrayList<User> getBiddingUser(){

        return this.biddingUser;
    }
    public User getUserBaseOnUsername(String username){
        User u = null;
        for (User us: this.users ){
            if (us.getUsername().equals(username))
                u = us;
        }
        return u;
    }
    public Project getProjectBaseOnTitle (String title){
        Project p = null;
        for(Project pj : this.projects){
            if(pj.getTitle().equals(title))
                p = pj;
        }
        return p;
    }
}
