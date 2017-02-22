package services;

import com.company.PlateuMap;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * These methods were seperated into a service to assist with mocking and unit testing.
 */
public class DataParseAndInputService {

    static Scanner sc = null;

    public String[] getCommaSeperatedValues(String userInput) {
        // here replace all will ignore all intermitment whitespace to create a more user friendly command line.
        return userInput.replaceAll("\\s+","").split(",");
    }

    public String getUserInput(String message){
        // implementing validation with a while loop
        System.out.println(message);
        return getScanner().nextLine();
    }

    public int getUserIntInput(String message) throws InputMismatchException{
        // implementing validation with a while loop
        System.out.println(message);

        for (int i = 0; i < PlateuMap.rovers.size() ; i++) {
            System.out.println("rover " + i + " id: " + PlateuMap.rovers.get(i).getId());
        }

        return getScanner().nextInt();
    }

    private Scanner getScanner(){
        return new Scanner(System.in);
    }
}
