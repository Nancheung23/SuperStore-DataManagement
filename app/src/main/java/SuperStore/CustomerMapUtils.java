package SuperStore;

import java.util.Comparator;
import java.util.HashMap;

public class CustomerMapUtils {
    private CustomerMapUtils() {

    }

    public static int calculateCustomersNumber(HashMap<String, Customer> customerMap) {
        return customerMap.size();
    }

    public static int calculateOrdersNumber(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream().mapToInt(c -> c.getOrders().size()).sum();
    }

    public static int calculateProductsNumber(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream()
                .flatMap(c -> c.getOrders().values().stream()).mapToInt(o -> o.getProducts().size()).sum();
    }

    public static Customer getCustomerById(HashMap<String, Customer> customerMap, String customerId) {
        return customerMap.get(customerId);
    }

    public static Order getOrderById(HashMap<String, Customer> customerMap, String orderId) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .filter(o -> o.getOrderId().equals(orderId))
        .findFirst().orElse(null);
    }

    public static Product getProductById(HashMap<String, Customer> customerMap, String productId) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .flatMap(o -> o.getProducts().values().stream())
        .filter(p -> p.getProductId().equals(productId))
        .findFirst().orElse(null);
    }

    public static double getTotalSales(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .flatMap(o -> o.getProducts().values().stream())
        .mapToDouble(p -> p.getSales()).sum();
    }

    public static double getTotalSalesForOrder(HashMap<String, Customer> customerMap, String orderId) {
        Order order = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .filter(o -> o.getOrderId().equals(orderId))
        .findFirst().orElse(null);
        return order.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum();
    }

    public static double getAverageSalesForOrder(HashMap<String, Customer> customerMap, String orderId) {
        Order order = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .filter(o -> o.getOrderId().equals(orderId))
        .findFirst().orElse(null);
        return order.getProducts().values().stream().mapToDouble(p -> p.getSales()).sum() / order.getProducts().size();
    }

    public static double getAverageSalesForAllOrders(HashMap<String, Customer> customerMap) {
        double total = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .flatMap(o -> o.getProducts().values().stream())
        .mapToDouble(p -> p.getSales()).sum();
        long totalOrders = customerMap.values().stream().flatMap(c -> c.getOrders().values().stream()).count();
        return total / totalOrders;
    }

    public static Customer getBestCustomer(HashMap<String, Customer> customerMap) {
        return customerMap.values().stream()
                .max(Comparator.comparingDouble(c -> getTotalSalesForCustomer(customerMap, c.getCustomerId())))
                .orElse(null); 
    }
    
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
}
