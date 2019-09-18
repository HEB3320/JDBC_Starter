package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestPractice {

    //ec2-3-83-246-142.compute-1.amazonaws.com

    // http://52.23.254.102:8000/api/hello
    /*
     * http://52.23.254.102 or this ec2-3-83-246-142.compute-1.amazonaws.com
     *           is your baseURL | Hostname
     * 8000 is the port for this particular
     * entry point | base path to your Rest API  ---    /api
     * eventually /hello is the resource you want to get
     * */
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://52.23.254.102";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void just_printing_out() {
        Response result = RestAssured.get("/hello");
        // this code will give you status code
        System.out.println(result.statusCode());
        // this code will give you the body in String format
        System.out.println(result.asString());
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());
        System.out.println(result.getHeader("content-type"));
    }

    @Test
    public void Hello_Endpoint_Test() {
        Response result = RestAssured.get("/hello");
        assertEquals(200, result.statusCode());
        assertEquals("Hello from Sparta", result.asString());
        // rest assured lib provide multiple method with same meaning
        // like getHeader = header   , getContentType = ContentType , statusCode = getStatusCode
        assertEquals("text/plain;charset=UTF-8", result.header("content-type"));
    }

//    Given no headers are provided
//    When User send request to /hello
//    Then Response status code should be 200
//    and  header should have content Type / text/plain
//    and header should contains Date       DO THIS !
//    and body should be "Hello from Sparta"
//    and Content-Length should be 17  !!!!

    @Test
    public void Hello_EndPoint_HeaderContains_Test() {

        // 1st approach to test header exists is by checking the value is null or not
        Response response = RestAssured.get("/hello");
//        String headerValue = response.getHeader("Date");
//        System.out.println(headerValue);
//        assertNotNull(headerValue);

        // 2nd approach : use existing method
        boolean dateHeaderExists = response.getHeaders().hasHeaderWithName("Date");
        assertTrue(dateHeaderExists);

        assertEquals("17", response.getHeader("content-length"));

    }


}
