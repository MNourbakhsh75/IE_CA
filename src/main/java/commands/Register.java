package commands;
import JoboonjaDB.User;
import com.google.gson.*;

import itemException.*;

import static JoboonjaDB.JDB.accessDataBase;

public class Register implements Instruction {


    private String userData;
    public Register(String userData){
        this.userData = userData;
    }

    public void run() {
        Gson g = new Gson();
        User user = g.fromJson(this.userData, User.class);
        try {
            accessDataBase().checkForUniqueUser(user.getId());
            accessDataBase().addUser(user);
        }catch (ItemAlreadyExistsException ie){
            System.out.println("There is an user with this id.Try again.");
        }
    }
}
