package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class HR_ORDS_REST_API_Test {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://54.145.11.232"; // your own IP goes here
        port = 1000;
        basePath = "/ords/hr";
        // above will generate a BASE REQUEST URL OF http://54.145.11.232:1000/ords/hr/regions
    }

    @Test
    public void test_regions(){

        Response response = get("/regions");
        response.prettyPrint();

    }

    @AfterClass
    public static void tearDown(){
        // this will reset all the set up we made to avoid accidental collusion between different test class
        RestAssured.reset();
    }


}
