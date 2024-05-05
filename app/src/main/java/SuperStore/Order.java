package SuperStore;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.converter.LocalDateStringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Order {
    private final SimpleStringProperty orderId = new SimpleStringProperty(this, "orderId");
    private final ObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>(this, "orderDate");
    private final ObjectProperty<LocalDate> shipDate = new SimpleObjectProperty<>(this, "shipDate");
    private final ObjectProperty<Address> address = new SimpleObjectProperty<>(this, "address");
    private final SimpleStringProperty shipMode = new SimpleStringProperty(this, "shipMode");
    private final ObservableMap<String, Product> products = FXCollections.observableHashMap();
    private final SimpleBooleanProperty isReturn = new SimpleBooleanProperty(this, "isReturn");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    // Constructors
    public Order() {
        // Default constructor
    }

    public Order(String orderId, LocalDate orderDate, LocalDate shipDate, Address address, String shipMode,
            HashMap<String, Product> products) {
        setOrderId(orderId);
        setOrderDate(orderDate);
        setShipDate(shipDate);
        setAddress(address);
        setShipMode(shipMode);
        setProducts(products);
    }

    public StringProperty orderIdProperty() {
        return orderId;
    }

    public String getOrderId() {
        return orderId.get();
    }

    public StringProperty orderDateProperty() {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.bindBidirectional(orderDate, new LocalDateStringConverter());
        return stringProperty;
    }

    public String getOrderDate() {
        return orderDate.get().format(formatter);
    }

    public StringProperty shipDateProperty() {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.bindBidirectional(shipDate, new LocalDateStringConverter());
        return stringProperty;
    }

    public String getShipDate() {
        return shipDate.get().format(formatter);
    }

    public Address getAddress() {
        return address.get();
    }

    public String getShipMode() {
        return shipMode.get();
    }

    public ObservableMap<String, Product> getProducts() {
        return products;
    }

    public boolean getIsReturn() {
        return isReturn.get();
    }

    public void setOrderId(String orderId) {
        this.orderId.set(orderId);
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate.set(orderDate);
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate.set(shipDate);
    }

    public void setAddress(Address address) {
        this.address.set(address);
    }

    public void setShipMode(String shipMode) {
        this.shipMode.set(shipMode);
    }

    public void setProducts(HashMap<String, Product> products) {
        if (products != null) {
            this.products.clear();
            this.products.putAll(products);
        } else {
            this.products.clear();
        }
    }

    public void setIsReturn(boolean isReturn) {
        this.isReturn.set(isReturn);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId.get() +
                "\nOrder Date: " + orderDate.get() +
                "\nShip Date: " + shipDate.get() +
                "\nAddress: " + address.get() +
                "\nShip Mode: " + shipMode.get() +
                "\nProducts: " + products.size() +
                "\nReturn: " + isReturn.get();
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }
}
