package JobOonja.commands;
import JobOonja.itemException.ItemAlreadyExistsException;
import JobOonja.Entities.User;
import com.google.gson.*;

import static JobOonja.Entities.JDB.accessDataBase;

public class Register {


    private String userData;
    public Register(String userData){
        this.userData = userData;
    }

    public User run() {
        Gson g = new Gson();
        User user = g.fromJson(this.userData, User.class);
//        try {
//            accessDataBase().checkForUniqueUser(user.getId());
//            accessDataBase().addUser(user);
//        }catch (ItemAlreadyExistsException ie){
//            System.out.println("There is an user with this id.Try again.");
//        }
        return user;
    }
}
