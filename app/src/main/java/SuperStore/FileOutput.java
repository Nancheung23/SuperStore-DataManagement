package SuperStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class FileOutput {
    private String fileOutputPath;
    private InstanceGenerator ig;

    public FileOutput (String filePath, InstanceGenerator ig){
        this.fileOutputPath = filePath;
        this.ig = ig;
    }

    public void fileWriter() {
        try (Writer fw = new FileWriter(fileOutputPath);
                BufferedWriter bfw = new BufferedWriter(fw);) {
                    // add all data
                    ig.initialization();
                    // input Static Method : calculateCustomersNumber
                    int number = CustomerMapUtils.calculateCustomersNumber(ig.getCustomerMap());
                    bfw.write("calculateCustomersNumber:" + number);
                    bfw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
