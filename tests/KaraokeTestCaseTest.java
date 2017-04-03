import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by Gareth on 4/2/2017.
 */
class KaraokeTestCaseTest {

    KaraokeTestCase testCase;
    List<List<Integer>> groups;

    @BeforeEach
    void setup() {
        groups = new ArrayList<>();
    }

    @Test
    void karaokeTestCaseWillNotAddAGroupThatDoesNotValidateALessThanBLessThanC() {
        groups.add(Arrays.asList(1, 5, 3, 1));
        testCase = new KaraokeTestCase(groups);
        assertEquals(0, testCase.getGroups().size());
    }

    @Test
    void karaokeTestCaseWillAddAGroupThatDoesValidateALessThanBLessThanC() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        testCase = new KaraokeTestCase(groups);
        assertEquals(1, testCase.getGroups().size());
    }

    @Test
    void karaokeTestCaseWillNotAddAGroupThatDoesNotValidate0LessThanScore() {
        groups.add(Arrays.asList(1, 2, 3, -1));
        testCase = new KaraokeTestCase(groups);
        assertEquals(0, testCase.getGroups().size());
    }

    @Test
    void karaokeTestCaseWillNotAddAGroupThatDoesNotValidateScoreLessThan1000() {
        groups.add(Arrays.asList(1, 2, 3, 10001));
        testCase = new KaraokeTestCase(groups);
        assertEquals(0, testCase.getGroups().size());
    }

    @Test
    void karaokeTestCaseWillAddAGroupThatDoesValidate0LessThanScoreLessThan10000() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        testCase = new KaraokeTestCase(groups);
        assertEquals(1, testCase.getGroups().size());
    }

    @Test
    void karaokeTestCaseCanDetermineIfThereAreEnoughPossibleGroups() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(4, 5, 6, 2));
        groups.add(Arrays.asList(7, 8, 9, 3));
        testCase = new KaraokeTestCase(groups);
        assertTrue(testCase.isValid());
    }

    @Test
    void karaokeTestCaseCanDetermineIfThereAreNotEnoughPossibleGroups() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(1, 4, 5, 2));
        groups.add(Arrays.asList(1, 7, 8, 3));
        groups.add(Arrays.asList(1, 8, 9, 4));
        testCase = new KaraokeTestCase(groups);
        assertFalse(testCase.isValid());

    }
   @Test
    void karaokeTestCaseCanDetermineIfThereAreEnoughPossibleGroupsForALargerSet() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(1, 4, 5, 2));
        groups.add(Arrays.asList(3, 4, 5, 3));
        groups.add(Arrays.asList(1, 8, 9, 4));
        groups.add(Arrays.asList(1, 4, 5, 5));
        groups.add(Arrays.asList(6, 7, 8, 6));
        groups.add(Arrays.asList(2, 3, 9, 8));
        testCase = new KaraokeTestCase(groups);
        assertTrue(testCase.isValid());
    }

    @Test
    void karaokeTestCaseCanGetHighestPossibleScoreForValid() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(4, 5, 6, 2));
        groups.add(Arrays.asList(7, 8, 9, 3));
        testCase = new KaraokeTestCase(groups);
        assertEquals(6, testCase.getHighestScore());
    }

    @Test
    void karaokeTestCaseCanGetHighestPossibleScoreForInvalid() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(1, 4, 5, 2));
        groups.add(Arrays.asList(1, 7, 8, 3));
        groups.add(Arrays.asList(1, 8, 9, 4));
        testCase = new KaraokeTestCase(groups);
        assertEquals(-1, testCase.getHighestScore());
    }

    @Test
    void karaokeTestCaseCanGetHighestPossibleScoreForLargerValid() {
        groups.add(Arrays.asList(1, 2, 3, 1));
        groups.add(Arrays.asList(1, 4, 5, 2));
        groups.add(Arrays.asList(3, 4, 5, 3));
        groups.add(Arrays.asList(1, 8, 9, 4));
        groups.add(Arrays.asList(1, 4, 5, 5));
        groups.add(Arrays.asList(6, 7, 8, 6));
        groups.add(Arrays.asList(2, 3, 9, 8));
        testCase = new KaraokeTestCase(groups);
        assertEquals(19, testCase.getHighestScore());
    }

}