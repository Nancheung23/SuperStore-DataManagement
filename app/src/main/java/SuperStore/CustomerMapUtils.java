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

    public static HashMap<String, Integer> getAmountOfSegment(HashMap<String, Customer> customerMap) {
        HashMap<String, Integer> segmentMap = new HashMap<>();
        customerMap.values().forEach(c -> {
            String segment = c.getSegment();
            if (segmentMap.containsKey(segment)) {
                segmentMap.put(segment, segmentMap.get(segment) + 1);
            } else {
                segmentMap.put(segment, 1);
            }
        });
        return segmentMap;
    }

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
}
