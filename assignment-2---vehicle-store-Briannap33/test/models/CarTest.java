package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car carValidAtBottomEdge, carInValidBelow, carValidAtTopEdge, carValidOverTopEdge;

    @BeforeEach
    void setUp() {

        carValidAtBottomEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 1012, 120, 4, 50, 100, 45,200);
        carInValidBelow = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2012, 119, 3, 49, 99, 45,200);
        carValidAtTopEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2012, 300, 25, 3000, 400, 45,200);
        carValidOverTopEdge = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2012, 301, 26, 3001, 401, 45,200);

    }
    @Nested
    class constructorTests {
        @Test
        void powerValidation() {
            assertEquals(120, carValidAtBottomEdge.getPower());
            assertEquals(120, carInValidBelow.getPower());
            assertEquals(300, carValidAtTopEdge.getPower());
            assertEquals(120, carValidOverTopEdge.getPower());
        }
        @Test
        void secs0To60Validation() {
            assertEquals(4, carValidAtBottomEdge.getSecs0To60());
            assertEquals(4, carInValidBelow.getSecs0To60());
            assertEquals(25, carValidAtTopEdge.getSecs0To60());
            assertEquals(4, carValidOverTopEdge.getSecs0To60());
        }
        @Test
        void topSpeedValidation() {
            assertEquals(50, carValidAtBottomEdge.getTopSpeed());
            assertEquals(50, carInValidBelow.getTopSpeed());
            assertEquals(3000, carValidAtTopEdge.getTopSpeed());
            assertEquals(50, carValidOverTopEdge.getTopSpeed());
        } @Test
        void torqueValidation() {
            assertEquals(100, carValidAtBottomEdge.getTorque());
            assertEquals(100, carInValidBelow.getTorque());
            assertEquals(400, carValidAtTopEdge.getTorque());
            assertEquals(100, carValidOverTopEdge.getTorque());
        }

    }

    @Test
    void setGetPower() {
        assertEquals(120, carValidAtBottomEdge.getPower());
        carValidAtBottomEdge.setPower(249);   //just below 250-> 1000 range - should not be changed.
        assertEquals(249, carValidAtBottomEdge.getPower());
        carValidAtBottomEdge.setPower(251);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getPower());
        carValidAtBottomEdge.setPower(999);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getPower());
        carValidAtBottomEdge.setPower(1000);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getPower());
        carValidAtBottomEdge.setPower(1001);   //just outside 250-> 1000 range - should not  be updated.
        assertEquals(251, carValidAtBottomEdge.getPower());

    }


    @Test
    void setGetSecs0To60() {
        assertEquals(4, carValidAtBottomEdge.getSecs0To60());
        carValidAtBottomEdge.setSecs0To60(249);   //just below 250-> 1000 range - should not be changed.
        assertEquals(4, carValidAtBottomEdge.getSecs0To60());
        carValidAtBottomEdge.setSecs0To60(16);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(16, carValidAtBottomEdge.getSecs0To60());
        carValidAtBottomEdge.setSecs0To60(7);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(7, carValidAtBottomEdge.getSecs0To60());
        carValidAtBottomEdge.setSecs0To60(12);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(12, carValidAtBottomEdge.getSecs0To60());
        carValidAtBottomEdge.setSecs0To60(1001);   //just outside 250-> 1000 range - should not  be updated.
        assertEquals(12, carValidAtBottomEdge.getSecs0To60());
    }


    @Test
    void setGetTopSpeed() {
        assertEquals(50, carValidAtBottomEdge.getTopSpeed());
        carValidAtBottomEdge.setTopSpeed(249);   //just below 250-> 1000 range - should not be changed.
        assertEquals(249, carValidAtBottomEdge.getTopSpeed());
        carValidAtBottomEdge.setTopSpeed(251);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getTopSpeed());
        carValidAtBottomEdge.setTopSpeed(999);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(999, carValidAtBottomEdge.getTopSpeed());
        carValidAtBottomEdge.setTopSpeed(1000);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(1000, carValidAtBottomEdge.getTopSpeed());
        carValidAtBottomEdge.setTopSpeed(1001);   //just outside 250-> 1000 range - should not  be updated.
        assertEquals(1001, carValidAtBottomEdge.getTopSpeed());
    }

    @Test
    void setGetTorque() {
        assertEquals(100, carValidAtBottomEdge.getTorque());
        carValidAtBottomEdge.setTorque(249);   //just below 250-> 1000 range - should not be changed.
        assertEquals(249, carValidAtBottomEdge.getTorque());
        carValidAtBottomEdge.setTorque(251);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getTorque());
        carValidAtBottomEdge.setTorque(999);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getTorque());
        carValidAtBottomEdge.setTorque(1000);   //just inside 250-> 1000 range - should  be updated.
        assertEquals(251, carValidAtBottomEdge.getTorque());
        carValidAtBottomEdge.setTorque(1001);   //just outside 250-> 1000 range - should not  be updated.
        assertEquals(251, carValidAtBottomEdge.getTorque());
    }


    @Test
    void getCarbonFootPrint() {
        assertEquals(0.052, carValidAtBottomEdge.getCarbonFootPrint(), 0.01);
        assertEquals(0.024, carInValidBelow.getCarbonFootPrint(), 0.01);
        assertEquals(0.025, carValidAtTopEdge.getCarbonFootPrint(), 0.01);
        assertEquals(0.024, carValidOverTopEdge.getCarbonFootPrint(), 0.01);

    }

@Test
    void testToString() {
        Car car = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000,2012, 200, 20, 1500,250, 40,100);
        // test that the inherited fields appear in the toString
        String strToTest = car.toString();

        assertTrue(strToTest.contains("ABCD5678"));  // reg number
        assertTrue(strToTest.contains("toyota789012345")); // make
        assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
        assertTrue(strToTest.contains("1000")); //cost
        // now check the fields of Scooter subclass
        assertTrue(strToTest.contains("200"));
        assertTrue(strToTest.contains("20"));
        assertTrue(strToTest.contains("1500"));
        assertTrue(strToTest.contains("250"));

    }
    @Test
    void testEquals() {
        Car car1 = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2023, 250, 10, 110, 250, 45,200);
        Car car2 = new ElectricCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 12000, 2023, 250, 10, 110, 250, 45,200);

        assertEquals(car1, car2);

        // Test the subclasses' fields
        car2.setPower(300);  // change value of power
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setPower(250);  // reset
        assertEquals(car1, car2);

        car2.setSecs0To60(15);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setSecs0To60(10);  // reset
        assertEquals(car1, car2);

        car2.setTopSpeed(120);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setTopSpeed(110);  // reset
        assertEquals(car1, car2);

        car2.setTorque(120);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setTorque(250);  // reset
        assertEquals(car1, car2);
    }
}