package com.company.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.DataParseAndInputService;

import java.util.Scanner;

import static com.sun.javaws.JnlpxArgs.verify;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;

/**
 * Created by rd019985 on 22/02/2017.
 */
public class DataParseAndInputServiceTest {

    @Test
    public void testCommaseperateValues(){
        // given
        DataParseAndInputService dataParseAndInputService = new DataParseAndInputService();

        // when
        String[] resultString = dataParseAndInputService.getCommaSeperatedValues("10, 100, 1000");

        // then
        assertEquals("10", resultString[0]);
        assertEquals("100", resultString[1]);
        assertEquals("1000", resultString[2]);
    }

}
