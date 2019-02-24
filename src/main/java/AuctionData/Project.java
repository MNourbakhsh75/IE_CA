package AuctionData;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private String title;
    private ArrayList<Skills> skills = new ArrayList<Skills>();
    private ArrayList<Bid> bids = new ArrayList<Bid>();
    private String id;
    private String description;
    private String imageUrl;
    private Long deadline;
    private User winner;
    private Integer budget;
    public Project(){

    }
    public String getTitle(){
        return this.title;
    }
    public void addSkill(String name,Integer point){
        this.skills.add(new Skills(name,point));
    }
    public void setBudget(Integer budget){
        this.budget = budget;
    }
    public Integer getBudget(){
        return this.budget;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void addBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public Long getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public User getWinner() {
        return winner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

}
