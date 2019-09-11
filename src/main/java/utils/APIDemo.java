package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class APIDemo {

    @Test
    public void statusCodeTest(){

       Response response = RestAssured.get("https://github.com/khalilinemat");
       int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("Status Code: " + statusCode);

    }
}
