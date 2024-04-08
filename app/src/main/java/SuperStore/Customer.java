package SuperStore;

import java.util.HashMap;

/**
 * Represents a customer with their ID, name, segment, and a collection of
 * orders.
 */
public class Customer {
    private String customerId;
    private String customerName;
    private String segment;
    private HashMap<String,Order> ordersMap;

    /**
     * Constructs a Customer with the specified ID, name, segment, and orders.
     *
     * @param customerId   Unique identifier for the customer.
     * @param customerName Name of the customer.
     * @param segment      Segment the customer belongs to.
     * @param orders       Map of orders associated with the customer.
     * @throws IllegalArgumentException If any string parameter is null or empty.
     */
    public Customer(final String customerId, final String customerName, final String segment,
            final HashMap<String,Order> ordersMap) {
        setCustomerId(customerId);
        setCustomerName(customerName);
        setSegment(segment);
        setOrders(ordersMap);
    }

    /**
     * Default constructor for creating a Customer instance without setting fields
     * initially.
     */
    public Customer() {
        // Intentionally empty.
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId The unique identifier for the customer.
     * @throws IllegalArgumentException If customerId is null or empty.
     */
    public void setCustomerId(final String customerId) {
        if ((customerId == null) || (customerId.isEmpty())) {
            throw new IllegalArgumentException("customerId cannot be null or empty");
        }
        this.customerId = customerId;
    }

    /**
     * Gets the customer name.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName The name of the customer.
     * @throws IllegalArgumentException If customerName is null or empty.
     */
    public void setCustomerName(final String customerName) {
        if ((customerName == null) || (customerName.isEmpty())) {
            throw new IllegalArgumentException("customerName cannot be null or empty");
        }
        this.customerName = customerName;
    }

    /**
     * Gets the segment of the customer.
     *
     * @return The customer's segment.
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the segment of the customer.
     *
     * @param segment The segment the customer belongs to.
     * @throws IllegalArgumentException If segment is null or empty.
     */
    public void setSegment(final String segment) {
        if ((segment == null) || (segment.isEmpty())) {
            throw new IllegalArgumentException("segment cannot be null or empty");
        }
        this.segment = segment;
    }

    /**
     * Gets the Map of orders associated with the customer.
     *
     * @return The customer's orders.
     */
    public HashMap<String,Order> getOrders() {
        return ordersMap;
    }

    /**
     * Sets the orders associated with the customer.
     *
     * @param orders The Map of orders for the customer.
     */
    public void setOrders(HashMap<String,Order> orders) {
        if (orders == null) {
            this.ordersMap = new HashMap<>();
        } else {
            this.ordersMap = orders;
        }
    }
}
