import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Gareth on 4/2/2017.
 */
class InputReaderTest {

    public InputReader reader;
    public List<KaraokeTestCase> storage;
    public List<List<Integer>> exampleList;

    @BeforeEach
    public void setup(){
        reader =  new InputReader("tests/input.dat");
        storage = reader.getStorage();
        exampleList = new ArrayList<>();
        exampleList.add(Arrays.asList(1, 2, 3, 1));
        exampleList.add(Arrays.asList(4, 5, 6, 2));
        exampleList.add(Arrays.asList(7, 8, 9, 3));
    }

   @Test
    public void InputReaderCanReadTheFirstLineToStorage() {
        assert(storage.get(0).getGroups()).equals(exampleList);
    }

}