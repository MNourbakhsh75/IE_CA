package commands;
import AuctionData.*;
import com.google.gson.*;
import java.util.HashMap;

public class BidCommand implements Instruction {

    private MyAuction myAuction;
    private String biddingData;

    public BidCommand(MyAuction myAuction, String biddingData){
        this.myAuction = myAuction;
        this.biddingData = biddingData;
    }

    public void run() {

//        Gson gson = new Gson();
//        JsonObject bidObject = gson.fromJson(this.biddingData,JsonObject.class);
//        System.out.println(bidObject);
//        JsonObject jsonObject = new JsonParser().parse(this.biddingData).getAsJsonObject();
//        String bidingUsername = jsonObject.get("biddingUser").getAsString();
//        String bidingTitle = jsonObject.get("projectTitle").getAsString();
//        Integer bidingAmount = jsonObject.get("bidAmount").getAsInt();
//        AuctionData.Project bidingProject = this.myAuction.getProjectBaseOnTitle(bidingTitle);
//        AuctionData.User bidingUser = this.myAuction.getUserBaseOnUsername(bidingUsername);
//        if (bidingProject != null && bidingUser != null){
//            HashMap<String, Integer> pjSkills = bidingProject.getSkills();
//            HashMap<String, Integer> usSkills = bidingUser.getSkills();
//            if (checkForEnoughSkills(pjSkills, usSkills) && checkForProperBudget(bidingProject.getBudget(), bidingAmount)) {
//                bidingUser.setBidAmount(bidingAmount);
//                this.myAuction.addBiddingUser(bidingUser);
//            }
//        }else
//            System.out.println("There is no user with this username or project with this title.");

    }
//    private static Boolean checkForEnoughSkills(HashMap<String,Integer> pj,HashMap<String,Integer> us){
//        Boolean isEnough = true;
//        for(String key : pj.keySet()){
//            if(us.containsKey(key)){
//                if(pj.get(key) > us.get(key))
//                    isEnough = false;
//            }else
//                isEnough = false;
//        }
//        return isEnough;
//    }

    private static Boolean checkForProperBudget (Integer pb,Integer ub){
        Boolean isProper = true;
        if(ub > pb)
            isProper = false;
        return isProper;
    }
}
