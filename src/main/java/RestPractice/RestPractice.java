package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class RestPractice {


    @Test
    public void test1(){

        Response result = RestAssured.get("http://52.23.254.102:8000/api/hello");
        // this code will give you status code
        System.out.println(result.statusCode() );
        // this code will give you the body in String format
        System.out.println(result.asString() );
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());

        System.out.println(result.getHeader("content-type") );

        assertEquals(200, result.statusCode());
        assertEquals("Hello from Sparta", result.asString());
        // rest assured lib provide multiple method with same meaning
        // like getHeader = header   , getContentType = ContentType , statusCode = getStatusCode
        assertEquals("text/plain;charset=UTF-8", result.header("content-type") );





    }


}
