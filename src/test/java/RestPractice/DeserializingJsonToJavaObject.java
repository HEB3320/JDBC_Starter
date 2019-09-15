package RestPractice;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

        Spartan sp1 = get("/spartans/10")
                        .jsonPath()
                        .getObject("",Spartan.class) ;
        System.out.println(sp1);


    }



}