package SuperStore;

import java.util.Comparator;
import java.util.HashMap;
import com.google.common.base.Function;

/**
 * Utility class for operations on customer data maps in a superstore context.
 * Provides static methods to analyze and retrieve aggregated data from
 * collections
 * of customers, orders, and products.
 */
public class CustomerMapUtils {

    private CustomerMapUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the total number of customers in the customer map.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The total number of customers.
     */
    public static int calculateCustomersNumber(HashMap<String, Customer> customerMap) {
        return customerMap.size();
    }

    /**
     * Calculates the total number of orders across all customers.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The total number of orders.
     */
    public static int calculateOrdersNumber(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream().mapToInt(c -> c.getOrders().size()).sum();
    }

    /**
     * Calculates the total number of products across all orders and customers.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The total number of products.
     */
    public static int calculateProductsNumber(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream()
                .flatMap(c -> c.getOrders().values().stream()).mapToInt(o -> o.getProducts().size()).sum();
    }

    /**
     * Retrieves a Customer object by its ID.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param customerId  The ID of the customer to retrieve.
     * @return The Customer object associated with the given ID, or null if not
     *         found.
     */
    public static Customer getCustomerById(HashMap<String, Customer> customerMap, String customerId) {
        return customerMap.get(customerId);
    }

