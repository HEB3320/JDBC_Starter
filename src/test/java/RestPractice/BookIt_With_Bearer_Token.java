package RestPractice;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BookIt_With_Bearer_Token {


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
        //RestAssured.port = 8000;
        //RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void getTokenTest() {

        given()
                .queryParam("email", "emaynell8f@google.es")
                .queryParam("password", "besslebond").
                when()
                .get("/sign").
                then()
                .log().all()
                .statusCode(200)
                .body("accessToken", notNullValue()
                );

    }


    @Test
    public void getAllRoom_Bearer_Token_Test() {

        String newToken = generateTokenUtility();
        given()
                .header("Authorization", "Bearer " + newToken).
                when()
                .get("/api/rooms").
                then()
                .statusCode(200);


    }

    // BooKIT APP implements oath2 , so we can directly use oath2 access token
    // above approach will work for any oath2 based authentication and authorization
    @Test
    public void getAllRoom_oath2_Test() {


        String newToken = generateTokenUtility();
        given()
                .auth().oauth2(newToken).
                when()
                .get("/api/rooms").
                then()
                .statusCode(200);


    }


    public String generateTokenUtility() {

        String token = given()
                .queryParam("email", "emaynell8f@google.es")
                .queryParam("password", "besslebond").
                        when()
                .get("/sign")
                .jsonPath().getString("accessToken");

        return token;
    }


}
