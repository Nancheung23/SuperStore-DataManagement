package SuperStore;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a product with details such as ID, name, category,
 * sales, quantity, discount, and profit.
 */
public class Product {
    private final SimpleStringProperty productId = new SimpleStringProperty(this, "productId");
    private final SimpleStringProperty productName = new SimpleStringProperty(this, "productName");
    private final ObjectProperty<CategoryInfo> category = new SimpleObjectProperty<>(this, "category");
    private final SimpleDoubleProperty sales = new SimpleDoubleProperty(this, "sales");
    private final SimpleIntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
    private final SimpleDoubleProperty discount = new SimpleDoubleProperty(this, "discount");
    private final SimpleDoubleProperty profit = new SimpleDoubleProperty(this, "profit");

    /**
     * Constructs a new Product with the given details.
     *
     * @param productId   Unique identifier for the product.
     * @param productName Name of the product.
     * @param category    Category of the product.
     * @param sales       Total sales amount for the product.
     * @param quantity    Quantity of the product sold.
     * @param discount    Discount applied to the product.
     * @param profit      Profit earned from the product.
     * @throws IllegalArgumentException if any argument does not meet requirements.
     */
    public Product(final String productId, final String productName, final CategoryInfo category,
            final double sales, final int quantity, final double discount, final double profit) {
        setProductId(productId);
        setProductName(productName);
        setCategory(category);
        setSales(sales);
        setQuantity(quantity);
        setDiscount(discount);
        setProfit(profit);
    }

    /**
     * Default constructor for Product.
     */
    public Product() {

    }

    /**
     * Gets the unique identifier for the product.
     *
     * @return The product ID.
     */
    public String getProductId() {
        return productId.get();
    }

    public StringProperty productIdProperty() {
        return productId;
    }

    /**
     * Sets the unique identifier for the product.
     *
     * @param productId The product ID.
     * @throws IllegalArgumentException if productId is null or empty,
     *                                  or if productId does not have at least 15
     *                                  letters.
     */
    public void setProductId(String productId) {
        if ((productId == null) || (productId.equals(""))) {
            throw new IllegalArgumentException("productId cannot be null or empty");
        }
        if (productId.length() < 15) {
            throw new IllegalArgumentException("productId has to have 15 letters");
        }
        this.productId.set(productId);
    }

    /**
     * Gets the product name.
     *
     * @return The name of the product.
     */
    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName The name of the product.
     * @throws IllegalArgumentException if productName is null or empty.
     */
    public void setProductName(String productName) {
        if ((productName == null) || (productName.equals(""))) {
            throw new IllegalArgumentException("productName cannot be null or empty");
        }
        this.productName.set(productName);
    }

    /**
     * Gets the category of the product.
     *
     * @return The category information of the product.
     */
    public CategoryInfo getCategory() {
        return category.get();
    }

    /**
     * Sets the category of the product.
     *
     * @param category The category information of the product.
     * @throws IllegalArgumentException if category is null.
     */
    public void setCategory(CategoryInfo category) {
        if (category == null) {
            throw new IllegalArgumentException("category cannot be null");
        }
        this.category.set(category);
    }

    /**
     * Gets the total sales amount for the product.
     *
     * @return The sales amount.
     */
    public double getSales() {
        return sales.get();
    }

    public DoubleProperty salesProperty() {
        return sales;
    }

    /**
     * Sets the total sales amount for the product.
     *
     * @param sales The sales amount.
     * @throws IllegalArgumentException if sales is less than or equal to 0.
     */
    public void setSales(double sales) {
        if (sales <= 0) {
            throw new IllegalArgumentException("sales cannot be lower than 0");
        }
        this.sales.set(sales);
    }

    /**
     * Gets the quantity of the product sold.
     *
     * @return The quantity sold.
     */
    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    /**
     * Sets the quantity of the product sold.
     *
     * @param quantity The quantity sold.
     * @throws IllegalArgumentException if quantity is less than or equal to 0.
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity cannot be lower than 0");
        }
        this.quantity.set(quantity);
    }

    /**
     * Gets the discount applied to the product.
     *
     * @return The discount percentage.
     */
    public double getDiscount() {
        return discount.get();
    }

    public DoubleProperty discounProperty() {
        return discount;
    }

    /**
     * Sets the discount applied to the product.
     *
     * @param discount The discount percentage.
     */
    public void setDiscount(double discount) {
        this.discount.set(discount);
    }

    /**
     * Gets the profit earned from the product.
     *
     * @return The profit amount.
     */
    public double getProfit() {
        return profit.get();
    }

    public DoubleProperty profitProperty() {
        return profit;
    }

    /**
     * Sets the profit earned from the product.
     *
     * @param profit The profit amount.
     */
    public void setProfit(double profit) {
        this.profit.set(profit);
    }

    @Override
    public String toString() {
        return "Id:" + getProductId() + 
        "\nName:" + getProductName() + 
        "\nCategory:" + getCategory() + 
        "\nSales:" + getSales() +
        "\nQuantity:" + getQuantity() +
        "\nDiscount:" + getDiscount() +
        "\nProfit:" + getProfit()
        ;
    }
}
