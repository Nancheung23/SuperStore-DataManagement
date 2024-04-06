package SuperStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Processes data from a file by reading its contents and splitting it into
 * lines and arrays.
 * The first line is treated as titles, and the remaining lines are processed
 * into arrays based on a delimiter.
 */
public class FileDataProcessor {
    private String filePath;
    private List<String> lines;
    private String[] titles;

    /**
     * Constructs a FileDataProcessor with the specified file path.
     *
     * @param filePath the path of the file to be processed.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    public FileDataProcessor(final String filePath) {
        setFilePath(filePath);
    }

    /**
     * Gets the file path.
     *
     * @return the file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path and initializes the lines list.
     *
     * @param filePath the file path to set.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    public void setFilePath(final String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }
        this.filePath = filePath;
        this.lines = new ArrayList<>();
    }

    /**
     * Reads the file specified by filePath and splits its content into lines.
     * It then processes these lines into arrays.
     *
     * @throws IOException if an I/O error occurs reading from the file.
     */
    public void processFile() throws IOException {
        try (BufferedReader bfr = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                lines.add(line);
            }
            processLinesIntoArrays(lines);
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filePath, e);
        }
    }

    /**
     * Processes a list of strings (lines) by splitting each line into an array of
     * strings based on a delimiter.
     * The first line is assumed to be the titles and is separated from the data.
     *
     * @param lines the list of lines to process.
     * @return a list of string arrays, each representing data from one line of the
     *         file.
     */
    public List<String[]> processLinesIntoArrays(final List<String> lines) {
        List<String[]> listArr = new ArrayList<>();
        lines.forEach(line -> listArr.add(line.split(";")));
        // Assumes the first line contains titles and removes it from the list
        this.titles = listArr.remove(0);
        return listArr;
    }

    /**
     * Gets the titles extracted from the first line of the file.
     *
     * @return an array of titles.
     */
    public String[] getTitles() {
        return titles;
    }
}
