package com.company.exceptions;

import com.company.Rover;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class ExceptionHandler {

    // can be modified to use Dijstra's algorithm to find an alternative path for the rover.
    // this can be done by modifying the command on which the first rover collided with the second to 'c'
    // splitting the string between the c and the next m, finding out the rovers ultimate destination
    // finding an alternative route and splicing and modifying the first rovers movement commands

    /**
     *  Handles the Collision Exception. Currently prints out information about the collision.
     *
     * Can be modified to use Dijstra's algorithm to find an alternative path for the rover.
     * this can be done by modifying the command on which the first rover collided with the second to 'c'
     * splitting the string between the c and the next m, finding out the rovers ultimate destination
     * finding an alternative route and splicing and modifying the first rovers movement commands
     *
     * @param e exception with message
     * @param commands the command string of the first rover that collided with the second
     * @param i the index of the command string at which the collision occurred, this can be used for splicing
     * @param rover the first rover that collided with the static rover represented by rover.getCollidedRover();
     */
    public static void handleCollisionException(CollisionException e, String[] commands, int i, Rover rover) {
        System.out.println(e.getMessage());
        String commandString = "";

        for (String command : commands) {
            commandString = commandString.concat(command + ", ");
        }

        System.out.println("rover " + rover.getId() + " at position X: " + rover.getxCoordinate()+ " Y: " + rover.getyCoordinate() +
                " \nWas on track to Collide on command index " + i + " in command string " + commandString + "moving " + rover.getOrientation() +
                " \nwith rover " +  rover.getCollidedWithRover().getId() + " at position X: "
                + rover.getCollidedWithRover().getxCoordinate()+ " Y: " + rover.getCollidedWithRover().getyCoordinate());

    }

    /**
     * Prints out the exception message
     *
     * @param e exception with exception message
     */
    public static void handleOutOfBoundsException(IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
    }
}
