import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DBType;
import utils.DBUtility;

import java.util.*;


public class DB_Test {

    @BeforeClass
    public static void setUp(){

        DBUtility.establishConnection(DBType.ORACLE);

    }

    @Test
    public void test1(){


        System.out.println("checking 3rd region is Asia");
        List<Map<String,Object>> lstOfMap = DBUtility.runSQLQuery("SELECT * FROM REGIONS");
        Assert.assertEquals("Asia", lstOfMap.get(2).get("REGION_NAME")  );


    }

    @AfterClass
    public static void tearDown(){

        DBUtility.closeConnections();

    }


}
