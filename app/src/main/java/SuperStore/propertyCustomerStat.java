package SuperStore;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class propertyCustomerStat {
    private final SimpleStringProperty property;
    private final SimpleIntegerProperty number;

    public propertyCustomerStat(String property, Integer number) {
        this.property = new SimpleStringProperty(property);
        this.number = new SimpleIntegerProperty(number);
    }

    public String getState() {
        return property.get();
    }

    public Integer getNumber() {
        return number.get();
    }

    public StringProperty propertyProperty() {
        return property;
    }

    public IntegerProperty numberProperty() {
        return number;
    }
}
