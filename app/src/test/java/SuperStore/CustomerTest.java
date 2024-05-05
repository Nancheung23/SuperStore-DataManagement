
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.time.LocalDate;

public class CustomerTest {

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer("C001", "John Doe", "Corporate");
        assertAll("Customer should be created with all properties set correctly",
            () -> assertEquals("C001", customer.getCustomerId()),
            () -> assertEquals("John Doe", customer.getCustomerName()),
            () -> assertEquals("Corporate", customer.getSegment())
        );
    }

    @Test
    public void testSetAndGetCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("C002");
        assertEquals("C002", customer.getCustomerId());
    }

    @Test
    public void testSetAndGetCustomerName() {
        Customer customer = new Customer();
        customer.setCustomerName("Alice Smith");
        assertEquals("Alice Smith", customer.getCustomerName());
    }

    @Test
    public void testSetAndGetSegment() {
        Customer customer = new Customer();
        customer.setSegment("Government");
        assertEquals("Government", customer.getSegment());
    }

    @Test
    public void testAddAndGetOrders() {
        Customer customer = new Customer();
        Order order1 = new Order("ORD001", LocalDate.parse("2021-05-10"), LocalDate.parse("2021-05-12"), new Address("USA", 12345, "Midwest", "IL", "Chicago"), "Standard", new HashMap<>());
        customer.addOrder(order1);
        assertEquals(1, customer.getOrders().size());
        assertEquals(order1, customer.getOrders().get("ORD001"));

        Order order2 = new Order("ORD002", LocalDate.parse("2021-06-15"), LocalDate.parse("2021-06-17"), new Address("Canada", 54321, "East", "ON", "Toronto"), "Express", new HashMap<>());
        customer.addOrder(order2);
        assertEquals(2, customer.getOrders().size());
        assertEquals(order2, customer.getOrders().get("ORD002"));
    }
}
