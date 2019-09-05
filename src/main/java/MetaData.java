import utils.ConfigurationReader;
import utils.DBType;
import utils.DBUtility;

import java.sql.*;

public class MetaData {

    public static void main(String[] args) throws SQLException{


        Connection conn = DriverManager.getConnection(ConfigurationReader.getProperty("oracledb.url"),
                ConfigurationReader.getProperty("oracledb.user"),
                ConfigurationReader.getProperty("oracledb.password"));

        String sql = "SELECT * FROM COUNTRIES " ;

        PreparedStatement stmt = conn.prepareStatement(sql , ResultSet.TYPE_SCROLL_INSENSITIVE
                , ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery();

        // how do we get row count
        // there is a method called getRow -->> this will return current row index starting from one
        // move the cursor to last row then get what is the row number
        rs.last();
        System.out.println(  rs.getRow()   );

        /*MetaData is data about the data
        *
        * DatabaseMetaData
        *
        * ResultSetMetaData
        *
        *
        * */
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println( "dbmd.getDatabaseProductName()  : "  + dbmd.getDatabaseProductName() );
        System.out.println( "dbmd.getUserName()  : "  + dbmd.getUserName() );

        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("rsmd.getColumnCount()  : "  +  rsmd.getColumnCount()   );
        System.out.println("rrsmd.getColumnName(2)  : "  +  rsmd.getColumnName(2)   );




//        rs.next();
//        System.out.println(rs.getObject("COUNTRY_ID")
//                + " "+ rs.getObject("COUNTRY_Name")
//                + " "+ rs.getObject("REGION_ID") );
        rs.close();
        stmt.close();
        conn.close();


    }



}
