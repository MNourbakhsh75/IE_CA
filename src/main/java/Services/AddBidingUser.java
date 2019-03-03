package Services;

import JoboonjaDB.*;

import itemException.*;

import static JoboonjaDB.JDB.accessDataBase;

public class AddBidingUser {

    public Boolean AddBid(String uid,String pid,Integer bidamount){
        Boolean success;
        try {
            Project project = accessDataBase().getProjectBaseOnId(pid);
            User user = accessDataBase().getUserBaseOnId(uid);
            if(project.getBudget() >= bidamount){
                Bid bid = new Bid(user,project,bidamount);
                project.addBids(bid);
                success = true;
            }else{
                success = false;
            }
        }catch (itemNotFoundException ex){
            success = false;
        }
        return success;
    }
}
