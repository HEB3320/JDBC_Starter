package JDBC;

import utils.ConfigurationReader;

import java.sql.*;

public class JDBC_PreparedStatement {


    public static void main(String[] args) {


        String connection_str = ConfigurationReader.getProperty("oracledb.url");
        String db_user = ConfigurationReader.getProperty("oracledb.user");
        String db_password = ConfigurationReader.getProperty("oracledb.password");


        try {

            Connection conn = DriverManager.getConnection(connection_str, db_user, db_password);
            //Statement stmt = conn.createStatement();
//            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE
//                    , ResultSet.CONCUR_UPDATABLE);

            /// Prepared statement pre-compile your sql query ,
            // and query is part of the PreparedStatement object
            // and we have ability to parameterize the sql query  using question mark
            // and set it's value dynamically by using 1 based index
            // it's relatively faster and safer.
            String sql = "SELECT * FROM COUNTRIES " +
                      "WHERE COUNTRY_ID = ? AND REGION_ID = ? " ;
            PreparedStatement stmt = conn.prepareStatement(sql , ResultSet.TYPE_SCROLL_INSENSITIVE
                   , ResultSet.CONCUR_UPDATABLE);
            // because country ID in sql query is represented by String so we used setString method
            // if it was number we could use setInt for whole number or setDouble for fractional number
            // most of the time it will be obvious
            stmt.setString(1,"US");
            stmt.setInt(2,2);

            ResultSet rs = stmt.executeQuery();

            rs.next();
                System.out.println(rs.getObject("COUNTRY_ID")
                        + " "+ rs.getObject("COUNTRY_Name")
                        + " "+ rs.getObject("REGION_ID") );
            rs.close();
            stmt.close();
            conn.close();


        }catch (Exception e){
            System.out.println("error occured");
        }



    }


}
