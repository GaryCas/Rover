package com.company.commandline;

import com.company.PlateuMap;
import com.company.Rover;
import services.DataParseAndInputService;

/**
 * Created by rd019985 on 22/02/2017.
 */
public class RoverUI {

    static DataParseAndInputService dataParseAndInputService = new DataParseAndInputService();

    void createNewRover() {
        int roverX = 0;
        int roverY = 0;
        Rover rover;
        String message = "enter the new rovers position divided by a comma, followed by the rovers orientation\n" +
                "example: 1, 1, N\n" +
                "Alternatively, leave blank to intialise a rove on 0, 0, N";

        String userInput = dataParseAndInputService.getUserInput(message);

        if(userInput.isEmpty()){
            rover = new Rover();
        } else {
            String[] values = dataParseAndInputService.getCommaSeperatedValues(userInput);

            // implementing validation with a recursive call
            if (values.length != 3) {
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
    void moveExistingRover() {
        if(PlateuMap.rovers.isEmpty()){
            System.out.println("Sorry, there aren't any rovers yet ");
            MainMenuUI.mainMenu();
            return;
        }

        int roverNumber = dataParseAndInputService.getUserIntInput("which rover would you like to move? ");

        moveExistingRover(PlateuMap.rovers.get(roverNumber));
    }

    /**
     * A method to be called once a rover has been selected, this can be called directly from the
     * command line or after creating a new rover
     *
     * @param rover selected or newly created rover
     */
    private static void moveExistingRover(Rover rover) {
        String message = "enter in a series of commands to move the rover seperated by a comma.\n" +
                "R, to roatate right\n" +
                "L to rotate left and\n" +
                "M to move forward";

        String userInput = dataParseAndInputService.getUserInput(message);

        if(userInput.isEmpty()){
            System.out.println("please enter in a command ");
            moveExistingRover(rover);
        }

        String[] commands = dataParseAndInputService.getCommaSeperatedValues(userInput);

        rover.takeCommand(commands);

        System.out.println("Moving rover " + rover.getId());
        MainMenuUI.mainMenu();
    }
}
