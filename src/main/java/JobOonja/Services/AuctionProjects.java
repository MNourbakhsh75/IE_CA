package JobOonja.Services;

import JobOonja.Entities.Bid;
import JobOonja.Entities.Project;
import JobOonja.Entities.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static JobOonja.DataLayer.DataMapper.ProjectMapper.doneAuction;
import static JobOonja.DataLayer.DataMapper.ProjectMapper.getAllExpiredProject;

public class AuctionProjects {

    public void actionProjects(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime());
        ArrayList<Project> projects = new ArrayList<>();
        try {
            projects = getAllExpiredProject(timestamp);
            for(Project p : projects){
                ArrayList<Bid> bids = p.getBids();
                User winner = new User();
                int max = 0;
                if(!bids.isEmpty()){
                    for(Bid b : bids){
                        if(b.getBidAmount()>max){
                            max = b.getBidAmount();
                            winner = b.getBidingUser();
                        }
                    }
                }else{
                    winner = null;
                }
                System.out.println("done auction");
                doneAuction(p.getId(),winner.getUserName());
            }
        }catch (SQLException s){
            System.out.println("actionProjects: "+s);
        }
    }
}
