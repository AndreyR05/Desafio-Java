package school.sptech.model;

import school.sptech.utils.enums.ComponentEnum;

public class Metric {
    private ComponentEnum type;
    private Double value;

    public ComponentEnum getType() {
        return type;
    }

    public void setType(ComponentEnum type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    // to string

    @Override
    public String toString() {
        return "Metric [type=" + type + ", value=" + value + "]";
    }
}
