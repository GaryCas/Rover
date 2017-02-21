package com.company.exceptions;

import com.company.PlateuMap;
import com.company.Rover;
import org.junit.Test;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class ExceptionHandlerTest {

    // doesn't acctually test anything at the moment but woul
    @Test
    public void testHandleCollisionException(){
        // given
        PlateuMap.topRightX = 100;
        PlateuMap.topRightY = 100;

        CollisionException e = new CollisionException("");
        String[] commands = {"r", "m", "m", "l", "m"};
        int i = 1;
        Rover rover = new Rover(2, 2, "W");
        Rover collidedRover = new Rover(1, 2, "W");
        rover.setCollidedWithRover(collidedRover);

        // when
        ExceptionHandler.handleCollisionException(e, commands, i, rover);

        // then
    }
}
