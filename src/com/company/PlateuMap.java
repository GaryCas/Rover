package com.company;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class PlateuMap {

    // default values form the plateu
    public static int topRightX;
    public static int topRightY ;

    public void validateAndModifyTopRightX(int newTopRightX){
        if(newTopRightX > 0){
            topRightX = newTopRightX;
        }
    }

    public void validateAndModifyTopRightY(int newTopRightY){
        if(newTopRightY > 0){
            topRightY = newTopRightY;
        }
    }
}
