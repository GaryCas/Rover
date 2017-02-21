package com.company;

import java.util.ArrayList;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class PlateuMap {

    // default values form the plateu
    public static int topRightX;
    public static int topRightY ;
    public static ArrayList<Rover> rovers = new ArrayList<>();

    public static int validateAndModifyTopRightX(int newTopRightX){
        if(newTopRightX > 0){
            topRightX = newTopRightX;
            return 0;
        } else {
            return -1;
        }
    }

    public static int validateAndModifyTopRightY(int newTopRightY){
        if(newTopRightY > 0){
            topRightY = newTopRightY;
            return 0;
        } else {
            return -1;
        }
    }
}
