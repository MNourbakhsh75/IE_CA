package AuctionData;

import itemException.*;
import java.util.ArrayList;

//Receiver
public class MyAuction {
    private ArrayList <User> users = new ArrayList<User>();
    private ArrayList <Project> projects = new ArrayList<Project>();
//    private ArrayList <AuctionData.User> biddingUser = new ArrayList<AuctionData.User>();

    public void addUser(User user){

        this.users.add(user);
    }

    public void addProject(Project project){

        this.projects.add(project);
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    //    public void addBiddingUser(AuctionData.User user){
//
//        this.biddingUser.add(user);
//    }

//

    public User getUserBaseOnId(String id){
        User u = null;
        for (User us: this.users ){
            if (us.getId().equals(id))
                u = us;
        }
        return u;
    }
    public Project getProjectBaseOnId (String id){
        Project p = null;
        for(Project pj : this.projects){
            if(pj.getId().equals(id))
                p = pj;
        }
        return p;
    }
    public void checkForUniqueUser(String id) throws ItemAlreadyExistsException {
        for(User u: this.users){
            if(u.getId().equals(id))
                throw new ItemAlreadyExistsException();
        }
    }
    public void checkForUniqueProjectId(String id) throws ItemAlreadyExistsException{
        for(Project p: this.projects){
            if(p.getId().equals(id))
                throw new ItemAlreadyExistsException();
        }
    }
}
