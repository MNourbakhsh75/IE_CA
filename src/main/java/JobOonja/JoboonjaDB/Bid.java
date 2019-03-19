package JobOonja.JoboonjaDB;

public class Bid {
    private User bidingUser;
//    private Project project;
    private Integer bidAmount;

    public Bid(User bidingUser,Integer bidAmount){
        this.bidingUser = bidingUser;
//        this.project = project;
        this.bidAmount = bidAmount;
    }
    public User getBidingUser() {
        return bidingUser;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

//    public Project getProject() {
//        return project;
//    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setBidingUser(User bidingUser) {
        this.bidingUser = bidingUser;
    }

//    public void setProject(Project project) {
//        this.project = project;
//    }
}
