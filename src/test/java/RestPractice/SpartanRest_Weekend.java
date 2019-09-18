package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class SpartanRest_Weekend {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://3.89.115.0";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void test1() {

        //RequestSpecification reqSpec = given().accept(ContentType.JSON);
        Response response =
                // RequestSpecification
                given()
                        .accept(ContentType.JSON).
                        when()
                        // Actual request is being sent using HTTP verbs methods with URL
                        .get("/spartans");
        // eventually it will return a Response object

    }

    @Test
    public void test2() {

        //RequestSpecification reqSpec = given().accept(ContentType.JSON);
        Response response =
                // RequestSpecification
                given()
                        .accept(ContentType.JSON).
                        when()
                        // Actual request is being sent using HTTP verbs methods with URL
                        .get("/spartans/2");
        // eventually it will return a Response object
        response.prettyPrint();
        assertEquals("Male", response.path("gender").toString());


    }


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


        System.out.println(response.path("pageable.sort.empty").toString());
        // jsonPath -->> just like xpath , it's for finding elements in json object / document

        boolean isEmpty = response.jsonPath().getBoolean("pageable.sort.empty");
        assertTrue("ASSERTION FOR EMPTY HAS FAILED", isEmpty);

        int totalElements = response.jsonPath().getInt("totalElements");
        System.out.println("totalElements is : " + totalElements);

        // find out the first guys phone number

        // task number 1
        // find out totalElement field from the response  , numberOfElements


    }

    @Test
    public void Search_By_Providing_JsonPath_Practice_For_Array() {

        Response response = given().
                accept(ContentType.JSON).
                queryParam("gender", "Male").
                //param("gender","Male").
                        when().
                        get("/spartans/search");

        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));

        long firstPhone = response.jsonPath().getLong("content[0].phone");
        System.out.println("first guy phone is " + firstPhone);

        // jsonPath for content return a json array
        // in order to get single json object we would use  content[indexnumber]
        // in order to get single field in that json obeject : content[indexnumber].fieldName
        // for example content[1].phone --> second items phone number
        // if we want to store entire phone as a List
        // we can use getList methods with jsonPath by taking out index
        // content.phone

        List<Long> phoneList = response.jsonPath().getList("content.phone");
        // get all the name in List of String
        System.out.println(phoneList);

        List<String> nameList = response.jsonPath().getList("content.name");
        System.out.println(nameList);

    }

    // get single Spartan as json response by calling /api/spartans/{id}
    // store the response inside a Map of String and Object
    // do some assertion expected value you already set
    @Test
    public void Single_Spartan_Map_Test() {

        Response response = given().pathParam("my_id", 3).get("/spartans/{my_id}");
        response.prettyPrint();
        Map<String, Object> myJsonMap = response.jsonPath().getMap("");
        System.out.println(myJsonMap.get("name"));
        System.out.println(myJsonMap.get("gender"));
        System.out.println(myJsonMap.get("phone"));

    }

    @Test
    public void All_Spartan_Map_Test() {

        Response response = get("/spartans");
        List<Map<String, Object>> allSpartans = response.jsonPath().getList("");

        System.out.println(allSpartans);
        for (Map<String, Object> each : allSpartans) {
            System.out.println(each);
        }

    }

    @Test
    public void Seach_All_Spartan_Map_Test() {

        Response response = given().
                accept(ContentType.JSON).
                queryParam("gender", "Male").
                //param("gender","Male").
                        when().
                        get("/spartans/search");

        List<Map<String, Object>> allSpartans = response.jsonPath().getList("content");
        for (Map<String, Object> each : allSpartans) {
            System.out.println(each);
        }
    }

}
