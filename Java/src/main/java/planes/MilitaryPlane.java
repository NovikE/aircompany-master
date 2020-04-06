package planes;

import models.MilitaryType;

import java.util.Objects;

public class MilitaryPlane extends Plane{

    private MilitaryType type;

    public MilitaryPlane(Plane plane, MilitaryType type) {
        super();
        setModel(plane.getModel());
        setMaxSpeed(plane.getMaxSpeed());
        setMaxFlightDistance(plane.getMaxFlightDistance());
        setMaxLoadCapacity(plane.getMaxLoadCapacity());
        this.type = type;
    }

    public MilitaryType getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + type +
                '}');
    }

    @Override
    public boolean equals(Object o) {
        return (this == o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
