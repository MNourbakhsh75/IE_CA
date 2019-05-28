package JobOonja.DataLayer.DataMapper;

import JobOonja.DataLayer.DBConnectionPool.ConnectionPool;
import JobOonja.Entities.Skills;
import JobOonja.Entities.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserMapper {

    public UserMapper() throws SQLException{
        Connection con = ConnectionPool.getConnection();
        Statement st = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + "user" + " " + "(userName VARCHAR(255) PRIMARY KEY,"+
                "firstName TEXT,"+
                "lastName TEXT,"+
                "jobTitle TEXT,"+
                "profilePictureURL TEXT,"+
                "bio TEXT);";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "users" + " " + "(userName VARCHAR(255) PRIMARY KEY,password Text,FOREIGN KEY(userName) REFERENCES user(userName));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "userSkill" + " " + "(userName VARCHAR(255) ,skillName VARCHAR(255) ,point INT,PRIMARY KEY (userName,skillName),FOREIGN KEY(skillName) REFERENCES skill(name),FOREIGN KEY(userName) REFERENCES user(userName));";
        st.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS " + "endorsement" + " " + "(endorserId VARCHAR(255),endorsedId VARCHAR(255),skillName VARCHAR(255),PRIMARY KEY (endorserId,endorsedId,skillName),FOREIGN KEY(skillName) REFERENCES userSkill(skillName),FOREIGN KEY(endorserId) REFERENCES user(userName),FOREIGN KEY(endorsedId) REFERENCES userSkill(userName));";
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public static void insertUserToDB(User u) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("insert into user values(?,?,?,?,?,?)", "userName", "firstName","lastName","jobTitle","profilePictureURL","bio"));
        statement.setString(1,u.getUserName());
        statement.setString(2,u.getFirstName());
        statement.setString(3,u.getLastName());
        statement.setString(4,u.getJobTitle());
        statement.setString(5,u.getProfilePictureURL());
        statement.setString(6,u.getBio());
        statement.executeUpdate();

        statement = connection.prepareStatement(String.format("insert into users values(?,?)", "userName", "password"));
        statement.setString(1,u.getUserName());
        statement.setString(2,getMd5(u.getPassword()));
        statement.executeUpdate();
        statement = connection.prepareStatement(String.format("insert into userSkill values(?,?,?)", "userName", "skillName","point"));
        for (Skills s : u.getSkills()){
            statement.setString(1,u.getUserName());
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

    public static String loggedInUser(String userName,String password) throws SQLException{

        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM users u WHERE u.userName = ? AND u.password = ?"));
        stat.setString(1,userName);
        stat.setString(2,getMd5(password));
        String res = null;
        ResultSet resultSet = stat.executeQuery();
        if(resultSet.next()){
            res =  resultSet.getString("userName");
        }
        stat.close();
        connection.close();
        return res;
    }

    public static Boolean checkForUniqueUserName(String userName) throws SQLException{
        Boolean res = false;
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM users u WHERE u.userName = ?"));
        stat.setString(1,userName);
        ResultSet resultSet = stat.executeQuery();
        if(!resultSet.next())
            res = true;
        stat.close();
        connection.close();
        return res;
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static User getSingleUserFromDB(String uid) throws SQLException{
        User user = new User();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement(String.format("SELECT * FROM user u WHERE u.userName = ?"));
        stat.setString(1,uid);
        ResultSet rs = stat.executeQuery();
        if(rs.next())
            System.out.println(rs.getString("firstName"));
        stat = connection.prepareStatement(String.format("SELECT * FROM userSkill u WHERE u.userName = ?"));
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
//        System.out.println(rs.getString("firstName"));
        while (rs.next()){
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM userSkill u WHERE u.userName = ?"));
            statement.setString(1,rs.getString("userName"));
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
        PreparedStatement statement = connection.prepareStatement(String.format("insert into userSkill values(?,?,?)", "userName", "skillName","point"));
        statement.setString(1,uid);
        statement.setString(2,name);
        statement.setInt(3,0);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void deleteUserSkill(String uid,String name) throws SQLException{

        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(String.format("DELETE FROM userSkill  WHERE userName = ? AND skillName = ?"));
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
        System.out.println(String.format("UPDATE userSkill SET point = point + 1 WHERE userName = "+endorsedId+" AND skillName = "+name+";"));
        PreparedStatement statement = connection.prepareStatement(String.format("UPDATE userSkill SET point = point + 1 WHERE userName = ? AND skillName= ?;"));
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

    public static ArrayList<User> searchBetweenUsers(String userName,String name) throws SQLException{

        ArrayList<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement stat = connection.prepareStatement("SELECT * FROM user WHERE firstName LIKE ? OR lastName LIKE ?");
        stat.setString(1,"%" + name + "%");
        stat.setString(2,"%"+name+"%");
        System.out.println("fdff");

        ResultSet rs = stat.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("firstName"));
            if(!rs.getString("userName").equals(userName)) {
                User u = getSingleUserFromDB(rs.getString("userName"));
                users.add(u);
            }
        }
        stat.close();
        connection.close();
        return users;
    }

    private static User convertResultSetToObject(ResultSet resultSet,ResultSet resultSet2) throws SQLException{
        User user = new User();
        System.out.println(resultSet);
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setBio(resultSet.getString("bio"));
            user.setJobTitle(resultSet.getString("jobTitle"));
            user.setProfilePictureURL(resultSet.getString("profilePictureURL"));
            user.setUserName(resultSet.getString("userName"));
        while (resultSet2.next()){
            user.addSkill(resultSet2.getString("skillName"),resultSet2.getInt("point"));
        }
        return user;
    }
}
