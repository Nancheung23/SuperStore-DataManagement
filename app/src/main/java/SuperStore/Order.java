package SuperStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * Represents an order with details such as order ID, order date, ship date,
 * shipping address, shipping mode, and a collection of ordered products.
 */
public class Order {
    private String orderId;
    private LocalDate orderDate;
    private LocalDate shipDate;
    private Address address;
    private String shipMode;
    private HashSet<Product> products;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs an Order with the specified details.
     *
     * @param orderId   Unique identifier for the order.
     * @param orderDate Date the order was placed.
     * @param shipDate  Date the order is scheduled to ship.
     * @param address   Shipping address for the order.
     * @param shipMode  Mode of shipment.
     * @param products  Set of products in the order.
     */
    public Order(final String orderId, final LocalDate orderDate, final LocalDate shipDate, final Address address, final String shipMode,
            HashSet<Product> products) {
        setOrderId(orderId);
        setOrderDate(orderDate);
        setShipDate(shipDate);
        setAddress(address);
        setShipMode(shipMode);
        setProducts(products);
    }

    /**
     * Default constructor for Order.
     */    
    public Order() {

    }

    /**
     * Gets the order ID.
     *
     * @return The order ID.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId The order ID.
     * @throws IllegalArgumentException If orderId is null or empty.
     */
    public void setOrderId(final String orderId) {
        if ((orderId == null) || (orderId.isEmpty())) {
            throw new IllegalArgumentException("orderId cannot be null or empty");
        }
        this.orderId = orderId;
    }

    /**
     * Gets the order date formatted as a String.
     *
     * @return The order date.
     */
    public String getOrderDate() {
        return dateTimeFormatter.format(orderDate);
    }

    /**
     * Sets the order date.
     *
     * @param orderDate The order date.
     * @throws IllegalArgumentException If orderDate is null.
     */
    public void setOrderDate(final LocalDate orderDate) {
        if (orderDate == null) {
            throw new IllegalArgumentException("orderDate cannot be null");
        }
        this.orderDate = orderDate;
    }

    /**
     * Gets the ship date formatted as a String.
     *
     * @return The ship date.
     */
    public String getShipDate() {
        return dateTimeFormatter.format(shipDate);
    }

    /**
     * Sets the ship date.
     *
     * @param shipDate The ship date.
     * @throws IllegalArgumentException If shipDate is null.
     */
    public void setShipDate(final LocalDate shipDate) {
        if (shipDate == null) {
            throw new IllegalArgumentException("shipDate cannot be null");
        }
        this.shipDate = shipDate;
    }

    /**
     * Gets the address associated with this order.
     *
     * @return The address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address for this order.
     *
     * @param address The shipping address.
     * @throws IllegalArgumentException If address is null.
     */
    public void setAddress(final Address address) {
        if (address == null) {
            throw new IllegalArgumentException("address cannot be null");
        }
        this.address = address;
    }

    /**
     * Gets the shipping mode for this order.
     *
     * @return The shipping mode.
     */
    public String getShipMode() {
        return shipMode;
    }

    /**
     * Sets the shipping mode for this order.
     *
     * @param shipMode The shipping mode.
     * @throws IllegalArgumentException If shipMode is null or empty.
     */
    public void setShipMode(final String shipMode) {
        if ((shipMode == null) || (shipMode.isEmpty())) {
            throw new IllegalArgumentException("shipMode cannot be null or empty");
        }
        this.shipMode = shipMode;
    }

    /**
     * Gets the set of products in this order.
     *
     * @return The set of products.
     */
    public HashSet<Product> getProducts() {
        return products;
    }

    /**
     * Sets the set of products for this order.
     *
     * @param products The set of products.
     */
    public void setProducts(final HashSet<Product> products) {
        if (products == null) {
            this.products = new HashSet<>(); // Corrected to assign a new HashSet to the class field
        } else {
            this.products = products;
        }
    }
}
