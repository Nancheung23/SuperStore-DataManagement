package SuperStore;

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

    public static Product geProductById(HashMap<String, Customer> customerMap, String productId) {
        return customerMap.values().stream().flatMap(c -> c.getOrders().values().stream())
        .flatMap(o -> o.getProducts().values().stream())
        .filter(p -> p.getProductId().equals(productId))
        .findFirst().orElse(null);
    }
}
