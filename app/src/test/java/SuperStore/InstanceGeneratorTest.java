
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstanceGeneratorTest {

    @Test
    public void testCustomerOrderCreation() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"C001", "John Doe", "Corporate", "ORD001", "Product1", "10", "Electronic", "Computer"});
        data.add(new String[]{"C002", "Alice Smith", "Home", "ORD002", "Product2", "5", "Electronic", "Phone"});

        InstanceGenerator generator = new InstanceGenerator(data);
        generator.initialization();
        assertEquals(2, generator.getCustomerMap().size());
        assertTrue(generator.getCustomerMap().containsKey("C001"));
        assertTrue(generator.getCustomerMap().containsKey("C002"));

        Customer john = generator.getCustomerMap().get("C001");
        Customer alice = generator.getCustomerMap().get("C002");
        
        assertEquals(1, john.getOrders().size());
        assertEquals(1, alice.getOrders().size());
    }

    @Test
    public void testSetReturnMap() {
        List<String[]> returnData = new ArrayList<>();
        returnData.add(new String[]{"Yes", "ORD001"});
        returnData.add(new String[]{"No", "ORD002"});

        InstanceGenerator generator = new InstanceGenerator(new ArrayList<>());
        generator.initialization();
        generator.setReturnMap(returnData);

        assertTrue(generator.getReturnMap().get("ORD001"));
        assertNull(generator.getReturnMap().get("ORD002"));
    }

    @Test
    public void testSetOrderReturn() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"C001", "John Doe", "Corporate", "ORD001", "Product1", "10", "Electronic", "Computer"});
        List<String[]> returnData = new ArrayList<>();
        returnData.add(new String[]{"Yes", "ORD001"});

        InstanceGenerator generator = new InstanceGenerator(data);
        generator.initialization();
        generator.setReturnMap(returnData);
        generator.setOrderReturn();

        Customer customer = generator.getCustomerMap().get("C001");
        Order order = customer.getOrders().get("ORD001");
        assertTrue(order.getIsReturn());
    }
}
