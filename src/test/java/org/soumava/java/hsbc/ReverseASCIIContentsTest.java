package org.soumava.java.hsbc;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReverseASCIIContentsTest {

    @Test
    public void reverseContent() throws IOException {
        String[] args = new String[] {"Input.txt", "Output.txt"};
        ReverseASCIIContents reverseASCIIContents = mock(ReverseASCIIContents.class);
        doNothing().when(reverseASCIIContents).reverseContent(args[0], args[1]);
        verify(reverseASCIIContents, times(1));
        ReverseASCIIContents.main(args);
    }

    @Test
    public void testPureASCII_True() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReverseASCIIContents reverseASCIIContents = mock(ReverseASCIIContents.class);
        Method privateMethod = ReverseASCIIContents.class.getDeclaredMethod("isPureAscii", String.class);
        privateMethod.setAccessible(true);
        boolean result = (boolean) privateMethod.invoke(reverseASCIIContents, "ABC");
        assertTrue(result);
    }

    @Test
    public void testPureASCII_False() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReverseASCIIContents reverseASCIIContents = mock(ReverseASCIIContents.class);
        Method privateMethod = ReverseASCIIContents.class.getDeclaredMethod("isPureAscii", String.class);
        privateMethod.setAccessible(true);
        boolean result = (boolean) privateMethod.invoke(reverseASCIIContents, "Réal");
        assertFalse(result);
    }

    @Test
    public void reverseContentWithError() throws IOException {
        String[] args = new String[] {"Inp.txt", "Out.txt"};
        ReverseASCIIContents reverseASCIIContents = mock(ReverseASCIIContents.class);
        doThrow(NoSuchFileException.class).when(reverseASCIIContents).reverseContent(args[0], args[1]);
    }
}