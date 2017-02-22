package com.company.commandline;

import com.company.PlateuMap;
import com.company.exceptions.PlateuInitException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.DataParseAndInputService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by rd019985 on 22/02/2017.
 */
public class CommandLineTest {

    @Mock
    DataParseAndInputService dataParseAndInputService;

    @Mock
    RoverUI roverUI;

    @Before
    public void setUp(){
        PlateuMap.topRightX = 30;
        PlateuMap.topRightY = 30;
        MockitoAnnotations.initMocks(this);
        MainMenuUI.setDataParseAndInputService(dataParseAndInputService);
        MainMenuUI.setRoverUI(roverUI);
    }

    @Test
    public void testInitialisePlateuSuccessful() throws PlateuInitException {
        // given
        String[] testString = {"10", "10"};
        String testUserString = "10, 10";

        // these methods are unit tested individually so an assumption of this test data can be assumed
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testString);
        given(dataParseAndInputService.getUserInput(anyString())).willReturn(testUserString);

        // when
        MainMenuUI.initialisePlateu();

        // then
        assertEquals(PlateuMap.topRightX, 10);
        assertEquals(PlateuMap.topRightY, 10);
    }

    @Test
    public void testInitialisePlateuDefault() throws PlateuInitException {
        // given
        MainMenuUI.setDataParseAndInputService(dataParseAndInputService);
        String[] testString = {};
        String testUserString = "";

        // these methods are unit tested individually so an assumption of this test data can be assumed
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testString);
        given(dataParseAndInputService.getUserInput(anyString())).willReturn(testUserString);

        // when
        MainMenuUI.initialisePlateu();

        // then
        assertEquals("The top right X should not be modified ", PlateuMap.topRightX, 30);
        assertEquals("The top right Y should not be modified ", PlateuMap.topRightY, 30);
    }

    @Test (expected = PlateuInitException.class)
    public void testInitialisePlateuOneParam() throws PlateuInitException {
        // given
        MainMenuUI.setDataParseAndInputService(dataParseAndInputService);
        String[] testString = {"100"};
        String testUserString = "100";

        // these methods are unit tested individually so an assumption of this test data can be assumed
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testString);
        given(dataParseAndInputService.getUserInput(anyString())).willReturn(testUserString);

        // when
        MainMenuUI.initialisePlateu();

        // then
    }

    @Test (expected = PlateuInitException.class)
    public void testInitialisePlateuOverTwoParams() throws PlateuInitException {
        // given
        MainMenuUI.setDataParseAndInputService(dataParseAndInputService);
        String[] testString = {"100", "100", "100"};
        String testUserString = "100, 100, 100";

        // these methods are unit tested individually so an assumption of this test data can be assumed
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testString);
        given(dataParseAndInputService.getUserInput(anyString())).willReturn(testUserString);

        // when
        MainMenuUI.initialisePlateu();

        // then
    }

    // showing off mockito verify
    @Test
    public void testMainMenu(){
        // given
        given(dataParseAndInputService.getUserInput(anyString())).willReturn("r");

        // when
        MainMenuUI.mainMenu();

        // then
        verify(roverUI, times(1)).createNewRover();
    }
}
