package JobOonja.Services;

import JobOonja.itemException.ItemAlreadyExistsException;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.*;

import static JobOonja.Entities.JDB.accessDataBase;

public class AddBidingUser {

    public void AddBid(String uid,String pid,Integer bidamount) throws itemNotFoundException,ItemAlreadyExistsException{

        try {
            Project project = accessDataBase().getProjectBaseOnId(pid);
            User user = accessDataBase().getUserBaseOnId(uid);
            if(project.getBudget() >= bidamount){
                try {
                    project.checkForUniqueBidingUser(uid);
                    Bid bid = new Bid(user,bidamount);
                    project.addBids(bid);
                }catch (ItemAlreadyExistsException ie){
                    throw new ItemAlreadyExistsException(ie.getMessage());
                }
            }else{
                throw new itemNotFoundException("مقدار پیشنهادی باید کمتر از بودجه پروژه باشد");
            }
        }catch (itemNotFoundException ex){
            throw new itemNotFoundException(ex.getMessage());
        }
    }
}
