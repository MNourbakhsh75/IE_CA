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
        String sql = "CREATE TABLE IF NOT EXISTS " + "project" + " " + "(id VARCHAR(255) PRIMARY KEY,"+
                "title TEXT,"+
                "description TEXT,"+
                "imageUrl TEXT,"+
                "deadline BIGINT,"+
                "budget INT,"+
                "auction INT ,"+
                "creationDate BIGINT)";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "projectSkill" + " " + "(projectId VARCHAR(255) ,skillName VARCHAR(255) ,point INT,PRIMARY KEY (projectId,skillName),FOREIGN KEY(skillName) REFERENCES skill(name),FOREIGN KEY(projectId) REFERENCES project(id));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "bid" + " " + "(projectId VARCHAR(255) ,userName VARCHAR(255) ,bidAmount INT,PRIMARY KEY (projectId,userName),FOREIGN KEY(projectId) REFERENCES project(id),FOREIGN KEY(userName) REFERENCES user(userName));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "winners" + " " + "(projectId VARCHAR(255) ,userName VARCHAR(255),PRIMARY KEY (projectId,userName),FOREIGN KEY(projectId) REFERENCES project(id),FOREIGN KEY(userName) REFERENCES user(userName));";
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public static void insertProjecsToDB(ArrayList<Project> projects) throws SQLException{

        System.out.println(projects.get(0).getTitle());
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("insert into project values(?,?,?,?,?,?,?,?)", "id", "title","description","imageUrl","deadline","budget","auction","creationDate"));
        for(Project p : projects){
            statement.setString(1,p.getId());
            statement.setString(2,p.getTitle());
            statement.setString(3,p.getDescription());
            statement.setString(4,p.getImageUrl());
            statement.setLong(5,p.getDeadline());
            statement.setInt(6,p.getBudget());
            statement.setInt(7,0);
            statement.setLong(8,p.getCreationDate());
            statement.executeUpdate();
        }

        statement.close();
        connection.close();
    }

    public static void insertToBidTable(String uid,String pid,Integer bidAmount) throws SQLException{
        Connection c = ConnectionPool.getConnection();
        PreparedStatement st = c.prepareStatement(String.format("INSERT INTO bid VALUES (?,?,?)","projectId","userName","bidAmount"));
        st.setString(1,pid);
        st.setString(2,uid);
        st.setInt(3,bidAmount);
        st.executeUpdate();
        st.close();
        c.close();
    }

    public static ArrayList<Project> getAllExpiredProject(Timestamp timestamp) throws SQLException{
        Connection c = ConnectionPool.getConnection();
        ArrayList<Project> projects = new ArrayList<>();
        PreparedStatement st = c.prepareStatement("SELECT * FROM project p WHERE p.deadline < ? AND p.auction = ?");
        st.setLong(1,timestamp.getTime());
        st.setLong(2,0);
        ResultSet resultSet = st.executeQuery();
        while(resultSet.next()){
            Project p = getSingleProjectFromDB(resultSet.getString("id"));
            projects.add(p);
        }
        st.close();
        c.close();
        return projects;
    }

    public static void insertProjectSkillToDB(Project p) throws SQLException{
        Connection c = ConnectionPool.getConnection();
//        System.out.println(String.format("INSERT INTO projectSkill(projectId,skillName,point) SELECT ?,?,? WHERE NOT EXISTS(SELECT 1 FROM projectSkill WHERE projectId = ? AND skillName = ? AND point = ?) "));
        PreparedStatement st = c.prepareStatement(String.format("INSERT INTO projectSkill VALUES (?,?,?)","projectId","skillName","point"));
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
        PreparedStatement stat1 = connection.prepareStatement(String.format("SELECT * FROM project p WHERE p.id = ?"));
        stat1.setString(1,pid);
        ResultSet rs = stat1.executeQuery();
//        System.out.println(rs.getString("title"));
        PreparedStatement stat2 = connection.prepareStatement(String.format("SELECT * FROM projectSkill p WHERE p.projectId = ?"));
        stat2.setString(1,pid);
        ResultSet rs2 = stat2.executeQuery();
        PreparedStatement stat3 = connection.prepareStatement(String.format("SELECT * FROM bid b WHERE b.projectId = ?"));
        stat3.setString(1,pid);
        ResultSet rs3 = stat3.executeQuery();
        PreparedStatement stat4 = connection.prepareStatement(String.format("SELECT * FROM winners w WHERE w.projectId = ?"));
        stat4.setString(1,pid);
        ResultSet rs4 = stat4.executeQuery();
//        System.out.println("RS4");
//        while (rs4.next()){
//            System.out.println("rs4 winner");
//        }
        project = convertResultSetToObject(rs,rs2,rs3,rs4);
        System.out.println(project.getTitle());
        stat1.close();
        stat2.close();
        stat3.close();
        connection.close();
        return project;
    }

    public static void doneAuction(String pid,String userName,Boolean flag) throws SQLException{
        System.out.println("doneAuction userName: "+userName);
        System.out.println("doneAuction pid: "+pid);
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat2 = connection.prepareStatement(String.format("UPDATE project SET auction = 1 WHERE id = ?"));
        stat2.setString(1,pid);
        stat2.executeUpdate();
        if(flag) {
            PreparedStatement stat1 = connection.prepareStatement(String.format("INSERT INTO winners VALUES (?,?)", "projectId", "userName"));
            stat1.setString(1, pid);
            stat1.setString(2, userName);
            stat1.executeUpdate();
            stat1.close();
        }
//        stat1.close();
        stat2.close();
        connection.close();
    }

    public static ArrayList<Project> getAllProjectFromDB(String userName) throws SQLException{

        ArrayList<Project> projects = new ArrayList<>();
        User user = getSingleUserFromDB(userName);
//        System.out.println("userrr : "+user.getFirstName());
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT DISTINCT projectId FROM projectSkill"));
        ResultSet allIdSet = stat.executeQuery();
        while (allIdSet.next()) {
//            System.out.println(allIdSet.getString("projectId"));
            stat = connection.prepareStatement(String.format("SELECT * FROM projectSkill p WHERE p.projectId = ?"));
            stat.setString(1,allIdSet.getString("projectId"));
            ResultSet singleIdSkillsSet = stat.executeQuery();
            ArrayList<Skills> projectSkill = new ArrayList<>();
            while (singleIdSkillsSet.next()){
                projectSkill.add(new Skills(singleIdSkillsSet.getString("skillName"),singleIdSkillsSet.getInt("point")));
            }
//            System.out.println("GETALLPDB");
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
        return projects;
    }

    public static ArrayList<Project> searchBetweenProjects(String name) throws SQLException{

        ArrayList<Project> projects = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement("SELECT * FROM project WHERE title LIKE ? OR description LIKE ?");
        stat.setString(1,"%" + name + "%");
        stat.setString(2,"%" + name + "%");
        ResultSet rs = stat.executeQuery();
        while (rs.next()){
//            System.out.println(rs.getString("title"));
            Project p = getSingleProjectFromDB(rs.getString("id"));
            projects.add(p);
        }
        stat.close();
        connection.close();
        System.out.println(projects.size());
        return projects;
    }

    private static Project convertResultSetToObject(ResultSet rs,ResultSet rs2,ResultSet rs3,ResultSet rs4) throws SQLException {

        rs.next();
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
            User user = getSingleUserFromDB(rs3.getString("userName"));
            Bid bid = new Bid(user,rs3.getInt("bidAmount"));
            project.addBids(bid);
        }
        while (rs4.next()){
            User user = getSingleUserFromDB(rs4.getString("userName"));
            project.setWinner(user);
        }
        return project;
    }
}
