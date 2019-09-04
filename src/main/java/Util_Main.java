import utils.DBType;
import utils.DBUtility;

import java.util.List;
import java.util.Map;

public class Util_Main {

    public static void main(String[] args) {

        DBUtility.establishConnection(DBType.ORACLE);
        System.out.println(DBUtility.getRowsCount("select * from employees"));

        List<Map<String,Object>> rsLst = DBUtility.runSQLQuery("select * from regions");
        DBUtility.closeConnections();

        System.out.println(rsLst.toString());



    }


}
