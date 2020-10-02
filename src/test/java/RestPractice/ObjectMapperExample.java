package RestPractice;

import java.io.File;
import java.io.IOException;
import  java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.fasterxml.jackson.*;

public class ObjectMapper {


   public void test()throws IOException {

       ObjectMapper mapper = new ObjectMapper();
Spartan spartan =mapper.readValue(new File("data.json"),Spartan.class);





   }















}
