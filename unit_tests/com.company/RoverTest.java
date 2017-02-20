package com.company;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class RoverTest {

    @Before
    public void setUp(){
        PlateuMap.topRightX = 100;
        PlateuMap.topRightY = 100;
    }

    @Test
    public void testDefaultConstructor(){
        // given

        // when
        Rover R1 = new Rover();

        // then
        assertTrue("Expected the default value for position to be north, but it was "
                        + R1.getOrientation(),
                R1.getOrientation().equalsIgnoreCase("N"));

        assertEquals(
                "Expected the default value for yCoordinate to be 0, but it was "
                        + R1.getyCoordinate(),
                0,
                R1.getyCoordinate());

        assertEquals("Expected the default value for xCoordinate to be north, but it was "
                        + R1.getxCoordinate(),
                0,
                R1.getxCoordinate());
    }

    @Test
    public void testCustumConstructor(){
        // given

        // when
        Rover R1 = new Rover(3, 3, "S");

        // then
        assertTrue("Expected the default value for position to be north, but it was "
                        + R1.getOrientation(),
                R1.getOrientation().equalsIgnoreCase("S"));

        assertEquals(
                "Expected the default value for yCoordinate to be 3, but it was "
                        + R1.getyCoordinate(),
                3,
                R1.getyCoordinate());

        assertEquals("Expected the default value for xCoordinate to be 3, but it was "
                        + R1.getxCoordinate(),
                3,
                R1.getxCoordinate()
        );
    }

    @Test(expected = Exception.class)
    public void testxCoordinateValidation() throws Exception {
        // given
        PlateuMap.topRightX = 100;

        // when
        Rover r1 = new Rover();

        // then
        r1.validateAndAddxCoordinate(300);
    }

    @Test(expected = Exception.class)
    public void testxCoordinateValidation2() throws Exception {
        // given
        PlateuMap.topRightX = 10;

        // when
        Rover r1 = new Rover();

        // then
        r1.validateAndAddxCoordinate(30);
    }

    @Test(expected = Exception.class)
    public void testyCoordinateValidation2() throws Exception {
        // given
        PlateuMap.topRightY = 10;

        // when
        Rover r1 = new Rover();

        // then
        r1.validateAndAddyCoordinate(30);

    }

    @Test
    public void testxCoordinateValidationSuccessful() throws Exception {
        // given
        PlateuMap.topRightX = 10;

        // when
        Rover r1 = new Rover(1, 1, "N");

        // then
        r1.validateAndAddxCoordinate(2);
        assertTrue(r1.getxCoordinate() == 2);
    }

    @Test(expected = Exception.class)
    public void testOrientationValidation() throws Exception {
        // given

        // when
        Rover r1 = new Rover();

        // then
        r1.validateAndAddOrientation("P");
    }

    @Test
    public void testOrientationValidationSuccessful() throws Exception {
        // given

        // when
        Rover r1 = new Rover();

        // then
        r1.validateAndAddOrientation("S");


        assertTrue("The final orientation should have been S",
                r1.getOrientation()
                .equalsIgnoreCase("S"));
    }


    @Test
    public void testMoveNorth() throws Exception {
        // given
        Rover r1 = new Rover();
        assertEquals("N", r1.getOrientation());

        // when
        r1.move();

        // then
        assertEquals( "the x coordinate should have stayed 0",
                0,
                r1.getxCoordinate() );

        assertEquals( "the y coordinate should become 1",
                1,
                r1.getyCoordinate() );
    }

    // technically integration test
    @Test (expected = Exception.class)
    public void testMoveNorthFail() throws Exception {
        // given
        Rover r1 = new Rover(99, 99, "N");
        assertEquals("N", r1.getOrientation());

        // when
        r1.move();

        // then
        assertEquals( "the x coordinate should have stayed 0",
                0,
                r1.getxCoordinate());

        assertEquals( "the y coordinate should become 1",
                1,
                r1.getyCoordinate() );
    }

    @Test
    public void testMoveSouth() throws Exception {
        // given
        Rover r1 = new Rover(99, 99, "S");
        assertEquals("S", r1.getOrientation());

        // when
        r1.move();

        // then
        assertEquals( "the x coordinate should have stayed 99",
                99,
                r1.getxCoordinate());

        assertEquals( "the y coordinate should become 98",
                98,
                r1.getyCoordinate() );
    }

    @Test
    public void testMoveWest() throws Exception {
        // given
        Rover r1 = new Rover(99, 99, "W");
        assertEquals("W", r1.getOrientation());

        // when
        r1.move();

        // then
        assertEquals( "the x coordinate should become 98",
                98,
                r1.getxCoordinate() );

        assertEquals( "the y coordinate should become 98",
                99,
                r1.getyCoordinate() );

    }

    @Test
    public void testMoveEast() throws Exception {
        // given
        Rover r1 = new Rover(0, 0, "E");
        assertEquals("E", r1.getOrientation());

        // when
        r1.move();

        // then
        assertEquals( "the x coordinate should become 1",
                1,
                r1.getxCoordinate() );

        assertEquals( "the y coordinate should have stayed 0",
                0,
                r1.getyCoordinate() );
    }

    @Test
    public void testRotateRight(){
        Rover r1 = new Rover();
        assertEquals(r1.getOrientation(), "N");

        r1.rotateRight();
        assertEquals("E", r1.getOrientation());

        r1.rotateRight();
        assertEquals("S", r1.getOrientation());

        r1.rotateRight();
        assertEquals("W", r1.getOrientation());

        r1.rotateRight();
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testRotateLeft(){
        Rover r1 = new Rover();
        assertEquals(r1.getOrientation(), "N");

        r1.rotateLeft();
        assertEquals("W", r1.getOrientation());

        r1.rotateLeft();
        assertEquals("S", r1.getOrientation());

        r1.rotateLeft();
        assertEquals("E", r1.getOrientation());

        r1.rotateLeft();
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testTakeCommand() throws Exception {
        Rover r1 = new Rover();

        r1.takeCommand("l");
        assertEquals("W", r1.getOrientation());

        r1.takeCommand("r");
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testTakeCommandCasing() throws Exception {
        Rover r1 = new Rover();

        r1.takeCommand("L");
        assertEquals("W", r1.getOrientation());

        r1.takeCommand("R");
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testTakeCommandMove() throws Exception {
        Rover r1 = new Rover();

        r1.takeCommand("m");
        assertEquals(1, r1.getyCoordinate());

        r1.takeCommand("M");
        assertEquals(2, r1.getyCoordinate());
    }



}
