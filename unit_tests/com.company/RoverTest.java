package com.company;


import com.company.exceptions.CollisionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.ValidationService;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class RoverTest {

    @Before
    public void setUp(){
        PlateuMap.topRightX = 100;
        PlateuMap.topRightY = 100;
        PlateuMap.rovers = new ArrayList<>();
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
    @Test (expected = IndexOutOfBoundsException.class)
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
        String[] strings = {"l"};

        r1.takeCommand(strings);
        assertEquals("W", r1.getOrientation());
        strings[0] = "r";

        r1.takeCommand(strings);
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testTakeCommandCasing() throws Exception {
        Rover r1 = new Rover();
        String[] strings = {"L"};

        r1.takeCommand(strings);
        assertEquals("W", r1.getOrientation());
        strings[0] = "R";


        r1.takeCommand(strings);
        assertEquals("N", r1.getOrientation());
    }

    @Test
    public void testTakeCommandMove() throws Exception {
        Rover r1 = new Rover();
        String[] strings = {"m"};

        r1.takeCommand(strings);
        assertEquals(1, r1.getyCoordinate());

        strings[0] = "M";
        r1.takeCommand(strings);
        assertEquals(2, r1.getyCoordinate());
    }

    @Test(expected = CollisionException.class)
    public void testCollisionNorth() throws CollisionException {
        // given
        Rover r1 = new Rover(1, 1, "N");
        Rover r2 = new Rover(1, 0, "N");

        // when
        r2.move();
    }

    @Test(expected = CollisionException.class)
    public void testCollisionWest() throws CollisionException {
        // given
        Rover r1 = new Rover(1, 1, "N");
        Rover r2 = new Rover(2, 1, "W");

        // when
        r2.move();
    }

    @Test(expected = CollisionException.class)
    public void testCollisionSouth() throws CollisionException {
        // given
        Rover r1 = new Rover(1, 1, "N");
        Rover r2 = new Rover(1, 2, "S");

        // when
        r2.move();
    }

    @Test(expected = CollisionException.class)
    public void testCollisionEast() throws CollisionException {
        // given
        Rover r1 = new Rover(1, 1, "N");
        Rover r2 = new Rover(0, 1, "E");

        // when
        r2.move();
    }


}
