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
}
