package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;
import JobOonja.Entities.Bid;
import JobOonja.Entities.Project;
import JobOonja.Entities.Skills;
import JobOonja.Entities.User;
import JobOonja.Functions.SortByCDate;
import JobOonja.itemException.NotEnoughSkillsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import static JobOonja.DataLayer.DataMapper.UserMapper.getSingleUserFromDB;
import static JobOonja.Functions.Functions.checkForEnoughSkills;

public class ProjectMapper {

    public ProjectMapper() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        Statement st = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + "project" + " " + "(id TEXT PRIMARY KEY,"+
                "title TEXT,"+
                "description TEXT,"+
                "imageUrl TEXT,"+
                "deadline BIGINT,"+
                "budget INT,"+
                "creationDate BIGINT)";
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
        PreparedStatement statement = connection.prepareStatement(String.format("insert into project values(?,?,?,?,?,?,?)", "id", "title","description","imageUrl","deadline","budget","creationDate"));
        for(Project p : projects){
            statement.setString(1,p.getId());
            statement.setString(2,p.getTitle());
            statement.setString(3,p.getDescription());
            statement.setString(4,p.getImageUrl());
            statement.setLong(5,p.getDeadline());
            statement.setInt(6,p.getBudget());
            statement.setLong(7,p.getCreationDate());
            statement.executeUpdate();
        }

        statement.close();
        connection.close();
    }

    public static void insertToBidTable(String uid,String pid,Integer bidAmount) throws SQLException{
        Connection c = ConnectionPool.getConnection();
        PreparedStatement st = c.prepareStatement(String.format("INSERT OR REPLACE INTO bid VALUES (?,?,?)","projectId","userId","bidAmount"));
        st.setString(1,pid);
        st.setString(2,uid);
        st.setInt(3,bidAmount);
        st.executeUpdate();
        st.close();
        c.close();
    }

    public static void insertProjectSkillToDB(Project p) throws SQLException{
        Connection c = ConnectionPool.getConnection();
//        System.out.println(String.format("INSERT INTO projectSkill(projectId,skillName,point) SELECT ?,?,? WHERE NOT EXISTS(SELECT 1 FROM projectSkill WHERE projectId = ? AND skillName = ? AND point = ?) "));
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
        stat = connection.prepareStatement(String.format("SELECT * FROM bid b WHERE b.projectId = ?"));
        stat.setString(1,pid);
        ResultSet rs3 = stat.executeQuery();
        project = convertResultSetToObject(rs,rs2,rs3);
        stat.close();
        connection.close();
        return project;
    }

    public static ArrayList<Project> getAllProjectFromDB() throws SQLException{

        ArrayList<Project> projects = new ArrayList<>();
        User user = getSingleUserFromDB("1");
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT DISTINCT projectId FROM projectSkill"));
        ResultSet allIdSet = stat.executeQuery();
        while (allIdSet.next()) {
            stat = connection.prepareStatement(String.format("SELECT * FROM projectSkill p WHERE p.projectId = ?"));
            stat.setString(1,allIdSet.getString("projectId"));
            ResultSet singleIdSkillsSet = stat.executeQuery();
            ArrayList<Skills> projectSkill = new ArrayList<>();
            while (singleIdSkillsSet.next()){
                projectSkill.add(new Skills(singleIdSkillsSet.getString("skillName"),singleIdSkillsSet.getInt("point")));
            }
            try{
                checkForEnoughSkills(user.getSkills(),projectSkill);
//                System.out.println("khaa : " + allIdSet.getString("projectId"));
                Project p = getSingleProjectFromDB(allIdSet.getString("projectId"));
                projects.add(p);
                projectSkill.clear();
            }catch (NotEnoughSkillsException ne){
//                System.out.println(ne.getMessage());
            }
        }
        stat.close();
        connection.close();
//        Collections.sort(projects, new SortByCDate());
        return projects;
    }

    public static ArrayList<Project> searchBetweenProjects(String name) throws SQLException{

        ArrayList<Project> projects = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM project WHERE title = ? OR description = ?"));
        stat.setString(1,name);
        stat.setString(2,name);
        ResultSet rs = stat.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("title"));
            Project p = getSingleProjectFromDB(rs.getString("id"));
            projects.add(p);
        }
        stat.close();
        connection.close();
        System.out.println(projects.size());
        return projects;
    }

    private static Project convertResultSetToObject(ResultSet rs,ResultSet rs2,ResultSet rs3) throws SQLException {

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
        while (rs3.next()){
            User user = getSingleUserFromDB(rs3.getString("userId"));
            Bid bid = new Bid(user,rs3.getInt("bidAmount"));
            project.addBids(bid);
        }
        return project;
    }
}
