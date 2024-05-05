
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class CustomerMapUtilsTest {

    @Test
    void testCalculateCustomersNumber() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        customerMap.put("1", new Customer());
        customerMap.put("2", new Customer());

        int result = CustomerMapUtils.calculateCustomersNumber(customerMap);
        assertEquals(2, result);
    }

    @Test
    void testCalculateOrdersNumber() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        Customer customer1 = new Customer();
        customer1.addOrder(new Order());
        customer1.addOrder(new Order());
        Customer customer2 = new Customer();
        customer2.addOrder(new Order());

        customerMap.put("1", customer1);
        customerMap.put("2", customer2);

        int result = CustomerMapUtils.calculateOrdersNumber(customerMap);
        assertEquals(3, result);
    }

    @Test
    void testCalculateProductsNumber() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        Customer customer = new Customer();
        Order order = new Order();
        order.addProduct(new Product());
        order.addProduct(new Product());
        customer.addOrder(order);

        customerMap.put("1", customer);

        int result = CustomerMapUtils.calculateProductsNumber(customerMap);
        assertEquals(2, result);
    }

    @Test
    void testGetCustomerById() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        Customer customer = new Customer();
        customer.setId("123");
        customerMap.put("123", customer);

        Customer result = CustomerMapUtils.getCustomerById(customerMap, "123");
        assertEquals(customer, result);
        assertNull(CustomerMapUtils.getCustomerById(customerMap, "999")); // Test for a non-existent ID
    }

    @Test
    void testGetTotalSalesPerFilter() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        Customer customer = new Customer();
        Order order = new Order();
        Address address = new Address();
        address.setRegion("East");
        order.setAddress(address);
        Product product = new Product();
        product.setSales(150.0);
        order.addProduct(product);
        customer.addOrder(order);
        customerMap.put("1", customer);

        HashMap<String, Double> salesMap = CustomerMapUtils.getTotalSalesPerFilter(customerMap, "region");
        assertEquals(150.0, salesMap.get("East"));
    }
}
