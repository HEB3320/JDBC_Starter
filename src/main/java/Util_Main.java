import utils.DBType;
import utils.DBUtility;

import java.util.List;
import java.util.Map;

public class Util_Main {

    public static void main(String[] args) {

        DBUtility.establishConnection(DBType.ORACLE);

        System.out.println(DBUtility.getRowsCount("select * from employees"));

        List<Map<String,Object>> rsLst = DBUtility.runSQLQuery("select * from employees");

        // PRINT OUT ALL THE EMPLOYEE NAME AND SALARY , JUST BY PASSING "select * from employees"
        // TO THE UTILITY
        // NAME IS  BLA BLA , SALARY IS 6 DIGIT





        DBUtility.closeConnections();







    }


}
