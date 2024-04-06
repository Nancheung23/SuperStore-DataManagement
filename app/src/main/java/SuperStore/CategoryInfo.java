package SuperStore;
/**
 * Represents a product category with a main category and a sub-category.
 * <p>
 * This record is an immutable data carrier and should be used to represent
 * static category information within an application.
 *
 * @param category The name of the main category.
 * @param subCategory The name of the sub-category.
 */
public record CategoryInfo(String category, String subCategory) {
    /**
     * Constructs a new {@code CategoryInfo} instance.
     *
     * @param category The name of the main category, not null or empty.
     * @param subCategory The name of the sub-category, not null or empty.
     */
    public CategoryInfo {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (subCategory == null || subCategory.isBlank()) {
            throw new IllegalArgumentException("Sub-category name cannot be null or empty");
        }
    }
}
