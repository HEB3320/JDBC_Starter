package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class MyStepdefs {

    RequestSpecification requestSpec ;
    Response response;

    @Before  // this is before annotation coming from cucumber
            //  it will let the method run right before the scenario !
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.base_path");;
        // this is how we can add basic auth for entire test
        //RestAssured.authentication = basic("user", "user");
    }

    @Given("credentials with USER_role is provided")
    public void credentials_with_USER_role_is_provided() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("given part");
        requestSpec = given()
                .auth().basic("user", "user")
                .log().all()
        ;
    }
    @When("user try to send get request on {string}")
    public void user_try_to_send_get_request_on(String path) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("when part");

        response = requestSpec.when()
                        .get(path);

    }

    @Then("user should get status code {int}")
    public void user_should_get_status_code(Integer statusCode) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("then part");
        response.then().statusCode(statusCode);
    }


    @After
    public void reset(){
        RestAssured.reset();
    }


}
