package RestPractice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ObjectMapperExample {

    @Test
    public void test() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Spartan spartan = mapper.readValue(new File("data.json"), Spartan.class);
        //   Object spartan = mapper.readValue(new File("data.json"),Object.class);
        System.out.println(spartan);


        File arrayFile = new File("all_data.json");

        //This will return list of LinkedHashmap
        List sps = mapper.readValue(arrayFile, List.class);
        sps.forEach(System.out::println);
// if you dont mention type of List it is linkedhashmap


        //this will return List of Spartan
        List<Spartan> allSpartans =
                mapper.readValue(arrayFile, new TypeReference<List<Spartan>>() {
                });
        System.out.println(allSpartans);
        System.out.println("Class type of first item in the list = " + allSpartans.get(0).getClass());


//write object to json
        System.out.println(mapper.writeValueAsString(spartan));

//----------write with pretty print

        String str1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(spartan);
        System.out.println(str1);


        // ------enable the pretty print globally
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String str1Pretty = mapper.writeValueAsString(spartan);
        System.out.println("str1Pretty = " + str1Pretty);


        //------write to new json file
//creates sp2 automatically from sp1
        mapper.writeValue(new File("sp3.json"), spartan);
    }


}
