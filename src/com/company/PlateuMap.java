package com.company;

import java.util.ArrayList;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class PlateuMap {

    // default values form the plateu
    public static int topRightX = 100;
    public static int topRightY = 100;
    public static ArrayList<Rover> rovers = new ArrayList<>();

    /**
     * Validate value is positive
     *
     * @param newTopRightX
     */
    public static void validateAndModifyTopRightX(int newTopRightX){
        if(newTopRightX > 0){
            topRightX = newTopRightX;
            System.out.println("valid input, initialising Plateus top right x coordinate to " + topRightX);
        } else {
            System.out.println("Invalid input, initialising Plateus top right x coordinate to 100 ");
        }
    }


    /**
     * Validate value is positive
     *
     * @param newTopRightY
     */
    public static void validateAndModifyTopRightY(int newTopRightY){
        if(newTopRightY > 0){
            topRightY = newTopRightY;
            System.out.println("valid input, initialising Plateus top right x coordinate to " + topRightY);
        } else {
            System.out.println("Invalid input, initialising Plateus top right y coordinate to 100 ");
        }
    }
}
