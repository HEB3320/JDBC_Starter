package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SendingPostRequests {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://54.146.86.143";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void Single_Spartan_LoggingAll_Details_Test() {

        given()
                .pathParam("my_id", 3)
                .log().all(). // we can put log().all() to see all request information in console
                when()
                .get("/spartans/{my_id}").
                then()
                // we can put log().all() to see all response information in console
                // there are multiple option to see exactly when we want to see the log
                // in below example we only want to see the response log if any validation fails
                .log().ifValidationFails()
                .statusCode(200)
        ;

    }

    // Sending a post request
    @Test
    public void Add_NewSpartan_WithStringAsBody_Test() {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \"Jon Snow\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 42189713412\n" +
                        "}").
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", equalToIgnoringCase("Jon Snow"))
                .body("data.phone", hasToString("42189713412"))


        ;

    }

    @Test
    public void Add_NewSpartan_WithMapAsBody_Test() {

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", "Ashraf");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", "42189713412");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", equalToIgnoringCase("Ashraf"))
                .body("data.phone", hasToString("42189713412"))

        ;

    }

//    @Test
//    public void Add_NewSpartan_WithPojoAsBody_Test() {
//
//        Spartan spartan = new Spartan("Myensulu", "Female", 1231231231);
//
//        given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .body(spartan).
//                when()
//                .post("/spartans").
//                then()
//                .log().all()
//                .statusCode(201)
//                .contentType(ContentType.JSON)
//                .body("success", is("A Spartan is Born!"))
//                .body("data.name", equalToIgnoringCase("Myensulu"))
//                .body("data.phone", hasToString("1231231231"))
//
//        ;
//
//    }

    @Test
    public void Add_NewSpartan_With_ExternalFile_Test() {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/data.json")).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", equalToIgnoringCase("Brady"))
                .body("data.phone", hasToString("67413853202"))

        ;

    }

//    @Test
//    public void Add_NewSpartan_negative_Test() {
//
//        Spartan spartan = new Spartan("M", "Female", 1231231231);
//
//        given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .body(spartan).
//                when()
//                .post("/spartans").
//                then()
//                .log().all()
//                .assertThat()
//                .statusCode(400)
//                .body("error", is("Bad Request"))
//                .body("errors[0].defaultMessage"
//                        , is("name should be at least 2 character and max 15 character"))
//
//        ;
//
//    }
//
//    @Test
//    public void Add_NewSpartan_negativeNameGenderPhone_Test() {
//
////        Spartan spartan = new Spartan("M", "F", 123);
//
//        given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .body(spartan).
//                when()
//                .post("/spartans").
//                then()
//                .log().all()
//                .assertThat()
//                .statusCode(400)
//                .body("error", is("Bad Request"))
////                .body("errors.defaultMessage"
////                        ,hasItem("name should be at least 2 character and max 15 character"))
////                .body("errors.defaultMessage"
////                                ,hasItem("Gender should be either Male or Female")
//                .body("errors.defaultMessage", hasSize(3))
//                .body("errors.defaultMessage",
//                        hasItems("Gender should be either Male or Female"
//                                , "Phone number should be at least 10 digit and UNIQUE!!"
//                                , "name should be at least 2 character and max 15 character"))
//                .body("message", containsString("Error count: 3"))
//        ;
//
//    }

    @Test
    public void selfTest5() {


        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Mary Lee");
        data.put("gender", "Female");
        data.put("phone", 1456426132);


        given().contentType(ContentType.JSON).body(data)
                .when().post("/spartans").then().log().all().statusCode(HttpStatus.SC_CREATED)
                .body("success", equalTo("A Spartan is Born!")).header("Content-Type", "application/json")
                .body("data.phone", hasToString("1456426132"));

    }

    @Test
    public void selfTest6() {

        SpartanPojo pojo = new SpartanPojo("Steven Fishback", "Male", 1234567890);//lombok

        given().body(pojo).contentType(ContentType.JSON).log().all()
                .when().post("/spartans")
                .then().statusCode(HttpStatus.SC_CREATED).log().all()
                .body("data.name", is("Steven Fishback"));

    }

    @Test
    public void selfLombokPojoToJson() {

        SpartanPojo pojo = new SpartanPojo("Steven Fishback", "Male", 1234567890);

        given().body(pojo).contentType(ContentType.JSON).log().all()
                .when().post("/spartans")
                .then().statusCode(HttpStatus.SC_CREATED).log().all()
                .body("data.name", is("Steven Fishback"));

    }

    @Test
    public void selfJsonToPojo() {

        Spartan[] response =

                given().accept(ContentType.JSON).get("/spartans")
                        .as(Spartan[].class);

        System.out.println(response.length);

        System.out.println("response[0].getName() = " + response[0].getName());
    }

    @Test
    public void selfJsonToPojo2() {

        List<String> list =

                given().accept(ContentType.JSON).get("/spartans")
                        .jsonPath().getList("");

        System.out.println(list.size());
        System.out.println("list of names are = " + list);

    }


    @Test
    public void testSelfNews() {
        JsonPath jsonPath =
                given().accept(ContentType.JSON)
                        //.log().all()
                        .baseUri("http://newsapi.org")
                        .basePath("/v2")
                        .param("country", "us")
                        .param("category", "business")
                        .param("apiKey", "371ba171643c4ca0bb366e506e217bf2")
                        .get("/top-headlines")
                        .then()
                     //   .log().all()
                        .extract()
                        .jsonPath();
String author = jsonPath.getString("articles[0].author");
String name = jsonPath.getString("articles[0].source.name");
        List<String> authors = jsonPath.getList("articles.author");
        System.out.println(author);
        System.out.println(name);
        System.out.println(authors);


        NewsPojo newsPojo =new NewsPojo("Samson X Horne","TribLIVE");
        System.out.println(newsPojo);
       NewsPojo n1 =  jsonPath.getObject("articles[0]",NewsPojo.class);
        System.out.println(n1);


        //get entire article as a list

        List<NewsPojo> artcLst = jsonPath.getList("articles",NewsPojo.class);
      //  System.out.println(artcLst);
        artcLst.forEach(System.out::println);






    }


}