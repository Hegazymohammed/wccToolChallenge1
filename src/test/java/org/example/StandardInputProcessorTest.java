package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StandardInputProcessorTest {

    @Test
   void main( ) {
        String Hello = "Hello World \n what do you want ".trim();
        String[]values= Arrays.stream(Hello.split("[\\s+]")).filter(s -> !s.isEmpty()||!s.equals("")).toArray(String[]::new);
        assertEquals(values[0],"Hello");
        System.out.println(values.length);
        for (String value : values) {
            System.out.println(  value  );
        }

        assertTrue(values.length==6);

    }

}