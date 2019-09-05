import utils.ConfigurationReader;
import utils.DBType;
import utils.DBUtility;

// IN DATABASE , ALL INDEXES START FROM 1 !!

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

        /* MetaData is data about the data
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

        //-------ResultSetMetaData will provide more information about resultset object we got

        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("rsmd.getColumnCount()  : "  +  rsmd.getColumnCount()   );
        System.out.println("rsmd.getColumnName(2)  : "  +  rsmd.getColumnName(2)   );

        System.out.println("------------------\n");
        // list all the column name from the query result you got
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {

            System.out.print(rsmd.getColumnName(i)+" | ");

        }


//        rs.next();
//        System.out.println(rs.getObject("COUNTRY_ID")
//                + " "+ rs.getObject("COUNTRY_Name")
//                + " "+ rs.getObject("REGION_ID") );
        rs.close();
        stmt.close();
        conn.close();


    }



}
