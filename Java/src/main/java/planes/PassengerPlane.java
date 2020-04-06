package planes;

import java.util.Objects;

public class PassengerPlane extends Plane{

    private int passengersCapacity;

    public PassengerPlane(Plane plane, int passengersCapacity) {
        super();
        setModel(plane.getModel());
        setMaxSpeed(plane.getMaxSpeed());
        setMaxFlightDistance(plane.getMaxFlightDistance());
        setMaxLoadCapacity(plane.getMaxLoadCapacity());
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() { return passengersCapacity; }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerPlane)) return false;
        if (!super.equals(o)) return false;
        PassengerPlane plane = (PassengerPlane) o;
        return passengersCapacity == plane.passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
}
