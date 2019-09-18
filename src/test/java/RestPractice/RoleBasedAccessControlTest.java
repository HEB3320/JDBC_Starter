package RestPractice;

import io.restassured.http.ContentType;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.is;

public class RoleBasedAccessControlTest extends TestBase {


    /*
     * Given public have not provided user credentials or token
     * When user try to send get request on /spartans/42
     * then user should get status code 401
     *
     * */

    @Test
    public void PublicUserRoleCanNotViewData_Test() {

        given()
                .log().all()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/117").
                then()
                .log().all()
                .statusCode(401);

    }

    /*
     * Given User provided user credentials for basic auth
     * When user try to send get request on /spartans/117
     * then user should get status code 200
     * and name should be "Stop Deleting "
     *
     * */
    @Test
    public void AuthenticatedUser_with_USERRoleCanViewData_Test() {

        String username = ConfigurationReader.getProperty("spartan.user_role.name");
        String password = ConfigurationReader.getProperty("spartan.user_role.password");


        given()
                // this is how we do basic auth authentication in RestAssured
                .auth().basic(username, password)
                .log().all()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/117").
                then()
                .log().all()
                .statusCode(200);

    }

    /*
     * Given User provided user credentials  USER role for basic auth
     * When user try to send put request on /spartans/117
     * then user should get status code 403
     * and response body name should be "Stop Deleting "
     * and body    "error" field should be "Forbidden",
     * */
    @Test
    public void AuthenticatedUser_with_Role_USER_CanNotUpdateData_Test() {

        String username = ConfigurationReader.getProperty("spartan.user_role.name");
        String password = ConfigurationReader.getProperty("spartan.user_role.password");

        given()
                // this is how we do basic auth authentication in RestAssured
                .auth().basic(username, password)
                .log().all()
                .pathParam("spartan_id", 117)
                .contentType(ContentType.JSON)
                .body(new Spartan("Asim", "Male", 987654321)).

                when()
                .put("/spartans/{spartan_id}").
                then()
                .statusCode(403)
                .body("error", is("Forbidden"))
        ;


    }


    /*
     * Given User provided user credentials  ADMIN role for basic auth
     * When user try to send put request on /spartans/117
     * then user should get status code 204
     * and we should get empty body
     * */
    @Test
    public void AuthenticatedUser_with_Role_ADMIN_CanUpdateData_Test() {

        String username = ConfigurationReader.getProperty("spartan.admin_role.name");
        String password = ConfigurationReader.getProperty("spartan.admin_role.password");

        given()
                // this is how we do basic auth authentication in RestAssured
                .auth().basic(username, password)
                .log().all()
                .pathParam("spartan_id", 117)
                .contentType(ContentType.JSON)
                .body(new Spartan("Asim", "Male", 987654321)).

                when()
                .put("/spartans/{spartan_id}").
                then()
                .log().all()
                .statusCode(204)
                .body(blankOrNullString())


        ;


    }


}

