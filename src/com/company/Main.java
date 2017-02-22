package com.company;

import com.company.commandline.MainMenuUI;
import com.company.exceptions.ExceptionHandler;
import com.company.exceptions.PlateuInitException;

public class Main {

    public static void main(String[] args) throws PlateuInitException {

        try {
            MainMenuUI.initialisePlateu();
            MainMenuUI.mainMenu();
        } catch (PlateuInitException e) {
            ExceptionHandler.handlePlateuInitException(e);
        }

    }
}
