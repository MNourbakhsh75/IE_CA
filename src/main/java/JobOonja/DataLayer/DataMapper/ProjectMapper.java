package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;
import JobOonja.Entities.Project;
import JobOonja.Entities.Skills;
import JobOonja.Entities.User;

import java.sql.*;
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

        System.out.println(projects.get(0).getTitle());
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
        System.out.println(String.format("INSERT INTO projectSkill(projectId,skillName,point) SELECT ?,?,? WHERE NOT EXISTS(SELECT 1 FROM projectSkill WHERE projectId = ? AND skillName = ? AND point = ?) "));
        PreparedStatement st = c.prepareStatement(String.format("INSERT OR REPLACE INTO projectSkill VALUES (?,?,?)","projectId","skillName","point"));
        System.out.println(p.getTitle());
        for(Skills s: p.getSkills()){
            st.setString(1,p.getId());
            st.setString(2,s.getName());
            st.setInt(3,s.getPoint());
            st.executeUpdate();
        }
//        c.setAutoCommit(false);
//        st.executeBatch();
//        c.setAutoCommit(true);
        st.close();
        c.close();
    }

    public static Project getSingleProjectFromDB(String pid) throws SQLException{

        Project project = new Project();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM project p WHERE p.id = ?"));
        stat.setString(1,pid);
        ResultSet rs = stat.executeQuery();
        System.out.println(rs.getString("title"));
        stat = connection.prepareStatement(String.format("SELECT * FROM projectSkill p WHERE p.projectId = ?"));
        stat.setString(1,pid);
        ResultSet rs2 = stat.executeQuery();
        project = convertResultSetToObject(rs,rs2);
        stat.close();
        connection.close();
        return project;
    }

    private static Project convertResultSetToObject(ResultSet rs,ResultSet rs2) throws SQLException {

        Project project = new Project();
        project.setId(rs.getString("id"));
        project.setTitle(rs.getString("title"));
        project.setBudget(rs.getInt("budget"));
        project.setDeadline(rs.getLong("deadline"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("imageUrl"));
        while (rs2.next()){
            project.addSkill(rs2.getString("skillName"),rs2.getInt("point"));
        }
        return project;
    }
}
