package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class DeserializingJsonToJavaObject {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.base_path");
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }


    @Test
    public void DeserializeAnJsonToObject_Test() {

        // FIRST WAY
        Spartan sp1 = get("/spartans/10")//.prettyPeek()
                .jsonPath()
                .getObject("", Spartan.class);
        System.out.println(sp1);

        // SECOND WAY

        Spartan sp2 = get("/spartans/15")//.prettyPeek()
                .as(Spartan.class);
        System.out.println(sp2);

    }

    @Test
    public void Add_NewSpartan_WithPojoAsBody_Test() {

        Spartan spartan = new Spartan("Myensulu", "Female", 1231231231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                when()
                .post("/spartans").
                then()
                .statusCode(201);

    }

}
