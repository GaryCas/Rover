package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class CommandLine {

    static Scanner sc;

    public static void initialisePlateu(){
        int validationStatusX = -1;
        int validationStatusY = -1;
        sc = getScanner();
        String userInput = "";

        // implementing validation with a while loop
        while(validationStatusX == -1 || validationStatusY == -1) {
            System.out.println("Please enter the x co-ordinates for the top right corner of the Plateu, seperated by commas. example 100, 100 " +
                    "\nleave blank to initialise a plateu of 100, 100 ");
            userInput = sc.nextLine();

            // here replace all will ignore all intermitment whitespace to create a more user friendly command line.
            String[] strings = getCommaSeperatedValues(userInput);

            if(!userInput.isEmpty()) {
                if (strings.length < 2) {
                    System.out.println("Be sure to enter two co-ordinates and follow the specified format ");
                    continue;
                }

                int topRightxInt = Integer.parseInt(strings[0]);
                int topRightyInt = Integer.parseInt(strings[1]);

                validationStatusX = PlateuMap.validateAndModifyTopRightX(topRightxInt);
                validationStatusY = PlateuMap.validateAndModifyTopRightY(topRightyInt);
            } else {
                validationStatusX = PlateuMap.validateAndModifyTopRightX(100);
                validationStatusY = PlateuMap.validateAndModifyTopRightY(100);
            }

        }
    }

    private static String[] getCommaSeperatedValues(String userInput) {
        return userInput.replaceAll("\\s+","").split(",");
    }

    public static void mainMenu(){
        sc = getScanner();
        System.out.println("enter in the next command\n " +
                "R, to create a new rover\n" +
                "M to move and existing rover\n" +
                "X to exit");
        String userInput = sc.nextLine();

        switch(userInput.toLowerCase()){
            case "r":
                createNewRover();
                break;
            case "m":
                moveExistingRover();
                break;
            case "x":
                System.exit(1);
                break;
            default:
                // implementing validation with a recursive call
                System.out.println("that is not a valid command");
                mainMenu();
                break;
        }
    }


    private static void createNewRover() {
        int roverX = 0;
        int roverY = 0;
        Rover rover = null;
        sc = getScanner();

        System.out.println("enter the new rovers position divided by a comma, followed by the rovers orientation\n" +
                "example: 1, 1, N\n" +
                "Alternatively, leave blank to intialise a rove on 0, 0, N");

        String userInput = sc.nextLine();

        if(userInput.isEmpty()){
            rover = new Rover();
        } else {
            String[] values = getCommaSeperatedValues(userInput);

            // implementing validation with a recursive call
            if (values.length < 3) {
                System.out.println("Be sure to enter two co-ordinates and follow the specified format ");
                createNewRover();
            }

            try {
                roverX = Integer.parseInt(values[0]);
                roverY = Integer.parseInt(values[1]);
            } catch (NumberFormatException e) {
                System.out.println("Please follow the specified format");
                createNewRover();
            }
            String orientation = values[2];

            rover = new Rover(roverX, roverY, orientation);
        }

        moveExistingRover(rover);

    }

    /**
     * A method that is called directly from the command line.
     * This method first lists the existing rovers and asks the users to select one before moving the rover.
     */
    private static void moveExistingRover() {
        if(PlateuMap.rovers.isEmpty()){
            System.out.println("Sorry, there aren't any rovers yet ");
            mainMenu();
        }
        System.out.println("which rover would you like to move? ");
        int roverNumber = 0;

        for (int i = 0; i < PlateuMap.rovers.size() ; i++) {
            System.out.println("rover " + i + " id: " + PlateuMap.rovers.get(i).id);
        }

        sc = getScanner();
        try {
            roverNumber = sc.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Please enter a valid interger");
            moveExistingRover();
        }

        moveExistingRover(PlateuMap.rovers.get(roverNumber));
    }

    /**
     * A method to be called once a rover has been selected, this can be called directly from the
     * command line or after creating a new rover
     *
     * @param rover selected or newly created rover
     */
    private static void moveExistingRover(Rover rover) {
        boolean finished = false;

        System.out.println("enter in a series of commands to move the rover seperated by a comma. \n" +
                "R, to roatate right\n" +
                "L to rotate left and\n" +
                "M to move forward");

        String userInput = sc.nextLine();

        if(userInput.isEmpty()){
            System.out.println("please enter in a command ");
            moveExistingRover();
        }

        String[] commands = getCommaSeperatedValues(userInput);

        for (String command : commands) {
                rover.takeCommand(command);
        }

        System.out.println("Moving rover " + rover.id);
    }


    private static Scanner getScanner() {
        if(sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }
}
