package com.company.commandline;

import com.company.PlateuMap;
import com.company.exceptions.PlateuInitException;
import services.DataParseAndInputService;


public class MainMenuUI {

    static RoverUI roverUI = new RoverUI();
    static DataParseAndInputService dataParseAndInputService = new DataParseAndInputService();

    // move getcommaseperatedValues to another class and mock that class.
    public static void initialisePlateu() throws PlateuInitException {

        String userInput = "";
        String message = "Please enter the x co-ordinates for the top right corner of the Plateu, seperated by commas. example 100, 100\n" +
                "                leave blank to initialise a plateu of 100, 100";

        userInput = dataParseAndInputService.getUserInput(message);

        String[] strings = dataParseAndInputService.getCommaSeperatedValues(userInput);

        // if the validation fails, call the method again.
        if(!userInput.isEmpty()) {
            if (strings.length != 2) {
                System.out.println("Be sure to enter two co-ordinates and follow the specified format ");
                throw new PlateuInitException("the init parameters must have an argument length of 2 or 0... retrying ");
            }

            int topRightxInt = Integer.parseInt(strings[0]);
            int topRightyInt = Integer.parseInt(strings[1]);

            PlateuMap.validateAndModifyTopRightX(topRightxInt);
            PlateuMap.validateAndModifyTopRightY(topRightyInt);
        }
    }

    public static void mainMenu(){
        String message = "enter in the next command\n " +
                "R, to create a new rover\n" +
                "M to move and existing rover\n" +
                "X to exit";

        String userInput = dataParseAndInputService.getUserInput(message);

        switch(userInput.toLowerCase()){
            case "r":
                roverUI.createNewRover();
                break;
            case "m":
                roverUI.moveExistingRover();
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

    // these methods are used for mock injections.
    // this sort of messy implementation breaks certain encapsulation principles and can be circumvented
    // by implementing a dependency injection mechanism such as Google Guice
    public static void setDataParseAndInputService(DataParseAndInputService dataParseAndInputService) {
        MainMenuUI.dataParseAndInputService = dataParseAndInputService;
    }

    public static void setRoverUI(RoverUI roverUI) {
        MainMenuUI.roverUI = roverUI;
    }
}
