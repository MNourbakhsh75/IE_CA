package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;
import JobOonja.Entities.Project;
import JobOonja.Entities.Skills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProjectMapper {

    public ProjectMapper() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        Statement st = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + "project" + " " + "(id TEXT PRIMARY KEY,"+
                "title TEXT,"+
                "description TEXT,"+
                "imageUrl TEXT,"+
                "deadline BIGINT,"+
                "budget INT);";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "projectSkill" + " " + "(projectId TEXT ,skillName TEXT ,point INT,PRIMARY KEY (projectId,skillName),FOREIGN KEY(skillName) REFERENCES skill(name),FOREIGN KEY(projectId) REFERENCES project(id));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "bid" + " " + "(projectId TEXT ,userId Text ,bidAmount INT,PRIMARY KEY (projectId,userId),FOREIGN KEY(projectId) REFERENCES project(id),FOREIGN KEY(userId) REFERENCES user(id));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "winners" + " " + "(projectId TEXT ,userId Text,PRIMARY KEY (projectId,userId),FOREIGN KEY(projectId) REFERENCES project(id),FOREIGN KEY(userId) REFERENCES user(id));";
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public static void insertProjecsToDB(ArrayList<Project> projects) throws SQLException{

//        System.out.println(projects.get(0).getTitle());
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("insert into project values(?,?,?,?,?,?)", "id", "title","description","imageUrl","deadline","budget"));
        for(Project p : projects){
            statement.setString(1,p.getId());
            statement.setString(2,p.getTitle());
            statement.setString(3,p.getDescription());
            statement.setString(4,p.getImageUrl());
            statement.setLong(5,p.getDeadline());
            statement.setInt(6,p.getBudget());
            statement.executeUpdate();
        }

        statement.close();
        connection.close();
    }

    public static void insertProjectSkillToDB(Project p) throws SQLException{
        Connection c = ConnectionPool.getConnection();
        PreparedStatement st = c.prepareStatement(String.format("insert into projectSkill values(?,?,?)", "projectId", "skillName","point"));
        System.out.println(p.getTitle());
        for(Skills s: p.getSkills()){
            st.setString(1,p.getId());
            st.setString(2,s.getName());
            st.setInt(3,s.getPoint());
            st.addBatch();
        }
        c.setAutoCommit(false);
        st.executeBatch();
        c.setAutoCommit(true);
        st.close();
        c.close();
    }
}
