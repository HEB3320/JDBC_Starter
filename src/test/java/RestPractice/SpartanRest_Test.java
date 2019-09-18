package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

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
    public void Spartan_All_Test() {
        //Response response = RestAssured.get("/spartans/");
        Response response = get("/spartans/");
        assertEquals(200, response.statusCode());
        // below codes are doing same exact thing
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertEquals("application/json;charset=UTF-8", response.getHeader("content-type"));

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
    public void SingleSpartanData_Test() {

        //1st way
        //Response response = get("/spartans/2");
        //2nd way
        Response response =
                //given().pathParam("id",2).get("/spartans/{id}")   ;
                given().pathParam("id", 2).when().get("/spartans/{id}");

        System.out.println(response.asString());
        System.out.println(response.body().asString());
        // this will print result in formatted style
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.asString().contains("Nels"));

    }

    //    Given Accept header is provided as Json
//    When User send request to /api/spartans/2
//    Then Response status code should be 200
//    and header should have content Type / JSON
//    and json object id should be 2
    @Test
    public void SingleSpartanDataWithHeader_Test() {

        // RequestSpecification object hold the information about the request
        // like header , path variable , query parameters, body

        // Response is the object to store Response data

        // this is how we can path header to teh request
        Response response = given()
                //.header("accept","application/json")
                //.accept("application/json")
                .accept(ContentType.JSON)
                .when().get("/spartans/2");
        assertEquals("application/json;charset=UTF-8", response.contentType());


    }


    //    Given Accept header is provided as XML
//    When User send request to /api/spartans/2
//    Then Response status code should be 406
//
    @Test
    public void SingleSpartanDataWithHeader_XML_status_code_406_Test() {

        // RequestSpecification object hold the information about the request
        // like header , path variable , query parameters, body

        // Response is the object to store Response data

        // this is how we can path header to teh request
        Response response = given()
                //.header("accept","application/json")
                //.accept("application/json")
                .accept(ContentType.XML)
                .when().get("/spartans/2");

        System.out.println(response.statusLine());
        assertEquals(406, response.statusCode());

    }


    //    Given no header is provided
//    When User send request to /api/spartans/20000
//    Then Reponse status code should be 404
//    and header should have content Type / JSON
//    and response payload should contains "Spartan Not Found"
    @Test
    public void Invalid_Spartan_ID_should_return_404_Test() {

        Response response =  //get("/spartans/20000");
                given().pathParam("my_id", 20000).when().get("/spartans/{my_id}");

        response.prettyPrint();
        assertEquals(404, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.asString().contains("Spartan Not Found"));
    }

    /*
     *   Given accept header is json
     *   query parameters gender Male
     *   When User send request to /api/spartans/search
     *   Then Reponse status code should be 200
     *    and header should have content Type / JSON
     * */
    @Test
    public void Search_By_Providing_Query_Parameter() {

        Response response = given().
                accept(ContentType.JSON).
                queryParam("gender", "Male").
                //param("gender","Male").
                        when().
                        get("/spartans/search");

        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));
        response.prettyPrint();


        System.out.println(response.path("pageable.sort.sorted").toString());


    }


    @Test
    public void SingleSpartanData_Json_FieldValue_Test() {

        Response response =
                //given().pathParam("id",2).get("/spartans/{id}")   ;
                given().pathParam("id", 2).when().get("/spartans/{id}");
        response.prettyPrint();

        System.out.println(response.path("name").toString());
        System.out.println(response.path("phone").toString());

        assertEquals("Nels", response.path("name").toString());

    }
}
