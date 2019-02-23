
import AuctionData.MyAuction;
import commands.*;
import AuctionData.*;
import javafx.util.Pair;

import java.net.URL;
import java.util.Scanner;
import HttpServer.*;
import Request.*;

import static Functions.Functions.addStaticUser;

//Client
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;
    private static MyAuction myAuction = new MyAuction();
    private static Instruction instruction;
    private static Request request = new Request();
    private static Server server;
    public static void main(String[] args) throws Exception{
        instruction = new Register(myAuction,addStaticUser());
        instruction.run();
        String getProjects = request.getReq(new URL("http://142.93.134.194:8000/joboonja/project"));
        instruction = new AddProject(myAuction, getProjects);
        instruction.run();
        String getSkills = request.getReq(new URL("http://142.93.134.194:8000/joboonja/skill "));
        instruction = new AddSkills(myAuction,getSkills);
        instruction.run();
        server = new Server(myAuction);
        server.startServer();
        while (!isFinished) {
            Pair<String, String> commandParts = getCommandParts();
            if (commandParts != null) {
                String commandName = commandParts.getKey();
                String commandData = commandParts.getValue();
                if (commandName.equals("register")) {
                    instruction = new Register(myAuction, commandData);
                } else if (commandName.equals("addProject")) {
                    instruction = new AddProject(myAuction, commandData);
                } else if (commandName.equals("bid")) {
                    instruction = new commands.BidCommand(myAuction, commandData);
                } else if (commandName.equals("auction")) {
                    instruction = new commands.Auction(myAuction, commandData);
                    isFinished = true;
                } else {
                    System.out.println("Unknown Command");
                    continue;
                }
                instruction.run();
            }else
                System.out.println("Invalid input.");
        }
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
