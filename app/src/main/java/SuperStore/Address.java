package SuperStore;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a postal address, including details such as country, postal code,
 * region, state, and city.
 */
public class Address {
    private final SimpleStringProperty country = new SimpleStringProperty(this, "country");
    private final SimpleIntegerProperty postalCode = new SimpleIntegerProperty(this, "postalCode");
    private final SimpleStringProperty region = new SimpleStringProperty(this, "region");
    private final SimpleStringProperty state = new SimpleStringProperty(this, "state");
    private final SimpleStringProperty city = new SimpleStringProperty(this, "city");

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
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    /**
     * Sets the country of the address.
     *
     * @param country The country of the address.
     * @throws IllegalArgumentException If country is null or empty.
     */
    public void setCountry(String country) {
        if ((country == null) || (country.isEmpty())) {
            throw new IllegalArgumentException("country cannot be null or empty");
        }
        this.country.set(country);
    }

    /**
     * Gets the postal code of the address.
     *
     * @return The postal code of the address.
     */
    public int getPostalCode() {
        return postalCode.get();
    }

    public SimpleIntegerProperty postalCodeProperty() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address.
     *
     * @param postalCode The postal code of the address.
     */
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    /**
     * Gets the region of the address.
     *
     * @return The region of the address.
     */
    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    /**
     * Sets the region of the address.
     *
     * @param region The region of the address.
     * @throws IllegalArgumentException If region is null or empty.
     */
    public void setRegion(String region) {
        if ((region == null) || (region.isEmpty())) {
            throw new IllegalArgumentException("region cannot be null or empty");
        }
        this.region.set(region);
    }

    /**
     * Gets the state of the address.
     *
     * @return The state of the address.
     */
    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state The state of the address.
     * @throws IllegalArgumentException If state is null or empty.
     */
    public void setState(String state) {
        if ((state == null) || (state.isEmpty())) {
            throw new IllegalArgumentException("state cannot be null or empty");
        }
        this.state.set(state);
    }

    /**
     * Gets the city of the address.
     *
     * @return The city of the address.
     */
    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
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
        this.city.set(city);
    }

    @Override
    public String toString() {
        return "Country: " + (country.get() == null ? "Not set" : country.get()) +
            "\nState: " + (state.get() == null ? "Not set" : state.get()) +
            "\nRegion: " + (region.get() == null ? "Not set" : region.get()) +
            "\nCity: " + (city.get() == null ? "Not set" : city.get()) +
            "\nPostalCode: " + postalCode.get(); // Assuming postalCode is always set due to being an int
    }
}
