package RestPractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SelfStudy extends TestBase {


    @Test
    public void test1() throws IOException {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", "Kelam");
        map.put("gender", "Male");
        map.put("phone", 1234567890);

        ObjectMapper objectMapper = new ObjectMapper();

        String bec = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println("bec = " + bec);
        System.out.println(objectMapper.readValue(bec, Map.class));
        //  Spartan spartan = new Spartan("Malik","Male",1234567890);
        // String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(spartan);

        //    System.out.println("result = " + result);

        //   Spartan dsrlztn = objectMapper.readValue(result, Spartan.class);
        //  System.out.println("dsrlztn = " + dsrlztn);

    }


    @Test
    public void test2() {

        JsonPath response = given().baseUri("http://100.26.254.156:8000")
                .basePath("/api").accept(ContentType.JSON)
                .when()
                .get("/spartans/").jsonPath().prettyPeek();
        List list = response.getList("");
        System.out.println(list.size());


    }

    @Test
    public void test3() {

        Response response = given().baseUri("http://100.26.254.156:8000")
                .basePath("/api").accept(ContentType.JSON)
                .when()
                .get("/spartans");
//response.prettyPrint();
       System.out.print(response.path("name").toString());


          //    System.out.println(response.asString());

    }

    @Test
    public void test4() {

        with()

                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .param("gender", "male")
                .get("/spartans").prettyPeek().then().assertThat()
                .body("name", hasItem("Nels"))
                .body("[1].name", equalTo("Nels"))
                .body("[1].gender", is("Male"))
                .header("Date", notNullValue())
                .contentType(ContentType.JSON).statusCode(200);

    }

    @Test
    public void test5() {

        Response response = with().
                baseUri("http://100.26.254.156:8000/api")
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .param("gender", "Male")
                .get("/spartans");
        //  assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        String name = response.jsonPath().getString("[1].name");
        System.out.println(name);

    }

    @Test
    public void test6() {

        RequestSpecification requestSpecification = with().
                baseUri("http://100.26.254.156:8000/api")
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .param("gender", "Male");

        Response response = requestSpecification
                .get("/spartans");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertNull("Male exists", response.getBody().toString());
    }

    @Test
    public void test7() {
        Response response = with()
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON).auth().basic("admin", "admin")
                .param("gender", "Male")
                .get("/spartans");

        List list1 = response.jsonPath().getList("");

        list1.forEach(System.out::println);
        //   assertFalse(list1.toString().contains("Female"));
    }

    @Test
    public void Serilization() {

        SpartanPojo spartanPojo = new SpartanPojo("Yuko Madrin", "Male", 1234567890);

        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json").log().all()
                .body(spartanPojo).when().
                post("/spartans").prettyPrint();
    }

    @Test
    public void Deserilization() throws IOException {

        Object spartan =

                given().accept(ContentType.JSON).header("Content-Type", "application/json").
                        get("/spartans/3").as(Object.class);

        System.out.println(spartan);

        //Serilization
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(spartan);
        System.out.println(json);

        //Deserilization
        Object pojo = mapper.readValue(json, Object.class);
        System.out.println(pojo);
    }

    @Test
    @DisplayName("My Test")
    public void schemaCheck() {

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




}