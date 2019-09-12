package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpartanRest_Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://3.89.115.0";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

//    Given no headers are provided
//    When User send request to /Spartans
//    Then Reponse status code should be 200
//    and  header should have content Type / json
//    and  header should contains Date
    @Test
    public void Spartan_All_Test(){
        //Response response = RestAssured.get("/spartans/");
        Response response = get("/spartans/");
        assertEquals(200, response.statusCode());
        // below codes are doing same exact thing
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("application/json;charset=UTF-8",response.getContentType());
        assertEquals("application/json;charset=UTF-8",response.getHeader("content-type"));

        // checking whether one header exists
        // hasHeaderWithName is not case sensitive
        boolean hasDateHeader = response.getHeaders().hasHeaderWithName("date");
        assertTrue(hasDateHeader);
    }

//    Given no headers are provided
//    When User send request to /api/spartans/2
//    Then Response status code should be 200
//    and header should have content Type / JSON
//      and text Nels show up in response body
//   OPTIONAL  and json object id should be 2
    @Test
    public void SingleSpartanData_Test(){

        Response response = get("/spartans/2");

        System.out.println( response.asString()  );
        System.out.println( response.body().asString()  );
        // this will print result in formatted style
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(  response.asString().contains("Nels")    );


    }




}
