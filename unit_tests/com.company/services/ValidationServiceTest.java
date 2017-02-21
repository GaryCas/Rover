package com.company.services;

import com.company.PlateuMap;
import com.company.Rover;
import org.junit.Test;
import services.ValidationService;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class ValidationServiceTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testxCoordinateValidation() throws Exception {
        // given
        PlateuMap.topRightX = 100;

        // when
        Rover r1 = new Rover();

        // then
        ValidationService.validateAndAddxCoordinate(r1, 300);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testxCoordinateValidation2() throws Exception {
        // given
        PlateuMap.topRightX = 10;

        // when
        Rover r1 = new Rover();

        // then
        ValidationService.validateAndAddxCoordinate(r1, 30);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testyCoordinateValidation2() throws Exception {
        // given
        PlateuMap.topRightY = 10;

        // when
        Rover r1 = new Rover();

        // then
        ValidationService.validateAndAddyCoordinate(r1, 30);

    }

    @Test
    public void testxCoordinateValidationSuccessful() throws Exception {
        // given
        PlateuMap.topRightX = 10;

        // when
        Rover r1 = new Rover(1, 1, "N");

        // then
        ValidationService.validateAndAddxCoordinate(r1, 2);
        assertTrue(r1.getxCoordinate() == 2);
    }

    @Test(expected = Exception.class)
    public void testOrientationValidation() throws Exception {
        // given

        // when
        Rover r1 = new Rover();

        // then
        ValidationService.validateAndAddOrientation(r1, "P");
    }

    @Test
    public void testOrientationValidationSuccessful() throws Exception {
        // given

        // when
        Rover r1 = new Rover();

        // then
        ValidationService.validateAndAddOrientation(r1, "S");


        assertTrue("The final orientation should have been S",
                r1.getOrientation()
                        .equalsIgnoreCase("S"));
    }

}
