package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    ElectricCar ElectricCarValidAtBottomEdge, ElectricCarInValidBelow, ElectricCarValidAtTopEdge, ElectricCarValidOverTopEdge;

    @BeforeEach
    void setUp() {
        ElectricCarValidAtBottomEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 0, 120, 4, 50, 100, 40,100);
        ElectricCarInValidBelow = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 0, 119, 3, 49, 99, 39,99);
        ElectricCarValidAtTopEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 0, 300, 25, 3000, 400, 60,500);
        ElectricCarValidOverTopEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 0, 301, 26, 3001, 401, 61,501);
    }

    @Nested
    class constructorTests {

        @Test
        void engineWattsValidation() {
            assertEquals(40, ElectricCarValidAtBottomEdge.getEngineKWatts());
            assertEquals(40, ElectricCarInValidBelow.getEngineKWatts());
            assertEquals(60, ElectricCarValidAtTopEdge.getEngineKWatts());
            assertEquals(40, ElectricCarValidOverTopEdge.getEngineKWatts());

        }

        @Test
        void rangeValidation() {
            assertEquals(100, ElectricCarValidAtBottomEdge.getRange());
            assertEquals(100, ElectricCarInValidBelow.getRange());
            assertEquals(500, ElectricCarValidAtTopEdge.getRange());
            assertEquals(100, ElectricCarValidOverTopEdge.getRange());

        }

    }

    @Nested
    class setterAndGettersTests {

        @Test
        void setGetEngineKWatts() {
            assertEquals(40, ElectricCarValidAtBottomEdge.getEngineKWatts());
            ElectricCarValidAtBottomEdge.setEngineKWatts(39);   //just below 250-> 1000 range - should not be changed.
            assertEquals(40, ElectricCarValidAtBottomEdge.getEngineKWatts());
            ElectricCarValidAtBottomEdge.setEngineKWatts(40);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(40, ElectricCarValidAtBottomEdge.getEngineKWatts());
            ElectricCarValidAtBottomEdge.setEngineKWatts(60);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(60, ElectricCarValidAtBottomEdge.getEngineKWatts());
            ElectricCarValidAtBottomEdge.setEngineKWatts(59);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(59, ElectricCarValidAtBottomEdge.getEngineKWatts());
            ElectricCarValidAtBottomEdge.setEngineKWatts(61);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(59, ElectricCarValidAtBottomEdge.getEngineKWatts());

        }


        @Test
        void setGetRange() {
            assertEquals(100, ElectricCarValidAtBottomEdge.getRange());
            ElectricCarValidAtBottomEdge.setRange(99);   //just below 250-> 1000 range - should not be changed.
            assertEquals(100, ElectricCarValidAtBottomEdge.getRange());
            ElectricCarValidAtBottomEdge.setRange(101);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(101, ElectricCarValidAtBottomEdge.getRange());
            ElectricCarValidAtBottomEdge.setRange(450);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(450, ElectricCarValidAtBottomEdge.getRange());
            ElectricCarValidAtBottomEdge.setRange(499);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(499, ElectricCarValidAtBottomEdge.getRange());
            ElectricCarValidAtBottomEdge.setRange(501);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(499, ElectricCarValidAtBottomEdge.getRange());

        }

    }
        @Test
        void getCarbonFootPrint() {
            assertEquals(0.046, ElectricCarValidAtBottomEdge.getCarbonFootPrint(), 0.01);
            assertEquals(0.044, ElectricCarInValidBelow.getCarbonFootPrint(), 0.01);
            assertEquals(0.069, ElectricCarValidAtTopEdge.getCarbonFootPrint(), 0.01);
            assertEquals(0.046, ElectricCarValidOverTopEdge.getCarbonFootPrint(), 0.01);

        }

        @Test
        void testEquals() {
            ElectricCar car1 = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2023, 250, 10, 110, 250, 45,200);
            ElectricCar car2 = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2023, 250, 10, 110, 250, 45,200);

            assertEquals(car1, car2);

            // Test the subclasses' fields
            car2.setRange(500);  // change value of power
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setRange(200);  // reset
            assertEquals(car1, car2);

            car2.setEngineKWatts(60);  // change value of weight
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setEngineKWatts(45);  // reset
            assertEquals(car1, car2);

        }

        @Test
        void testToString() {
            ElectricCar electricCar = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2023, 120, 4, 50, 100, 40,100);
            // test that the inherited fields appear in the toString
            String strToTest = electricCar.toString();

            assertTrue(strToTest.contains("ABCD5678"));  // reg number
            assertTrue(strToTest.contains("toyota789012345")); // make
            assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
            assertTrue(strToTest.contains("1000")); //cost
            // now check the fields of Scooter subclass
            assertTrue(strToTest.contains("40"));
            assertTrue(strToTest.contains("100"));

        }
    }
