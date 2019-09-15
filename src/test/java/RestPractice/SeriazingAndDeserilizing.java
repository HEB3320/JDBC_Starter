package RestPractice;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SeriazingAndDeserilizing {

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
