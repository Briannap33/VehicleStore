package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarbonFuelCarTest {

    CarbonFuelCar CarbonFuelCarValidAtBottomEdge, CarbonFuelCarInValidBelow, CarbonFuelCarValidAtTopEdge, CarbonFuelCarValidOverTopEdge;

    @BeforeEach
    void setUp() {
        CarbonFuelCarValidAtBottomEdge = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  5, 1, 800, 0, 5,1,800,false, "diesel");
        CarbonFuelCarInValidBelow = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  4, 0, 799, 0, 4,0, 799, false, "diesel");
        CarbonFuelCarValidAtTopEdge = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  20, 100, 2500, 0, 20,100,2500,true, "petrol");
        CarbonFuelCarValidOverTopEdge = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  21, 10000, 2501, 0, 21,1000,2501,true, "petrol");

    }

    @Nested
    class constructorTests {

        @Test
        void fuelConsumptionValidation() {
            assertEquals(5, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            assertEquals(5, CarbonFuelCarInValidBelow.getFuelConsumption());
            assertEquals(20, CarbonFuelCarValidAtTopEdge.getFuelConsumption());
            assertEquals(5, CarbonFuelCarValidOverTopEdge.getFuelConsumption());

        }


        @Test
        void carbonEmissionValidation() {
            assertEquals(1, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            assertEquals(1, CarbonFuelCarInValidBelow.getCarbonEmission());
            assertEquals(100, CarbonFuelCarValidAtTopEdge.getCarbonEmission());
            assertEquals(1000, CarbonFuelCarValidOverTopEdge.getCarbonEmission());

        }


        @Test
        void engineSizeValidation() {
            assertEquals(800, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            assertEquals(800, CarbonFuelCarInValidBelow.getEngineSize());
            assertEquals(2500, CarbonFuelCarValidAtTopEdge.getEngineSize());
            assertEquals(800, CarbonFuelCarValidOverTopEdge.getEngineSize());

        }

        @Test
        void isAutomaticValidation() {
            assertEquals(false, CarbonFuelCarValidAtBottomEdge.isAutomatic());
            assertEquals(false, CarbonFuelCarInValidBelow.isAutomatic());
            assertEquals(true, CarbonFuelCarValidAtTopEdge.isAutomatic());
            assertEquals(true, CarbonFuelCarValidOverTopEdge.isAutomatic());

        }

        @Test
        void fuelTypeValidation() {
            assertEquals("diesel", CarbonFuelCarValidAtBottomEdge.getFuelType());
            assertEquals("diesel", CarbonFuelCarInValidBelow.getFuelType());
            assertEquals("petrol", CarbonFuelCarValidAtTopEdge.getFuelType());
            assertEquals("petrol", CarbonFuelCarValidOverTopEdge.getFuelType());

        }


    }

    @Nested
    class setterAndGettersTests {

        @Test
        void setGetFuelConsumption() {
            assertEquals(5, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            CarbonFuelCarValidAtBottomEdge.setFuelConsumption(2);   //just below 250-> 1000 range - should not be changed.
            assertEquals(5, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            CarbonFuelCarValidAtBottomEdge.setFuelConsumption(21);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(5, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            CarbonFuelCarValidAtBottomEdge.setFuelConsumption(19);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(19, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            CarbonFuelCarValidAtBottomEdge.setFuelConsumption(20);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(20, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());
            CarbonFuelCarValidAtBottomEdge.setFuelConsumption(21);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(20, CarbonFuelCarValidAtBottomEdge.getFuelConsumption());

        }

        @Test
        void setGetcarbonEmission() {
            assertEquals(1, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            CarbonFuelCarValidAtBottomEdge.setCarbonEmission(-1);   //just below 250-> 1000 range - should not be changed.
            assertEquals(1, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            CarbonFuelCarValidAtBottomEdge.setCarbonEmission(2501);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(2501, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            CarbonFuelCarValidAtBottomEdge.setCarbonEmission(999);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(999, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            CarbonFuelCarValidAtBottomEdge.setCarbonEmission(1000);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(1000, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());
            CarbonFuelCarValidAtBottomEdge.setCarbonEmission(1001);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(1001, CarbonFuelCarValidAtBottomEdge.getCarbonEmission());

        }

        @Test
        void setGetengineSize() {
            assertEquals(800, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            CarbonFuelCarValidAtBottomEdge.setEngineSize(799);   //just below 250-> 1000 range - should not be changed.
            assertEquals(800, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            CarbonFuelCarValidAtBottomEdge.setEngineSize(2501);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(800, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            CarbonFuelCarValidAtBottomEdge.setEngineSize(999);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(999, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            CarbonFuelCarValidAtBottomEdge.setEngineSize(1000);   //just inside 250-> 1000 range - should  be updated.
            assertEquals(1000, CarbonFuelCarValidAtBottomEdge.getEngineSize());
            CarbonFuelCarValidAtBottomEdge.setEngineSize(3600);   //just outside 250-> 1000 range - should not  be updated.
            assertEquals(1000, CarbonFuelCarValidAtBottomEdge.getEngineSize());

        }

        @Test
        void setGetisAutomatic() {
            assertFalse(CarbonFuelCarValidAtBottomEdge.isAutomatic());
            CarbonFuelCarValidAtBottomEdge.isAutomatic(true);  //just below 250-> 1000 range - should not be changed.
            assertTrue( CarbonFuelCarValidAtBottomEdge.isAutomatic());
            CarbonFuelCarValidAtBottomEdge.isAutomatic(false);   //just inside 250-> 1000 range - should  be updated.
            assertFalse( CarbonFuelCarValidAtBottomEdge.isAutomatic());
            CarbonFuelCarValidAtBottomEdge.isAutomatic(false);   //just inside 250-> 1000 range - should  be updated.
            assertFalse(CarbonFuelCarValidAtBottomEdge.isAutomatic());
            CarbonFuelCarValidAtBottomEdge.isAutomatic(true);   //just inside 250-> 1000 range - should  be updated.
            assertTrue(CarbonFuelCarValidAtBottomEdge.isAutomatic());
            CarbonFuelCarValidAtBottomEdge.isAutomatic(true);   //just outside 250-> 1000 range - should not  be updated.
            assertTrue(CarbonFuelCarValidAtBottomEdge.isAutomatic());

        }

        @Test
        void setGetfuelType() {
            assertEquals("diesel", CarbonFuelCarValidAtBottomEdge.getFuelType());
            CarbonFuelCarValidAtBottomEdge.setFuelType("petrol");   //just below 250-> 1000 range - should not be changed.
            assertEquals("petrol", CarbonFuelCarValidAtBottomEdge.getFuelType());
            CarbonFuelCarValidAtBottomEdge.setFuelType("diesel");   //just inside 250-> 1000 range - should  be updated.
            assertEquals("diesel", CarbonFuelCarValidAtBottomEdge.getFuelType());
            CarbonFuelCarValidAtBottomEdge.setFuelType("petrol");   //just inside 250-> 1000 range - should  be updated.
            assertEquals("petrol", CarbonFuelCarValidAtBottomEdge.getFuelType());
            CarbonFuelCarValidAtBottomEdge.setFuelType("petrol");   //just inside 250-> 1000 range - should  be updated.
            assertEquals("petrol", CarbonFuelCarValidAtBottomEdge.getFuelType());
            CarbonFuelCarValidAtBottomEdge.setFuelType("diesel");   //just outside 250-> 1000 range - should not  be updated.
            assertEquals("diesel", CarbonFuelCarValidAtBottomEdge.getFuelType());


        }
    }
    @Test
    void getCarbonFootPrint() {
        assertEquals(46, CarbonFuelCarValidAtBottomEdge.getCarbonFootPrint(), 0.01);
        assertEquals(46, CarbonFuelCarInValidBelow.getCarbonFootPrint(), 0.01);
        assertEquals(57500, CarbonFuelCarValidAtTopEdge.getCarbonFootPrint(), 0.01);
        assertEquals(46000, CarbonFuelCarValidOverTopEdge.getCarbonFootPrint(), 0.01);

    }
    @Test
    void testEquals() {
        CarbonFuelCar car1 = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  5, 1, 800, 0, 5,1,800,false, "diesel");
        CarbonFuelCar car2 = new CarbonFuelCar(new Manufacturer("NOT TESTING", 1000),"notTesting", "NotTesting", 0, 0,  5, 1, 800, 0, 5, 1,800,false, "diesel");




        assertEquals(car1, car2);

        // Test the subclasses' fields
        car2.setFuelConsumption(6);  // change value of power
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setFuelConsumption(5);  // reset
        assertEquals(car1, car2);

        car2.setCarbonEmission(5);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setCarbonEmission(1);  // reset
        assertEquals(car1, car2);

        car2.setEngineSize(900);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setEngineSize(800);  // reset
        assertEquals(car1, car2);

        car2.setFuelType("petrol");  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.setFuelType("diesel");  // reset
        assertEquals(car1, car2);

        car2.isAutomatic(true);  // change value of weight
        assertNotEquals(car1, car2);  // check that the equals picks up the difference
        car2.isAutomatic(false);  // reset
        assertEquals(car1, car2);
    }



    @Test
    void testToString() {
        CarbonFuelCar carbonFuelCar = new CarbonFuelCar(new Manufacturer("Toyota", 1000),"ABCD5678", "toyota789012345", 1000, 0,  5, 1, 800,  0, 5,1,800,false, "diesel");
        // test that the inherited fields appear in the toString
        String strToTest = carbonFuelCar.toString();
        //System.out.println(strToTest);
       assertTrue(strToTest.contains("ABCD5678"));  // reg number
      assertTrue(strToTest.contains("toyota789012345")); // make
       assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
       assertTrue(strToTest.contains("1000")); //cost
        assertTrue(strToTest.contains("1000")); //numemployees
        // now check the fields of Scooter subclass
        assertTrue(strToTest.contains("5"));
        assertTrue(strToTest.contains("1"));
        assertTrue(strToTest.contains("1000"));
        assertTrue(strToTest.contains("manual"));
        assertTrue(strToTest.contains("diesel"));

    }
}