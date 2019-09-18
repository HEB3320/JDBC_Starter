package RestPractice;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import utils.ConfigurationReader;

public class TestBase {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.base_path");
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @AfterClass
    public static void tearDown() {
        RestAssured.reset();
    }
}
