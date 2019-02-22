package commands;
import AuctionData.*;

import java.util.HashMap;

public class Auction implements Instruction {

    private MyAuction myAuction;
    private String auctionData;
    private HashMap<String,Integer> weights = new HashMap<String, Integer>();
    public Auction(MyAuction myAuction, String auctionData){
        this.myAuction = myAuction;
        this.auctionData = auctionData;
    }


    public void run() {
//        JsonObject jsonObject = new JsonParser().parse(this.auctionData).getAsJsonObject();
//        String titleObject = jsonObject.get("projectTitle").getAsString();
//        AuctionData.Project auctionProject = this.myAuction.getProjectBaseOnTitle(titleObject);
//        ArrayList<AuctionData.User> bidingUsers = this.myAuction.getBiddingUser();
//        if (auctionProject != null && (!bidingUsers.isEmpty())){
//            HashMap<String, Integer> pSkills = auctionProject.getSkills();
//            for (AuctionData.User us : bidingUsers) {
//                HashMap<String, Integer> uSkills = us.getSkills();
//                Integer calculatedWeight = 0;
//                Integer skillsSum = 0;
//                for (String n : pSkills.keySet()) {
//                    Integer temp1 = (uSkills.get(n) - pSkills.get(n));
//                    Integer temp2 = temp1 * temp1 * 10000;
//                    skillsSum = skillsSum + temp2;
//                }
//                Integer offer = auctionProject.getBudget() - us.getBidAmount();
//                calculatedWeight = skillsSum + offer;
//                this.weights.put(us.getUsername(), calculatedWeight);
//            }
//            System.out.println("winner: " + findMaxWeight(this.weights));
//        }else
//            System.out.println("There is no biding user or project with this title.");
    }
    public static String findMaxWeight(HashMap<String,Integer> w){
        String username = null;
        Integer max = 0;
        for(String s : w.keySet()){
            if(w.get(s) >= max) {
                max = w.get(s);
                username = s;
            }
        }
        return username;
    }
}
