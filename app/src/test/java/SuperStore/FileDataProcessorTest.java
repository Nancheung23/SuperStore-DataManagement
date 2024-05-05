
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class FileDataProcessorTest {

    @Test
    public void testSetAndGetFilePath() {
        FileDataProcessor processor = new FileDataProcessor("/path/to/datafile.csv");
        assertEquals("/path/to/datafile.csv", processor.getFilePath());
    }

    @Test
    public void testProcessLinesIntoArrays() {
        FileDataProcessor processor = new FileDataProcessor("/path/to/datafile.csv");
        List<String> lines = List.of("Title1;Title2;Title3", "Data1;Data2;Data3", "Data4;Data5;Data6");
        List<String[]> result = processor.processLinesIntoArrays(lines);
        assertEquals(2, result.size());
        assertArrayEquals(new String[] {"Data1", "Data2", "Data3"}, result.get(0));
        assertArrayEquals(new String[] {"Data4", "Data5", "Data6"}, result.get(1));
        assertArrayEquals(new String[] {"Title1", "Title2", "Title3"}, processor.getTitles());
    }
}
