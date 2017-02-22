package com.company;

import com.company.exceptions.CollisionException;
import com.company.exceptions.ExceptionHandler;
import com.company.exceptions.OrientationException;
import services.ValidationService;

import java.util.Random;

/**
 * Created by rd019985 on 20/02/2017.
 */
public class Rover {
    private int xCoordinate;
    private int yCoordinate;
    String orientation;
    Long id;
    Rover collidedWithRover;

    /**
     * Default constructor
     */
    public Rover(){
        this.id = Math.abs(new Random().nextLong());
        System.out.println("Creating robot with id " + this.id);
        System.out.println("No coordinates provided, defaulting to 0, 0, N");
        try {
            ValidationService.validateNewCoordinates(this, 0, 0);
            ValidationService.validateAndAddOrientation(this, "N");
        } catch (OrientationException e) {
            ExceptionHandler.handleOrientationException(e.getLocalizedMessage());
            this.orientation = "N";
        } catch (CollisionException e) {
            e.printStackTrace();
        }

        PlateuMap.rovers.add(this);
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
        this.id = Math.abs(new Random().nextLong());
        System.out.println("Creating robot with id " + this.id);
        try {
            ValidationService.validateNewCoordinates(this, xCoordinate, yCoordinate);
            ValidationService.validateAndAddOrientation(this, orientation);
        } catch (OrientationException e) {
            e.printStackTrace();
            this.orientation = "N";
        } catch (CollisionException e) {
            e.printStackTrace();
        }

        PlateuMap.rovers.add(this);
    }

    public String getOrientation() {
        return orientation;
    }

    public void takeCommand(String[] commands) {

        for (int i = 0; i < commands.length; i++) {
            switch (commands[i].toLowerCase()){
                case "l":
                    rotateLeft();
                    break;
                case "r":
                    rotateRight();
                    break;
                case "m":
                    try {
                        move();
                    } catch (IndexOutOfBoundsException e) {
                        ExceptionHandler.handleOutOfBoundsException(e);
                    } catch (CollisionException e){
                        ExceptionHandler.handleCollisionException(e, commands, i, this);
                    }
                    break;
                default:
                    System.out.println(commands[i] + " is not a valid command");
                    break;
            }
        }


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

    protected void move() throws IndexOutOfBoundsException, CollisionException {
        switch (this.orientation.toLowerCase()){
            case "n":
                ValidationService.validateNewCoordinates(this, this.getxCoordinate(), this.getyCoordinate() + 1);
                break;
            case "w":
                ValidationService.validateNewCoordinates(this, this.getxCoordinate() - 1, this.getyCoordinate());
                break;
            case "s":
                ValidationService.validateNewCoordinates(this, this.getxCoordinate(), this.getyCoordinate() - 1);
                break;
            case "e":
                ValidationService.validateNewCoordinates(this, this.getxCoordinate() + 1, this.getyCoordinate());
                break;
            default:
                // should never be called
                System.out.println("That is not a valid orientation!");
                break;
        }

        System.out.println("the rovers new position is " +
                "x: " + this.getxCoordinate() +
                " y: " + this.getyCoordinate() +
                "orientation: " + this.getOrientation()
        );
    }

    // Getters and Setters
    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Long getId() {
        return id;
    }

    public Rover getCollidedWithRover() {
        return collidedWithRover;
    }

    public void setCollidedWithRover(Rover collidedWithRover) {
        this.collidedWithRover = collidedWithRover;
    }
}
