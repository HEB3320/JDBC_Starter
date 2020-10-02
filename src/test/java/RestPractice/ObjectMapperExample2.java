package RestPractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectMapperExample2 {


    @Test
    public void test() throws IOException {


        Spartan p1 = new Spartan("Jon","Male",1234567890);
        p1.setId(123);
        System.out.println("p1 = " + p1);

        ObjectMapper mapper1 = new ObjectMapper();
        //Pojo to json String
        String abc =mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(p1);
        System.out.println("abc = " + abc);

        //back to Pojo
       Spartan sp1 = mapper1.readValue(abc,Spartan.class);
        System.out.println("sp1 = " + sp1);

        System.out.println("----------------------------------");

        Map<String, Object> myDataMap =new LinkedHashMap<>();
        myDataMap.put("success","a spartan is born");
        myDataMap.put("data",p1);

        System.out.println("myDataMap = " + myDataMap);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonStr =mapper.writeValueAsString(myDataMap);
        System.out.println("jsonStr = " + jsonStr);


    }


}
