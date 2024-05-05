
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;

public class OrderTest {

    @Test
    public void testOrderCreation() {
        Address address = new Address("USA", 12345, "Midwest", "IL", "Chicago");
        HashMap<String, Product> products = new HashMap<>();
        products.put("001", new Product("001", "Laptop", new CategoryInfo("Electronics", "Computers"), 1200.0, 3, 0.1, 100.0));
        Order order = new Order("ORD001", LocalDate.parse("2021-04-03"), LocalDate.parse("2021-04-05"), address, "Standard", products);

        assertAll("Order should be created with all properties set correctly",
            () -> assertEquals("ORD001", order.getOrderId()),
            () -> assertEquals("2021-04-03", order.getOrderDate()),
            () -> assertEquals("2021-04-05", order.getShipDate()),
            () -> assertNotNull(order.getAddress()),
            () -> assertEquals("Standard", order.getShipMode()),
            () -> assertEquals(1, order.getProducts().size())
        );
    }

    @Test
    public void testSetAndGetOrderId() {
        Order order = new Order();
        order.setOrderId("ORD002");
        assertEquals("ORD002", order.getOrderId());
    }

    @Test
    public void testSetAndGetOrderDate() {
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("2021-04-10"));
        assertEquals("2021-04-10", order.getOrderDate());
    }

    @Test
    public void testSetAndGetShipDate() {
        Order order = new Order();
        order.setShipDate(LocalDate.parse("2021-04-12"));
        assertEquals("2021-04-12", order.getShipDate());
    }

    @Test
    public void testSetAndGetAddress() {
        Order order = new Order();
        Address address = new Address("Canada", 54321, "East", "ON", "Toronto");
        order.setAddress(address);
        assertEquals("Toronto", order.getAddress().getCity());
    }

    @Test
    public void testSetAndGetShipMode() {
        Order order = new Order();
        order.setShipMode("Expedited");
        assertEquals("Expedited", order.getShipMode());
    }

    @Test
    public void testAddProduct() {
        Order order = new Order();
        Product product = new Product("002", "Smartphone", new CategoryInfo("Electronics", "Mobile"), 999.99, 5, 0.2, 50.0);
        order.addProduct(product);
        assertEquals(1, order.getProducts().size());
        assertEquals(product, order.getProducts().get("002"));
    }

    @Test
    public void testSetAndGetIsReturn() {
        Order order = new Order();
        order.setIsReturn(true);
        assertTrue(order.getIsReturn());
    }
}
