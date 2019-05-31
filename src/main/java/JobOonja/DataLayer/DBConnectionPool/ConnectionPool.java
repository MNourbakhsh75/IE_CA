package JobOonja.DataLayer.DBConnectionPool;



import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();
    private final static String dbURL = "jdbc:mysql://127.0.0.1:3333/joboonja99";

    static {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(dbURL);
//        ds.setUsername("root");
//        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("eeee: "+e);
        }
        System.out.println("test database5");
        return DriverManager.getConnection("jdbc:mysql://mysql-database:3306/test?allowPublicKeyRetrieval=true&useSSL=false","root","root");
//        Connection c = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        try {
//            String url = "jdbc:mysql://localhost:3333/joboonjadb";
//            c = DriverManager.getConnection(url, "root", "");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return c;
    }

    private ConnectionPool(){ }
}
