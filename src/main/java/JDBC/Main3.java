package JDBC;

import java.sql.*;

public class Main3 {


    public static void main(String[] args) throws SQLException {

        String connection_str = "jdbc:oracle:thin:@18.206.235.47:1521:xe"; // replace ip with your ip
        String db_user = "hr";
        String db_password = "hr";

        Connection conn = DriverManager.getConnection(connection_str, db_user, db_password);

        //Statement stmt = conn.createStatement();
        //ResultSet.TYPE_SCROLL_INSENSITIVE enable us to create ResultSet that we can move forward and backward
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE
                , ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery("SELECT * FROM COUNTRIES");
        // move cursor to a row that you specified
        rs.absolute(2);
        System.out.println(rs.getObject("COUNTRY_ID")
                + " " + rs.getObject("COUNTRY_Name")
                + " " + rs.getObject("REGION_ID"));

        rs.first(); // move the cursor to first row
        System.out.println(rs.getObject("COUNTRY_ID")
                + " " + rs.getObject("COUNTRY_Name")
                + " " + rs.getObject("REGION_ID"));
        rs.last(); // move the cursor to last row
        System.out.println(rs.getObject("COUNTRY_ID")
                + " " + rs.getObject("COUNTRY_Name")
                + " " + rs.getObject("REGION_ID"));

 //       rs.beforeFirst(); // move the cursor to the location right before first row
//        System.out.println(rs.getObject("COUNTRY_ID")
//                + " " + rs.getObject("COUNTRY_Name")
//                + " " + rs.getObject("REGION_ID"));

//        rs.afterLast(); // move the cursor to the location right after last row
//        System.out.println(rs.getObject("COUNTRY_ID")
//                + " " + rs.getObject("COUNTRY_Name")
//                + " " + rs.getObject("REGION_ID"));


        //rs.first();
        rs.beforeFirst();
        while(rs.next()){
            System.out.println(rs.getObject("COUNTRY_ID")
                    + " "+ rs.getObject("COUNTRY_Name")
                    + " "+ rs.getObject("REGION_ID") );

        }













    }

}
