package com.company;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class PlateuMapTest {

    @Before
    public void setUp(){
        PlateuMap.topRightX = 30;
        PlateuMap.topRightY = 30;
    }

    @Test
    public void testxValidationFail(){
        //when
        PlateuMap.validateAndModifyTopRightX(-1);

        assertEquals(30,  PlateuMap.topRightX );
    }

    @Test
    public void testxValidationPass(){


        //when
        PlateuMap.validateAndModifyTopRightX(100);

        assertEquals(100,  PlateuMap.topRightX );}


    @Test
    public void testyValidationFail(){

        //when
        PlateuMap.validateAndModifyTopRightY(-1);

        assertEquals(30,  PlateuMap.topRightY );
    }

    @Test
    public void testyValidationPass(){

        //when
        PlateuMap.validateAndModifyTopRightY(100);

        assertEquals(100,  PlateuMap.topRightY);
    }

}
