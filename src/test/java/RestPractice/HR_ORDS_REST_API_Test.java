package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class HR_ORDS_REST_API_Test {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://54.145.11.232"; // your own IP goes here
        port = 1000;
        basePath = "/ords/hr";
        // above will generate a BASE REQUEST URL OF http://54.145.11.232:1000/ords/hr/regions
    }

    @Test
    public void test_all_regions() {

        Response response = get("/regions");
        //response.prettyPrint();

        String first_regionName = response.jsonPath().getString("items[0].region_name");
        System.out.println(first_regionName);

        //String all_regionName = response.jsonPath().getString("items.region_name");
        List<String> all_regionName = response.jsonPath().getList("items.region_name");

        System.out.println(all_regionName);

        // get all the href field from the regions
        String all_regionlinks = response.jsonPath()
                .getString("items[1].links[0].href");
        System.out.println(all_regionlinks);


        List<String> all_regionlinksList = response.jsonPath()
                .getList("items.links.href");

        // TASK JSON PATH TASK ,
        // FIND OUT THE LAST LINKS ON TOP LEVEL
        // ASSERT THE rel value is first
        String lastLinksRel = response.jsonPath().getString("links[3].rel");
        assertEquals("first", lastLinksRel);

        System.out.println(all_regionlinksList);

        assertEquals(200, response.statusCode());

    }

    @Test
    public void test_single_Region() {

        Response response = given().pathParam("my_id", 1)
                .get("/regions/{my_id}");

        response.prettyPrint();

        Map<String, Object> myJsonMap = response.jsonPath().getMap("");
        System.out.println(myJsonMap.get("region_name"));
        System.out.println(myJsonMap.get("links"));

    }

    @AfterClass
    public static void tearDown() {
        // this will reset all the set up we made to avoid accidental collusion between different test class
        RestAssured.reset();
    }


}



