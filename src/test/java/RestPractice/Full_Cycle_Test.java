package RestPractice;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Full_Cycle_Test extends TestBase{


    @Test
    public void getSpartan_Test(){


        createRandomSpartan();

    }


    public int createRandomSpartan(){
        Faker faker = new Faker();


        int randomIndex = faker.number().numberBetween(0,2);
        String[] genders = {"Male","Female"};
        String randomGender = genders[randomIndex];

        Spartan spartan = new Spartan(faker.name().firstName(),
                            "Male",
                            Long.parseLong(faker.number().digits(10) ) );


       return given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(spartan).
               when()
                    .post("/spartans")
                    .jsonPath()
                    .getInt("data.id") ;

    }




}




