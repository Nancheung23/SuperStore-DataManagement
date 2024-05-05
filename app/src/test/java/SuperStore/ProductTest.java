
package SuperStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testProductCreation() {
        CategoryInfo categoryInfo = new CategoryInfo("Electronics", "Mobile");
        Product product = new Product("001", "TestProduct", categoryInfo, 299.99, 10, 0.05, 50.0);
        assertAll("Product should be created with all properties set correctly",
            () -> assertEquals("001", product.getProductId()),
            () -> assertEquals("TestProduct", product.getProductName()),
            () -> assertEquals("Electronics", product.getCategory().category()),
            () -> assertEquals("Mobile", product.getCategory().subCategory()),
            () -> assertEquals(299.99, product.getSales()),
            () -> assertEquals(10, product.getQuantity()),
            () -> assertEquals(0.05, product.getDiscount()),
            () -> assertEquals(50.0, product.getProfit())
        );
    }

    @Test
    public void testSetAndGetProductId() {
        Product product = new Product();
        product.setProductId("002");
        assertEquals("002", product.getProductId());
    }

    @Test
    public void testSetAndGetProductName() {
        Product product = new Product();
        product.setProductName("TestProductNew");
        assertEquals("TestProductNew", product.getProductName());
    }

    @Test
    public void testSetAndGetCategory() {
        Product product = new Product();
        CategoryInfo category = new CategoryInfo("Books", "Textbooks");
        product.setCategory(category);
        assertAll("Category should be set and retrieved correctly",
            () -> assertEquals("Books", product.getCategory().category()),
            () -> assertEquals("Textbooks", product.getCategory().subCategory())
        );
    }

    @Test
    public void testSetAndGetSales() {
        Product product = new Product();
        product.setSales(450.0);
        assertEquals(450.0, product.getSales());
    }

    @Test
    public void testSetAndGetQuantity() {
        Product product = new Product();
        product.setQuantity(5);
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testSetAndGetDiscount() {
        Product product = new Product();
        product.setDiscount(0.1);
        assertEquals(0.1, product.getDiscount());
    }

    @Test
    public void testSetAndGetProfit() {
        Product product = new Product();
        product.setProfit(100.0);
        assertEquals(100.0, product.getProfit());
    }
}
