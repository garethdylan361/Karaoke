import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by Gareth on 4/2/2017.
 */
class KaraokeTest {

    Karaoke karaoke;
    KaraokeTestCase testCase;
    KaraokeTestCase testCase1;
    KaraokeTestCase testCase2;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {

        testCase = Mockito.mock(KaraokeTestCase.class);
        testCase1 = Mockito.mock(KaraokeTestCase.class);
        testCase2 = Mockito.mock(KaraokeTestCase.class);
        karaoke = new Karaoke(Arrays.asList(testCase, testCase1, testCase2));
        when(testCase.getHighestScore()).thenReturn(6);
        when(testCase1.getHighestScore()).thenReturn(-1);
        when(testCase2.getHighestScore()).thenReturn(19);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    void karaokeCanPrintHighestScores() {
        karaoke.printHighScores();
        assertEquals("6\n-1\n19\n", outContent.toString());
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}