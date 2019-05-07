package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;
import JobOonja.Entities.Skills;
import JobOonja.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserMapper {

    public UserMapper() throws SQLException{
        Connection con = ConnectionPool.getConnection();
        Statement st = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + "user" + " " + "(id TEXT PRIMARY KEY,"+
                "firstName TEXT,"+
                "lastName TEXT,"+
                "jobTitle TEXT,"+
                "profilePictureURL TEXT,"+
                "bio TEXT);";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "userSkill" + " " + "(userId TEXT ,skillName Text ,point INT,PRIMARY KEY (userId,skillName),FOREIGN KEY(skillName) REFERENCES skill(name),FOREIGN KEY(userId) REFERENCES user(id));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "endorsement" + " " + "(endorserId TEXT,endorsedId TEXT,skillName Text,PRIMARY KEY (endorserId,endorsedId,skillName),FOREIGN KEY(skillName) REFERENCES skill(name),FOREIGN KEY(endorserId) REFERENCES user(id),FOREIGN KEY(endorsedId) REFERENCES user(id));";
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public static void insertUserToDB(User u) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("insert into user values(?,?,?,?,?,?)", "id", "firstName","lastName","jobTitle","profilePictureURL","bio"));
        statement.setString(1,u.getId());
        statement.setString(2,u.getFirstName());
        statement.setString(3,u.getLastName());
        statement.setString(4,u.getJobTitle());
        statement.setString(5,u.getProfilePictureURL());
        statement.setString(6,u.getBio());
        statement.executeUpdate();

        statement = connection.prepareStatement(String.format("insert into userSkill values(?,?,?)", "userId", "skillName","point"));
        for (Skills s : u.getSkills()){
            statement.setString(1,u.getId());
            statement.setString(2,s.getName());
            statement.setInt(3,s.getPoint());
            statement.addBatch();
        }
        connection.setAutoCommit(false);
        statement.executeBatch();
        connection.setAutoCommit(true);
        statement.close();
        connection.close();

    }

    public static User getSingleUserFromDB(String uid) throws SQLException{
        User user = new User();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM user u WHERE u.id = ?"));
        stat.setString(1,uid);
        ResultSet rs = stat.executeQuery();
        System.out.println(rs.getString("firstName"));
        stat = connection.prepareStatement(String.format("SELECT * FROM userSkill u WHERE u.userId = ?"));
        stat.setString(1,uid);
        ResultSet rs2 = stat.executeQuery();
        user = convertResultSetToObject(rs,rs2);
        stat.close();
        connection.close();
        return user;
    }

    public static ArrayList<User> getAllUserFromDB() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(String.format("SELECT * FROM user"));
        System.out.println(rs.getString("firstName"));
        while (rs.next()){
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM userSkill u WHERE u.userId = ?"));
            statement.setString(1,rs.getString("id"));
            ResultSet rs2 = statement.executeQuery();
            User user = convertResultSetToObject(rs,rs2);
            users.add(user);
        }
        stat.close();
        connection.close();
        return users;
    }

    public static void addNewSkillToUser(String uid,String name) throws SQLException{

        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("insert into userSkill values(?,?,?)", "userId", "skillName","point"));
        statement.setString(1,uid);
        statement.setString(2,name);
        statement.setInt(3,0);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void deleteUserSkill(String uid,String name) throws SQLException{

        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("DELETE FROM userSkill  WHERE userId = ? AND skillName = ?"));
        statement.setString(1,uid);
        statement.setString(2,name);
        statement.executeUpdate();
        statement.close();
        connection.close();

    }

    public static HashMap<String,ArrayList<String>> gerEndorsedUserSkill(String uid) throws Exception{
        HashMap<String,ArrayList<String>> ednorsedSkill = new HashMap<>();

        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM endorsement e  WHERE e.endorserId = ?"));
        statement.setString(1,uid);
        ResultSet resultSet = statement.executeQuery();
        System.out.println("fdffdffddfgfdhgf");
//        if(!resultSet.next()) {
//            statement.close();
//            connection.close();
//            return ednorsedSkill;
//        }
        while (resultSet.next()){
            System.out.println("fdffdf: "+resultSet.getString("endorsedId"));
            statement = connection.prepareStatement(String.format("SELECT * FROM endorsement e WHERE e.endorsedId = ?"));
            statement.setString(1,resultSet.getString("endorsedId"));
            ResultSet rs = statement.executeQuery();
            ArrayList<String> endorsedSkillName = new ArrayList<>();
            while (rs.next()){
                endorsedSkillName.add(rs.getString("skillName"));
            }
            System.out.println("sizeee11: "+endorsedSkillName.size());
            ednorsedSkill.put(resultSet.getString("endorsedId"),endorsedSkillName);
//            endorsedSkillName.clear();
        }
        statement.close();
        connection.close();
//        System.out.println("sizeee: "+ednorsedSkill.get("2").size());
//        for(String s : ednorsedSkill.get("2"))
//            System.out.println("fdffdf333: "+s);
        return ednorsedSkill;
    }

    public static void endorseUserSkill(String endorsedId,String endorserId,String name) throws SQLException {

        Connection connection = ConnectionPool.getConnection();
        System.out.println(String.format("UPDATE userSkill SET point = point + 1 WHERE userId = "+endorsedId+" AND skillName = "+name+";"));
        PreparedStatement statement = connection.prepareStatement(String.format("UPDATE userSkill SET point = point + 1 WHERE userId = ? AND skillName= ?;"));
        statement.setString(1,endorsedId);
        statement.setString(2,name);
        statement.executeUpdate();
        statement = connection.prepareStatement(String.format("insert into endorsement values(?,?,?)", "endorserId","endorsedId", "skillName"));
        statement.setString(1,endorserId);
        statement.setString(2,endorsedId);
        statement.setString(3,name);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static ArrayList<User> searchBetweenUsers(String name) throws SQLException{

        ArrayList<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM user WHERE firstName = ? OR lastName = ?"));
        stat.setString(1,name);
        stat.setString(2,name);
        ResultSet rs = stat.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("firstName"));
            User u = getSingleUserFromDB(rs.getString("id"));
            users.add(u);
        }
        stat.close();
        connection.close();
        return users;
    }

    private static User convertResultSetToObject(ResultSet resultSet,ResultSet resultSet2) throws SQLException{
        User user = new User();
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setBio(resultSet.getString("bio"));
        user.setJobTitle(resultSet.getString("jobTitle"));
        user.setProfilePictureURL(resultSet.getString("profilePictureURL"));
        user.setId(resultSet.getString("id"));
        while (resultSet2.next()){
            user.addSkill(resultSet2.getString("skillName"),resultSet2.getInt("point"));
        }
        return user;
    }
}
