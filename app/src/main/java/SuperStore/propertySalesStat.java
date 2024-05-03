package SuperStore;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class propertySalesStat {
    private final SimpleStringProperty property;
    private final SimpleDoubleProperty number;

    public propertySalesStat(String property, Double number) {
        this.property = new SimpleStringProperty(property);
        this.number = new SimpleDoubleProperty(number);
    }

    public String getState() {
        return property.get();
    }

    public Double getNumber() {
        return number.get();
    }

    public StringProperty propertyProperty() {
        return property;
    }

    public DoubleProperty numberProperty() {
        return number;
    }
}
