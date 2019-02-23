
import AuctionData.MyAuction;
import commands.*;
import AuctionData.*;
import javafx.util.Pair;

import java.net.URL;
import java.util.Scanner;
import HttpServer.*;
import Request.*;
//Client
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;
    private static MyAuction myAuction = new MyAuction();
    private static Instruction instruction;
    private static Request request = new Request();
    private static Server server;
    public static void main(String[] args) throws Exception{
        String getRes = request.getReq(new URL("http://142.93.134.194:8000/joboonja/project"));
        instruction = new AddProject(myAuction, getRes);
        instruction.run();
//        register
//        String userInfo = "{"id":"1","firstName":"علی","lastName":"شریف زاده","jobTitle":"برنامه نویس وب","profilePictureURL":"","bio":"روی سنگ قبرم بنویسید : خدا بیامرز میخواست خیلی کارا بکنه ولی پول نداشت","skills":[{"name":"HTML","point":5},{"name":"Javascript","point":4},{"name":"C++","point":2},{"name":"Java","point":3}]}";
        User user = new User();
        user.setBio("روی سنگ قبرم بنویسید : خدا بیامرز خیلی کارا میخواست بکنه ولی پول نداشت");
        user.setFirstName("علی");
        user.setLastName("شریف زاده");
        user.setId("1");
        user.setJobTitle("برنامه نویس وب");
        user.addSkill("HTML",5);
        user.addSkill("C++",2);
        user.addSkill("Javascript",4);
        user.addSkill("Java",3);
        myAuction.addUser(user);
        server = new Server(myAuction);
        server.startServer();
//        while (!isFinished) {
//            Pair<String, String> commandParts = getCommandParts();
//            if (commandParts != null) {
//                String commandName = commandParts.getKey();
//                String commandData = commandParts.getValue();
//                if (commandName.equals("register")) {
//                    instruction = new Register(myAuction, commandData);
//                } else if (commandName.equals("addProject")) {
//                    instruction = new AddProject(myAuction, commandData);
//                } else if (commandName.equals("bid")) {
//                    instruction = new commands.BidCommand(myAuction, commandData);
//                } else if (commandName.equals("auction")) {
////                    instruction = new commands.Auction(myAuction, commandData);
//                    isFinished = true;
//                } else {
//                    System.out.println("Unknown Command");
//                    continue;
//                }
//                instruction.run();
//            }else
//                System.out.println("Invalid input.");
//        }
    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        if(spaceIndex == -1){
            return null;
        }
        return new Pair<String, String>(command.substring(0, spaceIndex), command.substring(spaceIndex));
    }

}
