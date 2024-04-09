package SuperStore;

/**
 * Represents a postal address, including details such as country, postal code,
 * region, state, and city.
 */
public class Address {
    private String country;
    private int postalCode;
    private String region;
    private String state;
    private String city;

    /**
     * Constructs a new Address with the specified country, postal code, region,
     * state, and city.
     *
     * @param country    The country of the address.
     * @param postalCode The postal code of the address.
     * @param region     The region of the address.
     * @param state      The state of the address.
     * @param city       The city of the address.
     * @throws IllegalArgumentException If any parameter is null or does not meet
     *                                  format requirements.
     */
    public Address(final String country, final int postalCode, final String region, final String state, final String city) {
        setCountry(country);
        setPostalCode(postalCode);
        setRegion(region);
        setState(state);
        setCity(city);
    }

    /**
     * Default constructor for Address.
     */
    public Address() {
        // Default constructor for creating an instance of Address without setting
        // fields initially.
    }

    /**
     * Gets the country of the address.
     *
     * @return The country of the address.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the address.
     *
     * @param country The country of the address.
     * @throws IllegalArgumentException If country is null or empty.
     */
    public void setCountry(final String country) {
        if ((country == null) || (country.isEmpty())) {
            throw new IllegalArgumentException("country cannot be null or empty");
        }
        this.country = country;
    }

    /**
     * Gets the postal code of the address.
     *
     * @return The postal code of the address.
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address.
     *
     * @param postalCode The postal code of the address.
     */
    public void setPostalCode(final int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the region of the address.
     *
     * @return The region of the address.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region of the address.
     *
     * @param region The region of the address.
     * @throws IllegalArgumentException If region is null or empty.
     */
    public void setRegion(final String region) {
        if ((region == null) || (region.isEmpty())) {
            throw new IllegalArgumentException("region cannot be null or empty");
        }
        this.region = region;
    }

    /**
     * Gets the state of the address.
     *
     * @return The state of the address.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state The state of the address.
     * @throws IllegalArgumentException If state is null or empty.
     */
    public void setState(final String state) {
        if ((state == null) || (state.isEmpty())) {
            throw new IllegalArgumentException("state cannot be null or empty");
        }
        this.state = state;
    }

    /**
     * Gets the city of the address.
     *
     * @return The city of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city The city of the address.
     * @throws IllegalArgumentException If city is null or empty.
     */
    public void setCity(final String city) {
        if ((city == null) || (city.isEmpty())) {
            throw new IllegalArgumentException("city cannot be null or empty");
        }
        this.city = city;
    }

    @Override
    public String toString() {
        return "Country:" + this.country +
        "\nState:" + this.state +  
        "\nRegion:" + this.region + 
        "\nCity:" + this.city + 
        "\nPostalCode:" + this.postalCode;
    }
}
