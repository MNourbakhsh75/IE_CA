package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

public class SkillsMapper {


    public SkillsMapper() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        Statement st = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + "skill" + " " + "(id TEXT PRIMARY KEY, name TEXT);";
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public void insertSkill(ArrayList<String> skills) throws SQLException{
        System.out.println("insert");
        Connection connection = ConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(String.format("insert into skill values(?, ?)", "id", "name"));
            for(int i = 1; i <= skills.size(); i++){
//                System.out.println(skills.get(i-1));
                statement.setString(1, Integer.toString(i));
                statement.setString(2, skills.get(i-1));
                statement.addBatch();
            }
            connection.setAutoCommit(false);
            statement.executeBatch();
            connection.setAutoCommit(true);
            statement.close();
        }catch (SQLException s){
            System.out.println(s);
        }
        connection.close();
    }

    public static ArrayList<String> getAllSkillsFromDB() throws SQLException{
        ArrayList<String> allSkills = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery("select * from skill;");
        while (rs.next()) {
            allSkills.add(rs.getString("name"));
        }
        System.out.println(allSkills);
        stat.close();
        connection.close();
        return allSkills;
    }
}
