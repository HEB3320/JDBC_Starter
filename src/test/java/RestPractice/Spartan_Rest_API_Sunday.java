package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Spartan_Rest_API_Sunday {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.base_path");
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void All_Spartan_With_Size_And_Items_Test(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").prettyPeek().
        then()
                .statusCode(200)
                .assertThat()
                .body("[0].name",equalTo("Nels"))
                //.body("name",hasSize(104))
                .body("[1].gender",is("Male"))
                .header("Transfer-Encoding","chunked")
                .header("Date", notNullValue() )


        ;


    }


}
