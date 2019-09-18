package RestPractice;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Full_Cycle_Test extends TestBase {


    @Test
    public void getSpartanAndDelete_Test() {
        // calling utility to create new data
        int spartanID = createRandomSpartan();
        System.out.println(spartanID);

        //  updating the data using put request
        given()
                .log().all()
                .pathParam("id", spartanID)
                .body(new Spartan("Zeynep", "Female", 1231231231)).
                when()
                .put("/spartans/{id}", spartanID).
                then()
                .log().all()
                .statusCode(204)
        ;

        ///   users/1/horses/5
        ///  users/{varName} -->>
        ///  users/:varName -->>

        //  verifying the updated data using get request
        given()
                .log().all().
                //.pathParam("id",spartanID).
                        when()
                .get("/spartans/{id}", spartanID). // another way to pass --> path pathParam
                then()
                .assertThat()
                .and()
                .statusCode(200)
                .and()
                .body("id", is(spartanID))
                .body("name", is("Zeynep"))
                .body("gender", is("Female"))
                .body("phone", hasToString("1231231231"))
                .and()
                .header("content-type", is("application/json;charset=UTF-8"))

        ;
        // deleting the data after verification
        given()
                .log().all().
                when()
                .delete("/spartans/" + spartanID).
                then()
                .log().all()
                .statusCode(204)
                // do not provide json path for empty body
                .body(blankOrNullString())
        ;

        // making sure the deleted data does not exists
        given()
                .log().all().
                when()
                .get("/spartans/" + spartanID).
                then()
                .statusCode(404)
        ;

    }

    /*
    This method is creating random Spartan data by using Post request
    It use faker to get random name , gender , phone number
    eventually return the id of new resource created in the server
     */
    public int createRandomSpartan() {

        Faker faker = new Faker();

        int randomIndex = faker.number().numberBetween(0, 2);
        String[] genders = {"Male", "Female"};
        String randomGender = genders[randomIndex];

        Spartan spartan = new Spartan(faker.name().firstName(),
                randomGender,
                Long.parseLong(faker.number().digits(11)));


        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                        when()
                .post("/spartans/")
                .prettyPeek()
                .jsonPath()
                .getInt("data.id");

    }

}
