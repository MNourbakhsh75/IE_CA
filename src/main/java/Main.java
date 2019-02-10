import javafx.util.Pair;
import java.util.Scanner;
//Client
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;
    private static MyAuction myAuction = new MyAuction();
    private static Instruction instruction;
    public static void main(String[] args) {

        while (!isFinished) {
            Pair<String, String> commandParts = getCommandParts();
            String commandName = commandParts.getKey();
            String commandData = commandParts.getValue();
            if (commandName.equals("register")){
                instruction = new Register(myAuction,commandData);
            }else if (commandName.equals("addProject")){
                instruction = new AddProject(myAuction,commandData);
            }else if (commandName.equals("bid")){
                instruction = new Bid(myAuction,commandData);
            }else if(commandName.equals("auction")){

                isFinished = true;
            }
            instruction.run();
        }

    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        return new Pair<String, String>(command.substring(0, spaceIndex), command.substring(spaceIndex));
    }

}
