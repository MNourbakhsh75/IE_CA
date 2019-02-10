import java.util.HashMap;

public class Project {
    private String title;
    private HashMap<String,Integer> skills = new HashMap<String, Integer>();
    private Integer budget;

    public Project(String title){

        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void addSkill(String name,Integer point){

        this.skills.put(name,point);
    }
    public void setBudget(Integer budget){

        this.budget = budget;
    }
    public Integer getBudget(){

        return this.budget;
    }
    public HashMap<String,Integer> getSkills(){

        return this.skills;
    }
}
