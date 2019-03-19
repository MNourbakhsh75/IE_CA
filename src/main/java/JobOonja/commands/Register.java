package JobOonja.commands;
import JobOonja.itemException.ItemAlreadyExistsException;
import JobOonja.JoboonjaDB.User;
import com.google.gson.*;

import static JobOonja.JoboonjaDB.JDB.accessDataBase;

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
