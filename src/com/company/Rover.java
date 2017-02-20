package com.company;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class Rover {

    // saving each co-ordinate and orientation through a series of steps allows the operators to see a history of the
    // steps that the rover has taken. This data would be important for correlating with other information such as a video stream
    private int xCoordinate;
    private int yCoordinate;
    String orientation;

    public Rover(){
        System.out.println("No coordinates provided, defaulting to 0, 0, N");
        try {
            validateAndAddxCoordinate(0);
            validateAndAddyCoordinate(0);
            validateAndAddOrientation("N");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * ensure that the co-ordinates specified are within the correct range and add them as the first co-ordinate
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param orientation
     */
    public Rover(int xCoordinate, int yCoordinate, String orientation){

        try {
            validateAndAddxCoordinate(xCoordinate);
            validateAndAddyCoordinate(yCoordinate);
            validateAndAddOrientation(orientation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOrientation() {
        return orientation;
    }

    public void takeCommand(String s) throws Exception {
        switch (s.toLowerCase()){
            case "l":
                rotateLeft();
                break;
            case "r":
                rotateRight();
                break;
            case "m":
                move();
                break;
            default:
                System.out.println(s + " is not a valid command");
                break;
        }
    }

    public void move() throws Exception {
        switch (this.orientation.toLowerCase()){
            case "n":
                this.validateAndAddyCoordinate(getyCoordinate() + 1);
                break;
            case "w":
                this.validateAndAddxCoordinate(getxCoordinate() - 1);
                break;
            case "s":
                this.validateAndAddyCoordinate(getyCoordinate() - 1);
                break;
            case "e":
                this.validateAndAddxCoordinate(getxCoordinate() + 1);
                break;
            default:
                System.out.println("That is not a valid orientation!");
                break;
        }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    // these methods have been made protected so they can be reached by the the unit tests
    // validation on these two methods is not required as the orientation value being compared will always be internal and not inputted by the user
    protected void rotateRight() {
        switch (this.orientation.toLowerCase()){
            case "n":
                this.orientation = "E";
                break;
            case "w":
                this.orientation = "N";
                break;
            case "s":
                this.orientation = "W";
                break;
            case "e":
                this.orientation = "S";
                break;
        }
    }

    protected void rotateLeft() {
        switch (this.orientation.toLowerCase()){
            case "n":
                this.orientation = "W";
                break;
            case "w":
                this.orientation = "S";
                break;
            case "s":
                this.orientation = "E";
                break;
            case "e":
                this.orientation = "N";
                break;
        }
    }

    protected void validateAndAddyCoordinate(int yCoordinate) throws Exception {
        if(yCoordinate >= 0 && yCoordinate < PlateuMap.topRightY){
            this.yCoordinate =yCoordinate;
        }  else {
            throw new Exception("yCoordinate must be between 0 and " + PlateuMap.topRightY + "/nBut is " + yCoordinate);
        }
    }

    protected void validateAndAddxCoordinate(int xCoordinate) throws Exception {
        if(xCoordinate >= 0 && xCoordinate < PlateuMap.topRightX){
            this.xCoordinate = xCoordinate;
        } else {
            throw new Exception("xCoordinate must be between 0 and " + PlateuMap.topRightX + "/nBut is " + xCoordinate);
        }
    }

    protected void validateAndAddOrientation(String orientation) throws Exception {
        if( orientation.equalsIgnoreCase("N") ||
                orientation.equalsIgnoreCase("S") ||
                orientation.equalsIgnoreCase("W") ||
                orientation.equalsIgnoreCase("E")){
            this.orientation = orientation;
        } else {
            throw new Exception("there is no such orientation as " + orientation);
        }
    }

}
