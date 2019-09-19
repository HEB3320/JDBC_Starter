package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidation {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.base_path");;
        // this is how we can add basic auth for entire test
        RestAssured.authentication = basic("user", "user");
    }

    /*
    * Given user with valid credentials provided
    * when user send get request to /spartans/{id}
    * then the response json format should match the schema SingleSpartanSchema.json
    *
    * */
    @Test
    public void SingleSpartanResponse_JsonSchema_Test() {
        given()
                .log().all().
        when()
            .get("/spartans/11")
        .then()
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }

    /*
     * Given user with valid credentials provided
     * when user send get request to /spartans
     * then the response json format should match the schema SpartanArraySchema.json
     *
     * */
    @Test
    public void AllSpartanResponse_JsonSchema_Test() {

        given()
                .log().all()
            .contentType(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SpartanArraySchema.json"));

    }

    /*
     * Given user with valid credentials provided
     * when user send get request to /spartans/search
     * then the response json format should match the schema SearchResultSchema.json
     *
     * */
    @Test
    public void SearchSpartanResponse_JsonSchema_Test() {

        given()
                .log().all()
                .queryParam("gender", "female").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SearchResultSchema.json"));

    }


    @AfterClass
    public static void tearDown() {
        RestAssured.reset();
    }


}