    /**
     * Retrieves an Order object by its ID, searching across all customers.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param orderId     The ID of the order to retrieve.
     * @return The Order object associated with the given ID, or null if not found.
     */
    public static Order getOrderById(HashMap<String, Customer> customerMap, String orderId) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst().orElse(null);
    }

    /**
     * Retrieves a Product object by its ID, searching across all orders and
     * customers.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param productId   The ID of the product to retrieve.
     * @return The Product object associated with the given ID, or null if not
     *         found.
     */
    public static Product getProductById(HashMap<String, Customer> customerMap, String productId) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .flatMap(o -> o.getProducts().values().stream())
                .filter(p -> p.getProductId().equals(productId))
                .findFirst().orElse(null);
    }

    /**
     * Calculates the total sales amount across all orders and customers.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The total sales amount.
     */
    public static double getTotalSales(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .flatMap(o -> o.getProducts().values().stream())
                .mapToDouble(p -> p.getSales()).sum();
    }

    /**
     * Calculates the total sales amount for specific order.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param orderId     The value of order ID
     * @return The total sales amount for one order.
     */
    public static double getTotalSalesForOrder(HashMap<String, Customer> customerMap, String orderId) {
        Order order = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst().orElse(null);
        return order.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
    }

    /**
     * Calculates the average sales amount for specific order.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param orderId     The value of order ID
     * @return The average sales amount for one order.
     */
    public static double getAverageSalesForOrder(HashMap<String, Customer> customerMap, String orderId) {
        Order order = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst().orElse(null);
        return order.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum() / order.getProducts().size();
    }

    /**
     * Calculates the average sales amount all orders.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The average sales amount for all orders.
     */
    public static double getAverageSalesForAllOrders(HashMap<String, Customer> customerMap) {
        double total = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
                .flatMap(o -> o.getProducts().values().stream())
                .mapToDouble(p -> p.getSales()).sum();
        long totalOrders = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream()).count();
        return total / totalOrders;
    }

    /**
     * Identifies the customer with the highest total sales across all their orders.
     * This method calculates the total sales for each customer and returns the one
     * with the maximum sales amount.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return The Customer object with the highest total sales, or null if the map
     *         is empty.
     */
    public static Customer getBestCustomer(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream()
                .max(Comparator.comparingDouble(c -> getTotalSalesForCustomer(customerMap, c.getCustomerId())))
                .orElse(null);
    }

    /**
     * Calculates the total sales for a given customer based on all their orders.
     * This method sums up the sales amount of all products across all orders made
     * by the customer.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param customerId  The ID of the customer for whom total sales is to be
     *                    calculated.
     * @return The total sales amount for the specified customer.
     */
    public static double getTotalSalesForCustomer(HashMap<String, Customer> customerMap, String customerId) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            return 0;
        }
        return customer.getOrders().values().stream()
                .flatMap(order -> order.getProducts().values().stream())
                .mapToDouble(Product::getSales)
                .sum();
    }

    /**
     * Counts the number of customers based on a specified attribute of their
     * address.
     * The attribute can be country, region, state, city, or postal code. This
     * method
     * goes through all orders of all customers to count how many times each unique
     * address attribute appears.
     *
     * @param customerMap   The map of customer IDs to Customer objects.
     * @param addressFilter The address attribute to count by (e.g., "Country",
     *                      "State").
     * @return A map of address attribute values to their corresponding customer
     *         count.
     */
    public static HashMap<String, Integer> getAmountCustomerPerFilter(HashMap<String, Customer> customerMap,
            String addressFilter) {
        HashMap<String, Integer> customorsPerFilter = new HashMap<>();
        switch (addressFilter) {
            case "Country":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getCountry();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
            case "Region":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getRegion();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
            case "State":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getState();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
            case "City":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getCity();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
            case "PostalCode":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = "" + o.getAddress().getPostalCode();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
            default:
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getCity();
                        if (customorsPerFilter.containsKey(key)) {
                            customorsPerFilter.put(key, customorsPerFilter.get(key) + 1);
                        } else {
                            customorsPerFilter.put(key, 1);
                        }
                    }
                });
                break;
        }
        return customorsPerFilter;
    }

    /**
     * A generic method to count customers based on a specific attribute of their
     * orders.
     * This method reduces code duplication by accepting a function that extracts
     * the
     * attribute of interest from an order.
     *
     * @param customerMap        The map of customer IDs to Customer objects.
     * @param attributeExtractor A function that extracts the desired attribute from
     *                           an Order object.
     * @return A map of attribute values to their corresponding customer count.
     */
    // Example usage:
    // Get the number of customers per country
    // HashMap<String, Integer> customersPerCountry =
    // getAmountCustomerPerAttribute(customerMap, order ->
    // order.getAddress().getCountry());

    // Get the number of customers per state
    // HashMap<String, Integer> customersPerState =
    // getAmountCustomerPerAttribute(customerMap, order ->
    // order.getAddress().getState());
    public static HashMap<String, Integer> getAmountCustomerPerAttribute(HashMap<String, Customer> customerMap,
            Function<Order, String> attributeExtractor) {
        HashMap<String, Integer> customorsPerFilter = new HashMap<>();
        customerMap.values().forEach(c -> {
            for (Order o : c.getOrders().values()) {
                // attribute extractor
                String key = attributeExtractor.apply(o);
                // map.merge
                customorsPerFilter.merge(key, 1, Integer::sum);
            }
        });
        return customorsPerFilter;
    }

    /**
     * Counts the number of customers in each segment.
     * This method categorizes customers based on their segment and counts the
     * number
     * of customers in each category.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @return A map of segments to their corresponding customer count.
     */
    public static HashMap<String, Integer> getAmountOfSegment(HashMap<String, Customer> customerMap) {
        HashMap<String, Integer> segmentMap = new HashMap<>();
        customerMap.values().forEach(c -> {
            String segment = c.getSegment();
            // if (segmentMap.containsKey(segment)) {
            //     segmentMap.put(segment, segmentMap.get(segment) + 1);
            // } else {
            //     segmentMap.put(segment, 1);
            // }
            segmentMap.merge(segment, 1, Integer::sum);
        });
        return segmentMap;
    }

    /**
     * Calculates the total sales per specified filter.
     * The filter can be based on attributes like year, month, or region. This
     * method
     * aggregates the sales amounts based on the specified filter criterion.
     *
     * @param customerMap The map of customer IDs to Customer objects.
     * @param Filter      The criterion to aggregate sales by (e.g., "Year",
     *                    "Month", "Region").
     * @return A map of filter values to their corresponding total sales amount.
     */
    public static HashMap<String, Double> getTotalSalesPerFilter(HashMap<String, Customer> customerMap, String Filter) {
        HashMap<String, Double> totalSalesPerFilterMap = new HashMap<>();
        switch (Filter) {
            case "Year":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getOrderDate().substring(0, 4);
                        Double sales = o.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
                        if (totalSalesPerFilterMap.containsKey(key)) {
                            totalSalesPerFilterMap.put(key, totalSalesPerFilterMap.get(key) + sales);
                        } else {
                            totalSalesPerFilterMap.put(key, sales);
                        }
                    }
                });
                break;
            case "Month":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getOrderDate().substring(0, 7);
                        Double sales = o.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
                        if (totalSalesPerFilterMap.containsKey(key)) {
                            totalSalesPerFilterMap.put(key, totalSalesPerFilterMap.get(key) + sales);
                        } else {
                            totalSalesPerFilterMap.put(key, sales);
                        }
                    }
                });
                break;
            case "Region":
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getRegion();
                        Double sales = o.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
                        if (totalSalesPerFilterMap.containsKey(key)) {
                            totalSalesPerFilterMap.put(key, totalSalesPerFilterMap.get(key) + sales);
                        } else {
                            totalSalesPerFilterMap.put(key, sales);
                        }
                    }
                });
                break;
            default:
                customerMap.values().forEach(c -> {
                    for (Order o : c.getOrders().values()) {
                        String key = o.getAddress().getRegion();
                        Double sales = o.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
                        if (totalSalesPerFilterMap.containsKey(key)) {
                            totalSalesPerFilterMap.put(key, totalSalesPerFilterMap.get(key) + sales);
                        } else {
                            totalSalesPerFilterMap.put(key, sales);
                        }
                    }
                });
                break;
        }
        return totalSalesPerFilterMap;
    }
    
    public static HashMap<String, Double> getTotalSalesPerAttribute(HashMap<String, Customer> customerMap, Function<Order,String> attributeExtractor) {
        HashMap<String, Double> totalSalesPerAttributeMap = new HashMap<>();
        customerMap.values().forEach(c -> {
            for(Order o : c.getOrders().values()) {
                String key = attributeExtractor.apply(o);
                Double sales = o.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
                totalSalesPerAttributeMap.merge(key, sales, Double::sum);
            }
        });
        return totalSalesPerAttributeMap;
    }
}
