package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SendingPostRequests {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://3.89.115.0";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void Single_Spartan_LoggingAll_Detals_Test() {

        given()
                .pathParam("my_id", 3)
                .log().all(). // we can put log().all() to see all request information in console
                when()
                .get("/spartans/{my_id}").
                then()
                // we can put log().all() to see all response information in console
                // there are multiple option to see exactly when we want to see the log
                // in below example we only want to see the response log if any validation fails
                .log().ifValidationFails()
                .statusCode(200)
        ;

    }

    // Sending a post request
    @Test
    public void Add_NewSpartan_WithStringAsBody_Test(){

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \"Jon Snow\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 42189713412\n" +
                        "}").
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",equalToIgnoringCase("Jon Snow"))
                .body("data.phone",hasToString("42189713412"))


        ;

    }

    @Test
    public void Add_NewSpartan_WithMapAsBody_Test(){

        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("name","Ashraf");
        bodyMap.put("gender","Male");
        bodyMap.put("phone","42189713412");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(  bodyMap  ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",equalToIgnoringCase("Ashraf"))
                .body("data.phone",hasToString("42189713412"))

        ;

    }

    @Test
    public void Add_NewSpartan_WithPojoAsBody_Test(){

      Spartan spartan = new Spartan("Myensulu","Female",1231231231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(  spartan  ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",equalToIgnoringCase("Myensulu"))
                .body("data.phone",hasToString("1231231231"))

        ;

    }

    @Test
    public void Add_NewSpartan_negative_Test(){

        Spartan spartan = new Spartan("M","Female",1231231231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(  spartan  ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("error",is("Bad Request"))
                .body("errors[0].defaultMessage"
                        ,is("name should be at least 2 character and max 15 character"))


        ;

    }




}
