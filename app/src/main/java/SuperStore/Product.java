package SuperStore;

/**
 * Represents a product with details such as ID, name, category,
 * sales, quantity, discount, and profit.
 */
public class Product {
    private String productId;
    private String productName;
    private CategoryInfo category;
    private double sales;
    private int quantity;
    private double discount;
    private double profit;

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
    public void setProductId(final String productId) {
        if ((productId == null) || (productId.equals(""))) {
            throw new IllegalArgumentException("productId cannot be null or empty");
        }
        if (productId.length() < 15) {
            throw new IllegalArgumentException("productId has to have 15 letters");
        }
        this.productId = productId;
    }

    /**
     * Gets the product name.
     *
     * @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName The name of the product.
     * @throws IllegalArgumentException if productName is null or empty.
     */
    public void setProductName(final String productName) {
        if ((productName == null) || (productName.equals(""))) {
            throw new IllegalArgumentException("productName cannot be null or empty");
        }
        this.productName = productName;
    }

    /**
     * Gets the category of the product.
     *
     * @return The category information of the product.
     */
    public CategoryInfo getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category The category information of the product.
     * @throws IllegalArgumentException if category is null.
     */
    public void setCategory(final CategoryInfo category) {
        if (category == null) {
            throw new IllegalArgumentException("category cannot be null");
        }
        this.category = category;
    }

    /**
     * Gets the total sales amount for the product.
     *
     * @return The sales amount.
     */
    public double getSales() {
        return sales;
    }

    /**
     * Sets the total sales amount for the product.
     *
     * @param sales The sales amount.
     * @throws IllegalArgumentException if sales is less than or equal to 0.
     */
    public void setSales(final double sales) {
        if (sales <= 0) {
            throw new IllegalArgumentException("sales cannot be lower than 0");
        }
        this.sales = sales;
    }

    /**
     * Gets the quantity of the product sold.
     *
     * @return The quantity sold.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product sold.
     *
     * @param quantity The quantity sold.
     * @throws IllegalArgumentException if quantity is less than or equal to 0.
     */
    public void setQuantity(final int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity cannot be lower than 0");
        }
        this.quantity = quantity;
    }

    /**
     * Gets the discount applied to the product.
     *
     * @return The discount percentage.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Sets the discount applied to the product.
     *
     * @param discount The discount percentage.
     * @throws IllegalArgumentException if discount is less than 0 or greater than 1
     *                                  (100%).
     */
    public void setDiscount(final double discount) {
        if (discount <= 0) {
            throw new IllegalArgumentException("discount cannot be lower than 0");
        }
        this.discount = discount;
    }

    /**
     * Gets the profit earned from the product.
     *
     * @return The profit amount.
     */
    public double getProfit() {
        return profit;
    }

    /**
     * Sets the profit earned from the product.
     *
     * @param profit The profit amount.
     */
    public void setProfit(final double profit) {
        this.profit = profit;
    }
}
