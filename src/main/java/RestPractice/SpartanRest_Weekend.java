package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void test1(){

        //RequestSpecification reqSpec = given().accept(ContentType.JSON);
        Response response =
                // RequestSpecification
                given()
                    .accept(ContentType.JSON).
                when()
        // Actual request is being sent using HTTP verbs methods with URL
                    .get("/spartans") ;
        // eventually it will return a Response object

    }

    @Test
    public void test2(){

        //RequestSpecification reqSpec = given().accept(ContentType.JSON);
        Response response =
                // RequestSpecification
                given()
                        .accept(ContentType.JSON).
                        when()
                        // Actual request is being sent using HTTP verbs methods with URL
                        .get("/spartans/2") ;
        // eventually it will return a Response object
        response.prettyPrint();
        assertEquals( "Male" , response.path("gender").toString()     );


    }






}
