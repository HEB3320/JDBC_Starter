package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidation {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://54.145.11.232";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        RestAssured.authentication = basic("user", "user");
    }
    @AfterClass
    public static void tearDown() {
        RestAssured.reset();
    }
    @Test
    public void schemaTest() {

        get("/spartans/11")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }


}
