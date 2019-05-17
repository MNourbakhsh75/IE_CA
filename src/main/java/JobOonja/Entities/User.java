package JobOonja.Entities;

import java.util.ArrayList;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private String bio;
    private String userName;
    private String password;
    private ArrayList<Skills> skills = new ArrayList<Skills>();
    public User(){

    }
    public void addSkill(String name,Integer point){
        skills.add(new Skills(name, point));
    }

    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ArrayList<Skills> getSkills(){
        return this.skills;
    }

    public String getProfilePictureURL() {
        return this.profilePictureURL;
    }

    public String getBio() {
        return bio;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }

    public void addPointToSkill(String name){

        for(Skills s: this.skills){
            if(s.getName().equals(name)){
                Integer point  = s.getPoint();
                s.setPoint(point+1);
            }
        }
    }

    public void deleteSkills(String name){
        ArrayList<Skills> newSkills = new ArrayList<>();
        for(Skills s : this.skills){
            if(!s.getName().equals(name)){
                newSkills.add(s);
            }
        }
        this.skills = newSkills;
    }
}
