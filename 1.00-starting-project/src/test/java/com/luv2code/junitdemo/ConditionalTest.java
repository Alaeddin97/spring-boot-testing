package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Disabled("Disabled this test until JIRA #123 is fixed")
    @Test
    void basicTest(){
        //execute basic test
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testForWindowsOnly(){
        //execute basic test
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testForMACOnly(){
        //execute basic test
    }

    @EnabledOnJre(JRE.JAVA_17)
    @Test
    void testForJRE17(){
        //execute basic test
    }

    @EnabledOnJre(JRE.JAVA_13)
    @Test
    void testForJRE13(){
        //execute basic test
    }

    @EnabledForJreRange(min = JRE.JAVA_18)
    @Test
    void testForJRERange(){
        //execute basic test
    }

    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV",matches = "DEV")
    @Test
    void testOnlyForEnvironmentDev(){
        //execute basic test
    }

    @EnabledIfSystemProperty(named = "LUV2COE_SYSTEM_PROP",matches = "CI_CD_DEPLOY")
    @Test
    void testOnlyForSystemProp(){
        //execute basic test
    }
}
