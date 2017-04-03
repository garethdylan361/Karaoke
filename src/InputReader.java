import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Gareth on 4/2/2017.
 */
public class InputReader {

    private List<KaraokeTestCase> storage = new ArrayList<>();

    //Reads the input file and processes it.
    public InputReader(String path){
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            this.processLineAndAddToStorage(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns storage to be handed to the Karaoke
    public List<KaraokeTestCase> getStorage() {
        return this.storage;
    }

    //Reads the line and processes it accordingly
    private void processLineAndAddToStorage(Stream<String> stream) {
        List<String> lines = stream.collect(Collectors.toList());
        List<List<Integer>> groups = new ArrayList<>();
        addGroups(lines, groups);
    }

    //Splits the line into a, b, c, and s.
    private int[] handlePartsOfCombinationLine(String line) {
        return Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    //prepares the loop for the next combination set
    private int handleSetupForNextCombinations(int combinations, String line, List<List<Integer>> groups) {
        if(groups.size() > 0) this.getStorage().add(new KaraokeTestCase(groups));
        return new Integer(line);
    }

    //creates the combination groups and adds them to a KaraokeTestCase
    private void addGroups(List<String> lines, List<List<Integer>> groups) {
        int i = 0, combinations = 0;
        for(String line: lines) {
            if(combinations == 0) groups.clear();
            int[] parts = this.handlePartsOfCombinationLine(line);
            if(combinations != 0 && combinations != i) groups.add(Arrays.asList(parts[0], parts[1], parts[2], parts[3]));
            if(combinations == i) {
                combinations = this.handleSetupForNextCombinations(combinations, line, groups);
                if(combinations == 0) break;
                i=0;
            } else i++;
        }
    }
}
