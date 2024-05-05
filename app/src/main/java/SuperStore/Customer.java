package SuperStore;

import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * Represents a customer with their ID, name, segment, and a collection of
 * orders.
 */
public class Customer {
    private final SimpleStringProperty customerId = new SimpleStringProperty(this, "customerId");
    private final SimpleStringProperty customerName = new SimpleStringProperty(this, "customerName");
    private final SimpleStringProperty segment = new SimpleStringProperty(this, "segment");
    private final ObservableMap<String, Order> ordersMap = FXCollections.observableHashMap();

    /**
     * Constructs a Customer with the specified ID, name, and segment.
     *
     * @param customerId   Unique identifier for the customer.
     * @param customerName Name of the customer.
     * @param segment      Segment the customer belongs to.
     * @throws IllegalArgumentException If any string parameter is null or empty.
     */
    public Customer(String customerId, String customerName, String segment) {
        setCustomerId(customerId);
        setCustomerName(customerName);
        setSegment(segment);
    }

    public Customer() {

    }

    // Default constructor and other overloaded constructors as needed

    // Property getters
    public StringProperty customerIdProperty() {
        return customerId;
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public StringProperty segmentProperty() {
        return segment;
    }

    // Regular getters and setters
    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getSegment() {
        return segment.get();
    }

    public void setSegment(String segment) {
        this.segment.set(segment);
    }

    public ObservableMap<String, Order> getOrders() {
        return ordersMap;
    }

    public void setOrders(HashMap<String, Order> orders) {
        if (orders != null) {
            this.ordersMap.clear();
            this.ordersMap.putAll(orders);
        }
    }

    public void addOrder(Order order) {
        ordersMap.put(order.getOrderId(), order);
    }

    public void setId(String string) {
        this.customerId.set(string);
    }

    // ToString, hashCode, equals as needed
}
