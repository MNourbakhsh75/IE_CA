import java.util.HashMap;

public class User {
    private String username;
    private HashMap<String,Integer> skills = new HashMap<String, Integer>();
    private Integer bidAmount;
    public User(String username){

        this.username = username;
    }
    public void addSkill(String name,Integer point){

        this.skills.put(name,point);
    }

    public String getUsername(){

        return this.username;
    }
    public HashMap<String,Integer> getSkills(){

        return this.skills;
    }
    public void setBidAmount(Integer bidAmount){

        this.bidAmount = bidAmount;
    }
    public Integer getBidAmount(){

        return this.bidAmount;
    }
}
