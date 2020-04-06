import planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane(new Plane("Boeing-737", 900, 12000, 60500), 164),
            new PassengerPlane(new Plane("Boeing-737-800", 940, 12300, 63870), 192),
            new PassengerPlane(new Plane("Boeing-747", 980, 16100, 70500), 242),
            new PassengerPlane(new Plane("Airbus A320", 930, 11800, 65500), 188),
            new PassengerPlane(new Plane("Airbus A330", 990, 14800, 80500), 222),
            new PassengerPlane(new Plane("Embraer 190", 870, 8100, 30800), 64),
            new PassengerPlane(new Plane("Sukhoi Superjet 100", 870, 11500, 50500), 140),
            new PassengerPlane(new Plane("Bombardier CS300", 920, 11000, 60700), 196),
            new MilitaryPlane(new Plane("B-1B Lancer", 1050, 21000, 80000), MilitaryType.BOMBER),
            new MilitaryPlane(new Plane("B-2 Spirit", 1030, 22000, 70000), MilitaryType.BOMBER),
            new MilitaryPlane(new Plane("B-52 Stratofortress", 1000, 20000, 80000), MilitaryType.BOMBER),
            new MilitaryPlane(new Plane("F-15", 1500, 12000, 10000), MilitaryType.FIGHTER),
            new MilitaryPlane(new Plane("F-22", 1550, 13000, 11000), MilitaryType.FIGHTER),
            new MilitaryPlane(new Plane("C-130 Hercules", 650, 5000, 110000), MilitaryType.TRANSPORT),
            new ExperimentalPlane(new Plane("Bell X-14", 277, 482, 500), ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane(new Plane("Ryan X-13 Vertijet", 560, 307, 500), ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane(new Plane("Boeing-747", 980, 16100, 70500), 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        Assert.assertTrue(isTransportMilitaryPlane(airport));
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.getPassengersCapacity() == planeWithMaxPassengerCapacity.getPassengersCapacity());
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        Assert.assertTrue(isNextPlaneMaxLoadCapacityIsHigherThanCurrent(airport));
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        Assert.assertTrue(isBomberMilitaryPlane(airport));
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        Airport airport = new Airport(planes);
        Assert.assertFalse(hasUnclassifiedPlanes(airport));
    }

    private boolean isTransportMilitaryPlane(Airport airport){
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        boolean transportMilitaryPlane = false;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getType() == MilitaryType.TRANSPORT)) {
                transportMilitaryPlane = true;
                break;
            }
        }
        return transportMilitaryPlane;
    }

    private boolean isBomberMilitaryPlane(Airport airport){
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean bomberMilitaryPlane = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getType() == MilitaryType.BOMBER)) {
                bomberMilitaryPlane = true;
                break;
            }
        }
        return bomberMilitaryPlane;
    }

    private boolean isNextPlaneMaxLoadCapacityIsHigherThanCurrent(Airport airport){
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        return nextPlaneMaxLoadCapacityIsHigherThanCurrent;
    }

    private boolean hasUnclassifiedPlanes(Airport airport){
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        boolean unclassifiedPlanes = false;
        for(ExperimentalPlane experimentalPlane : experimentalPlanes){
            if(experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED){
                unclassifiedPlanes = true;
                break;
            }
        }
        return unclassifiedPlanes;
    }
}
