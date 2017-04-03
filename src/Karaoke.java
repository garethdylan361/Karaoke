import java.util.List;

/**
 * Created by Gareth on 4/2/2017.
 */
public class Karaoke {
    List<KaraokeTestCase> tests;

    public Karaoke(List<KaraokeTestCase> tests) {
        this.tests = tests;
    }

    public void printHighScores() {
        String output = this.tests.stream().map(e -> e.getHighestScore() + "\n").reduce("", String::concat);
        System.out.print(output);
    }

}
