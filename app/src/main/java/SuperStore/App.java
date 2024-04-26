/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SuperStore;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class App extends Application {
    public static void main(String[] args) throws IOException {
        // javaFx
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();
        System.out.println("DB session// threadpool");
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("SuperStore Data Importer");
        stage.setScene(scene);
        addFileChooser(root, stage);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        super.stop();
        System.out.println("stop");
    }

    // GUI
    private void addFileChooser(BorderPane root, Stage primaryStage) {
        VBox vbox = new VBox(10);
        // center display
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Choose a CSV file:");
        TextField filePathField = new TextField();
        filePathField.setEditable(false);
        Button fileButton = new Button("Browse...");

        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                filePathField.setText(selectedFile.getAbsolutePath());
                FileDataProcessor fdp = new FileDataProcessor(
                        selectedFile.getAbsolutePath());
                FileDataProcessor rfdp = new FileDataProcessor(
                        selectedFile.getAbsolutePath());
                InstanceGenerator ig;
                try {
                    // init
                    ig = new InstanceGenerator(fdp.processFile());
                    ig.initialization();
                    ig.setReturnMap(rfdp.processFile());
                    // table
                    HashMap<String, Customer> customerMap = ig.getCustomerMap();
                    TableView<Customer> customerTable = createCustomerTable(customerMap);
                    root.setCenter(customerTable);
                    primaryStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        vbox.getChildren().addAll(label, filePathField, fileButton);
        root.setCenter(vbox);
    }

    private TableView<Customer> createCustomerTable(HashMap<String, Customer> customerMap) {
        TableView<Customer> table = new TableView<>();
        TableColumn<Customer, String> idColumn = new TableColumn<>("Customer ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Customer, String> segmentColumn = new TableColumn<>("Segment");
        segmentColumn.setCellValueFactory(new PropertyValueFactory<>("segment"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(segmentColumn);

        ObservableList<Customer> data = FXCollections.observableArrayList(customerMap.values());
        table.setItems(data);

        return table;
    }
}
