package JobOonja.DataLayer.DBConnectionPool;



import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();
    private final static String dbURL = "jdbc:sqlite:C:/Users/Mehrdad/Desktop/edu/ie/ca/3/CA3/ca3/JobOonja.db";

    static {
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl(dbURL);
//        ds.setUsername("root");
//        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPool(){ }
}
