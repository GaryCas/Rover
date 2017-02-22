package com.company;

import com.company.commandline.MainMenuUI;
import com.company.commandline.RoverUI;
import com.company.exceptions.PlateuInitException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.DataParseAndInputService;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by rd019985 on 22/02/2017.
 */
public class ThoughtworksTest {

    @Mock
    DataParseAndInputService dataParseAndInputService;

    RoverUI roverUI = new RoverUI();

    @Before
    public void startUp(){
        MockitoAnnotations.initMocks(this);
        PlateuMap.topRightX = 100;
        PlateuMap.topRightY = 100;
        PlateuMap.rovers = new ArrayList<>();
    }

    @Test
    public void thoughtworksInitTest() throws PlateuInitException {
        // given
        MainMenuUI.setDataParseAndInputService(dataParseAndInputService);
        String[] testStrings = {"5","5"};
        given(dataParseAndInputService.getUserInput(anyString())).willReturn("5, 5");

        // this behaviour is validated by other unit tests
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testStrings);

        // when
        MainMenuUI.initialisePlateu();

        // then
        assertEquals(PlateuMap.topRightX, 5);
        assertEquals(PlateuMap.topRightY, 5);
    }

    @Test
    public void thoughtworksCreaterobotTest(){
        // given 2
        RoverUI.setDataParseAndInputService(dataParseAndInputService);

        String[] testStrings2 = {"1","2","N"};
        given(dataParseAndInputService.getUserInput(anyString())).willReturn("1, 2, N");
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testStrings2);

        // when 2
        roverUI.createNewRover();

        // then 2
        assertEquals(1, PlateuMap.rovers.get(0).getxCoordinate());
        assertEquals(2, PlateuMap.rovers.get(0).getyCoordinate());
        assertEquals("N", PlateuMap.rovers.get(0).getOrientation());
    }

    @Test
    public void thoughtworksMoveRobotTest1(){
        // given 2
        RoverUI.setDataParseAndInputService(dataParseAndInputService);

        Rover r1 = new Rover(1, 2, "N");
        String[] testStrings3 = {"L","M","L", "M","L","M","L","M","M"};
        given(dataParseAndInputService.getUserInput(anyString())).willReturn("L,M,L,M,L,M,L,M,M");
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testStrings3);

        // when 2
        roverUI.moveExistingRover(r1);

        // then 2
        assertEquals(1, PlateuMap.rovers.get(0).getxCoordinate());
        assertEquals(3, PlateuMap.rovers.get(0).getyCoordinate());
        assertEquals("N", PlateuMap.rovers.get(0).getOrientation());
    }

    @Test
    public void thoughtworksMoveRobotTest2(){
        // given 2
        RoverUI.setDataParseAndInputService(dataParseAndInputService);

        Rover r1 = new Rover(3, 3, "E");
        String[] testStrings3 = {"M","M","R", "M","M","R","M","R","R", "M"};
        given(dataParseAndInputService.getUserInput(anyString())).willReturn("M, M, R, M, M, R, M, R, R, M");
        given(dataParseAndInputService.getCommaSeperatedValues(anyString())).willReturn(testStrings3);

        // when 2
        roverUI.moveExistingRover(r1);

        // then 2
        assertEquals(5, PlateuMap.rovers.get(0).getxCoordinate());
        assertEquals(1, PlateuMap.rovers.get(0).getyCoordinate());
        assertEquals("E", PlateuMap.rovers.get(0).getOrientation());
    }
}
