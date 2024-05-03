/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SuperStore;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class App extends Application {
    private File lastKnownDirectory = null;

    public static void main(String[] args) throws IOException {
        // javaFx
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("GUI Initialization...");
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
        super.stop();
        System.out.println("Program is stopped");
    }

    // GUI
    private void addFileChooser(BorderPane root, Stage primaryStage) {
        VBox vbox = new VBox(10);
        // center display
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Choose data CSV file:");
        TextField filePathField = new TextField();
        filePathField.setEditable(false);
        Label rLabel = new Label("Choose return CSV file:");
        TextField rFilePathField = new TextField();
        rFilePathField.setEditable(false);
        InputStream dataStream = getClass().getResourceAsStream("/SuperStoreOrders.csv");
        InputStream returnStream = getClass().getResourceAsStream("/SuperStoreReturns.csv");
        if (dataStream != null && returnStream != null) {
            filePathField.setText("Resource loaded: SuperStoreOrders.csv");
            rFilePathField.setText("Resource loaded: SuperStoreReturns.csv");
        } else {
            filePathField.setText("Failed to load default resources");
            rFilePathField.setText("Failed to load default resources");
        }

        // user select
        Button fileButton = new Button("Browse data CSV...");
        Button rFileButton = new Button("Browse return CSV...");

        // Handlers for the data file button
        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            if (lastKnownDirectory != null) {
                fileChooser.setInitialDirectory(lastKnownDirectory);
            }
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                filePathField.setText(selectedFile.getAbsolutePath());
                lastKnownDirectory = selectedFile.getParentFile();
            }
        });

        // Handlers for the return file button
        rFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            if (lastKnownDirectory != null) {
                fileChooser.setInitialDirectory(lastKnownDirectory);
            }
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                rFilePathField.setText(selectedFile.getAbsolutePath());
                lastKnownDirectory = selectedFile.getParentFile();
            }
        });

        // Initialize button
        Button initButton = new Button("Initialize");
        initButton.setOnAction(e -> {
            if (!filePathField.getText().isEmpty() && !rFilePathField.getText().isEmpty()) {
                try {
                    FileDataProcessor fdp = new FileDataProcessor(filePathField.getText());
                    FileDataProcessor rfdp = new FileDataProcessor(rFilePathField.getText());
                    InstanceGenerator ig = new InstanceGenerator(fdp.processFile());
                    ig.initialization();
                    ig.setReturnMap(rfdp.processFile());
                    // table
                    HashMap<String, Customer> customerMap = ig.getCustomerMap();
                    showCustomerTable(root, primaryStage, customerMap);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("Error processing files: " + e1.getMessage());
                }
            } else {
                System.out.println("Both data and return files must be selected.");
            }
        });
        // Add components to the VBox
        vbox.getChildren().addAll(label, filePathField, fileButton, rLabel, rFilePathField, rFileButton, initButton);
        root.setCenter(vbox);
    }

    // user file chooser (default selected)
    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    private void showCustomerTable(BorderPane root, Stage primaryStage, HashMap<String, Customer> customerMap) {
        TableView<Customer> customerTable = createCustomerTable(customerMap);
        customerTable.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Customer clickedRow = row.getItem();
                    showCustomerOrders(clickedRow, primaryStage);
                }
            });
            return row;
        });
        root.setCenter(customerTable);
    }

    private void showCustomerOrders(Customer customer, Stage primaryStage) {
        Stage stage = new Stage();
        TableView<Order> orderTable = createOrderTable(customer.getOrders());
        orderTable.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Order clickedOrder = row.getItem();
                    showOrderProducts(clickedOrder, stage);
                }
            });
            return row;
        });
        Scene scene = new Scene(orderTable);
        stage.setScene(scene);
        stage.setTitle("Orders for " + customer.getCustomerName());
        stage.show();
    }

    private void showOrderProducts(Order order, Stage parentStage) {
        TableView<Product> productTable = createProductTable(order.getProducts());
        Stage stage = new Stage();
        Scene scene = new Scene(productTable);
        stage.setScene(scene);
        stage.setTitle("Products for Order ID: " + order.getOrderId());
        stage.initOwner(parentStage);
        stage.show();
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

    private TableView<Order> createOrderTable(ObservableMap<String, Order> observableMap) {
        TableView<Order> table = new TableView<>();
        TableColumn<Order, String> idColumn = new TableColumn<>("Order ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        TableColumn<Order, String> orderDateColumn = new TableColumn<>("Order Date");
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        TableColumn<Order, String> shipDateColumn = new TableColumn<>("Ship Date");
        shipDateColumn.setCellValueFactory(new PropertyValueFactory<>("shipDate"));
        TableColumn<Order, Boolean> isReturnColumn = new TableColumn<>("Is Return");
        isReturnColumn.setCellValueFactory(new PropertyValueFactory<>("isReturn"));
        TableColumn<Order, Address> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getAddress()));
        addressColumn.setCellFactory(param -> new TableCell<Order, Address>() {
            private final Button detailButton = new Button("Detail");

            @Override
            protected void updateItem(Address address, boolean empty) {
                super.updateItem(address, empty);

                if (empty || address == null) {
                    setGraphic(null);
                } else {
                    setGraphic(detailButton);
                    detailButton.setOnAction(event -> showAddressDetails(address));
                }
            }
        });
        table.getColumns().add(idColumn);
        table.getColumns().add(orderDateColumn);
        table.getColumns().add(shipDateColumn);
        table.getColumns().add(isReturnColumn);
        table.getColumns().add(addressColumn);
        ObservableList<Order> data = FXCollections.observableArrayList(observableMap.values());
        table.setItems(data);
        return table;
    }

    private void showAddressDetails(Address address) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Address Details");
        alert.setHeaderText(null);
        alert.setContentText("Detail:" + address);
        alert.showAndWait();
    }

    private TableView<Product> createProductTable(ObservableMap<String, Product> observableMap) {
        TableView<Product> table = new TableView<>();
        TableColumn<Product, String> idColumn = new TableColumn<>("Product ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        TableColumn<Product, Double> salesColumn = new TableColumn<>("Sales");
        salesColumn.setCellValueFactory(new PropertyValueFactory<>("sales"));
        TableColumn<Product, Double> profitColumn = new TableColumn<>("Profit");
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        TableColumn<Product, Double> discountColumn = new TableColumn<>("Discount");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        TableColumn<Product, Double> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(salesColumn);
        table.getColumns().add(profitColumn);
        table.getColumns().add(discountColumn);
        table.getColumns().add(quantityColumn);
        ObservableList<Product> data = FXCollections.observableArrayList(observableMap.values());
        table.setItems(data);
        return table;
    }
}
