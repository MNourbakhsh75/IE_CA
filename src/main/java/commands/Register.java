package commands;
import AuctionData.*;
import com.google.gson.*;
import commands.Instruction;
import itemException.*;
public class Register implements Instruction {

    private MyAuction myAuction;
    private String userData;
    public Register(MyAuction myAuction, String userData){
        this.myAuction = myAuction;
        this.userData = userData;
    }

    public void run() {
        Gson g = new Gson();
        User user = g.fromJson(this.userData, User.class);
        try {
            this.myAuction.checkForUniqueUser(user.getId());
            this.myAuction.addUser(user);
        }catch (ItemAlreadyExistsException ie){
            System.out.println("There is an user with this id.Try again.");
        }
    }
}
