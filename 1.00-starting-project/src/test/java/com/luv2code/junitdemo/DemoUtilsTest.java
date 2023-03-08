package com.luv2code.junitdemo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach(){
        demoUtils=new DemoUtils();
        //System.out.println("execute @beforeEach testing method ..............................");
    }

    //    @Order(1)
    @Test
    @DisplayName("Equals and not equals")
    void testEqualsAndNotEquals(){
        assertEquals(6,demoUtils.add(2,4),"2+4 must be 6");
        assertNotEquals(6,demoUtils.add(1,9),"1+9 must not be 6");
        //System.out.println("execute equalsNotEquals testing method...............................");
    }

    //    @Order(-1)
    @Test
    @DisplayName("Null and not null")
    void testNullAndNotNull(){
        //String str1=null;
        String str2="luv2code";
        assertNull(demoUtils.checkNull(null),"this object should be null");
        assertNotNull(demoUtils.checkNull(str2),"this object should not be null");
        //System.out.println("execute testNullAndNotNull testing method...............................");
    }

    @Test
    @DisplayName("Same and not same")
    void testSameAndNotSame(){
        String str="luv2code";
        assertSame(demoUtils.getAcademy(),demoUtils.getAcademyDuplicate(),"these objects should be the same");
        assertNotSame(demoUtils.getAcademy(),str,"these objects should not be the same");
    }

    @Test
    @DisplayName("True and false")
    void testTrueAndFalse(){
        assertTrue(demoUtils.isGreater(3,2),"this should be true");
        assertFalse(demoUtils.isGreater(1,5),"this should be false");
    }

    @Test
    @DisplayName("Arrays equals")
    void arraysEquals(){
        String[] arrayString={"A","B","C"};
        assertArrayEquals(arrayString,demoUtils.getFirstThreeLettersOfAlphabet(),"arrays should be the same");
    }

    @Test
    @DisplayName("Iterable equals")
    void iterableEquals(){
        List<String>list=new ArrayList<>(Arrays.asList("luv","2","code"));
        assertIterableEquals(list,demoUtils.getAcademyInList(),"lists should be the same");
    }

    @Test
    @DisplayName("Lines match")
    void linesMatch(){
        List<String>lines=List.of("luv","2","code");
        assertLinesMatch(lines,demoUtils.getAcademyInList(),"lines should be the same");
    }

    @Test
    @DisplayName("Throws exception")
    void throwException(){
        assertThrows(Exception.class,()->demoUtils.throwException(-1),"this should throw exception");
        assertDoesNotThrow(()->{demoUtils.throwException(1);},"this should not throw exception");
    }

    @Test
    @DisplayName("Timeout")
    void testTimeout(){
        assertTimeout(Duration.ofSeconds(3),()->demoUtils.checkTimeout(),
                "this should execute in 3 seconds ");
    }

    @Test
    @DisplayName("Multiply")
    void testMultiply(){
        assertEquals(demoUtils.multiply(2,3),6,"2*3=5, This should fail");
    }









    /*
      @BeforeAll
    static void printBeforeAll(){
        System.out.println("execute this method @BeforeAll testing methods.................");
        System.out.println();
    }
    @AfterAll
    static void printAfterAll(){
        System.out.println();
        System.out.println("execute this method @AfterAll testing methods................... ");
    }


    @AfterEach
    void executeAfterEach(){
        System.out.println("execute this method @AfterEach testing method ....................");
        System.out.println();
    }
    * */
}
