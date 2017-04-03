import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Gareth on 4/3/2017.
 */
public class IntegrationTest {

    Karaoke karaoke;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {

        InputReader input = new InputReader("tests/input.dat");
        karaoke = new Karaoke(input.getStorage());

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void asAUserICanReadAFileWithTestCasesInItAndReturnThePossibleScores() {
        karaoke.printHighScores();
        assertEquals("6\n-1\n19\n", outContent.toString());
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
