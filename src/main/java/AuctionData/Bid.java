package AuctionData;

import AuctionData.Project;
import AuctionData.User;

public class Bid {
    private User bidingUser;
    private Project project;
    private Integer bidAmount;

    public Bid(){

    }
    public User getBidingUser() {
        return bidingUser;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public Project getProject() {
        return project;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setBidingUser(User bidingUser) {
        this.bidingUser = bidingUser;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
