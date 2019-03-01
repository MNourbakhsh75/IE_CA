package JoboonjaDB;


public class Skills {
    private String name;
    private Integer point;

    public Skills(String name,Integer point){
        this.name = name;
        this.point = point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getPoint() {
        return this.point;
    }

    public String getName() {
        return this.name;
    }

}
