import java.sql.*;

public class Main2 {


    public static void main(String[] args) throws SQLException {

        String connection_str = "jdbc:oracle:thin:@18.206.235.47:1521:xe"; // replace ip with your ip
        String db_user     = "hr";
        String db_password = "hr";

        Connection conn = DriverManager.getConnection(connection_str,db_user,db_password);

        //Statement stmt = conn.createStatement();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE
                                            ,ResultSet.CONCUR_UPDATABLE );

        ResultSet rs = stmt.executeQuery("SELECT * FROM COUNTRIES");

        while(rs.next()){
                System.out.println(rs.getObject("COUNTRY_ID")
                        + " "+ rs.getObject("COUNTRY_Name")
                        + " "+ rs.getObject("REGION_ID") );

        }
        System.out.println("-----------------");
        // what if we want to move the cursor back up to access previous row
        while(rs.previous()) {
            System.out.println(rs.getObject("COUNTRY_ID")
                    + " " + rs.getObject("COUNTRY_Name")
                    + " " + rs.getObject("REGION_ID"));
        }





    }


}
