package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;
import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class VehicleAPI implements Serializer { // todo implements Serializer {   (when load and saved written, include the 'implements Serializer here)

    // more private fields here (file)
    private List<Vehicle> vehicles = new ArrayList<>();

    private File file;

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)
    public VehicleAPI(File file) {
        this.file = file;
    }

/**
 ALL JAVADOC GOTTEN FROM CHATGPT
  */
    //-------------------------------------
//   CRUD
//-------------------------------------
    /**
     Adds a new Vehicle object to the ArrayList vehicles.
     @param vehicle the Vehicle object to be added to the list
     @return true if the vehicle was added successfully, false otherwise
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicles.add(vehicle))
            return true;
        else {
            return false;
        }
    }


    /**
     Removes a Vehicle object from the ArrayList at the specified index, if valid.
     @param index the index of the Vehicle object to be removed
     @return the Vehicle object that was removed, or null if the index is invalid
     */
    public Vehicle deleteVehicleByIndex(int index) {
        if (Utilities.isValidIndex(vehicles, index)) {
            return vehicles.remove(index);
        }
        return null;
    }

    /**
     Removes a Vehicle object from the ArrayList based on the given registration number.
     @param regNumber the registration number of the Vehicle object to be removed
     @return the Vehicle object that was removed, or null if the registration number is invalid
     */
    public Vehicle deleteVehicleByRegNumber(String regNumber) {
        if (isValidNewRegNumber(regNumber)) {
            Vehicle pickedVehicle = getVehicleByRegNumber(regNumber);
            vehicles.remove(pickedVehicle);
            return pickedVehicle;
        }
        return null;
    }

    /**

     Returns the Vehicle object with the given index number .
     @param index the index number of the Vehicle object to be retrieved
     @return the Vehicle object with the given index number, or null if not found
     */
    public Vehicle getVehicleByIndex(int index) {
        if (Utilities.isValidIndex(vehicles, index)) {
            return vehicles.get(index);
        } else {
            return null;
        }
    }
    /**

     Returns the Vehicle object with the given registration number .
     @param regNumber the registration number of the Vehicle object to be retrieved
     @return the Vehicle object with the given registration number, or null if not found
     */
    public Vehicle getVehicleByRegNumber(String regNumber) {
        if (isValidNewRegNumber(regNumber)) {
            for (Vehicle v : vehicles) {
                if (v.getRegNumber().equals(regNumber))
                    return v;
            }
        }
        return null;
    }

    /**

     Replaces the Vehicle object with the given registration number with the
     Scooter object passed as input (updatedScooter).
     @param regNumber the registration number of the Vehicle object to be replaced
     @param scooterUpdate the Scooter object to replace the existing Vehicle object
     */
    public boolean updateScooter(String regNumber, Scooter scooterUpdate) {

        for (Vehicle foundScooter : vehicles) {
            if (foundScooter.getRegNumber().equalsIgnoreCase(regNumber)) {
                if (foundScooter instanceof Scooter) {
                    foundScooter.setYear(scooterUpdate.getYear());
                    foundScooter.setCost(scooterUpdate.getCost());
                    foundScooter.setManufacturer(scooterUpdate.getManufacturer());
                    foundScooter.setModel(scooterUpdate.getModel());
                    foundScooter.setRegNumber(scooterUpdate.getRegNumber());
                    ((Scooter) foundScooter).getPower();
                    ((Scooter) foundScooter).getWeight();
                    ((Scooter) foundScooter).getTopRiderWeight();//casting
                    return true;
                }
            }
        }
            return false;

}

    /**
     Replaces the Vehicle object with the given registration number with the
     CarbonFuelCar object passed as input (updatedCar).
     @param regNumber the registration number of the Vehicle object to be replaced
     @param updatedCarbonCar the CarbonFuelCar object to replace the existing Vehicle object
     */
    public boolean updateCarbonFuelCar(String regNumber, CarbonFuelCar updatedCarbonCar){

        for (Vehicle foundCarbon : vehicles) {
            if (foundCarbon.getRegNumber().equalsIgnoreCase(regNumber)) {
                if (foundCarbon instanceof CarbonFuelCar) {
                    foundCarbon.setYear(updatedCarbonCar.getYear());
                    foundCarbon.setCost(updatedCarbonCar.getCost());
                    foundCarbon.setManufacturer(updatedCarbonCar.getManufacturer());
                    foundCarbon.setModel(updatedCarbonCar.getModel());
                    foundCarbon.setRegNumber(updatedCarbonCar.getRegNumber());
                    ((CarbonFuelCar) foundCarbon).getPower();
                    ((CarbonFuelCar) foundCarbon).getCarbonEmission();
                    ((CarbonFuelCar) foundCarbon).getEngineSize();
                    ((CarbonFuelCar) foundCarbon).getFuelType();
                    ((CarbonFuelCar) foundCarbon).getFuelConsumption();
                    ((CarbonFuelCar) foundCarbon).isAutomatic();
                    ((CarbonFuelCar) foundCarbon).getSecs0To60();
                    ((CarbonFuelCar) foundCarbon).getTorque();
                    ((CarbonFuelCar) foundCarbon).getTopSpeed();

                    return true;
                }
            }
        }
        return false;

    }

    /**
     Replaces the Vehicle object with the given registration number with the
     ElectricCar object passed as input (updatedCar).
     @param regNumber the registration number of the Vehicle object to be replaced
     @param updateElectric the ElectricCar object to replace the existing Vehicle object
     */
    public boolean updateElectricCar(String regNumber, ElectricCar updateElectric){

        for (Vehicle foundElectric : vehicles) {
            if (foundElectric.getRegNumber().equalsIgnoreCase(regNumber)) {
                if (foundElectric instanceof ElectricCar) {
                    foundElectric.setYear(updateElectric.getYear());
                    foundElectric.setCost(updateElectric.getCost());
                    foundElectric.setManufacturer(updateElectric.getManufacturer());
                    foundElectric.setModel(updateElectric.getModel());
                    foundElectric.setRegNumber(updateElectric.getRegNumber());
                    ((ElectricCar) foundElectric).getPower();
                    ((ElectricCar) foundElectric).getSecs0To60();
                    ((ElectricCar) foundElectric).getTorque();
                    ((ElectricCar) foundElectric).getTopSpeed();
                    ((ElectricCar) foundElectric).getEngineKWatts();
                    ((ElectricCar) foundElectric).getRange();//casting
                    return true;
                }

            }
        }
        return false;
    }
//------------------------
//      reporting
// -----------------------
    /**
     Returns a String containing the details of all the vehicles in vehicles along with the index number associated with each vehicle.
     If no vehicles exist yet, "No vehicles" should be returned.
     */
    public String listAllVehicles() {
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String listOfVehicles = "";
            for (int i = 0; i < vehicles.size(); i++) {
                listOfVehicles += i + ": " + vehicles.get(i).toString() + "\n";
            }

            return listOfVehicles;
        }
    }

    /**
     Returns a String containing the details of all the Scooter vehicles in vehicles along with the index number associated with each vehicle.
     If no such vehicles exist, "No scooters." should be returned.
     */
    public String listAllScooters() {
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String listScooter = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if(vehicles.get(i) instanceof Scooter)
                    listScooter += i + ": " + vehicles.get(i).toString() + "\n";
            }

            if(listScooter.isEmpty())
                return "no scooteer";

            return listScooter;
        }
    }

    /**
     Returns a String containing the details of all the Electric Cars in vehicles along with the index number associated with each vehicle.
     If no such vehicles exist, "No Electric Cars" should be returned.
     */
    public String listAllElectricCars(){
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String listElectric = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if(vehicles.get(i) instanceof ElectricCar)
                    listElectric += i + ": " + vehicles.get(i).toString() + "\n";
            }
            if(listElectric.isEmpty())
                return "no Electric cars";

            return listElectric;
        }
    }

   /**
    Returns a String containing the details of all the Carbon Fuel Cars in vehicles along with the index number associated with each vehicle.
    If no such vehicles exist, "No Carbon Fuel Cars" should be returned.
*/
    public String listAllCarbonFuelCars(){
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String listCarbonFuel = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if(vehicles.get(i) instanceof CarbonFuelCar)
                    listCarbonFuel += i + ": " + vehicles.get(i).toString() + "\n";
            }
            if(listCarbonFuel.isEmpty())
                return "no Carbon Fuel cars";

            return listCarbonFuel;
        }


    }
    /**

     Returns the list of vehicles whose fuel type is that entered as parameter.
     If the string is invalid, "Invalid fuel type entered" should be returned.
     If no such vehicles exist, "No vehicles with "fuel type" exists" should be returned.
     @param fuelType A String representing the fuel type to filter the vehicles by
     @return A list of vehicles that match the fuel type filter
     */
    public String listAllCarbonFuelsByFuelType(String fuelType) {
        if(!FuelTypeUtility.validFuelType(fuelType))
            return "invalid fuel type entered";
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String listByFuel = "";
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle oneImLookingAt = vehicles.get(i);
                if (oneImLookingAt instanceof CarbonFuelCar && ((CarbonFuelCar)oneImLookingAt).getFuelType().equalsIgnoreCase(fuelType)) {
                    listByFuel += i + ": " + vehicles.get(i) + "\n";
                }
            }
            if (listByFuel.isEmpty())
                return "no vehicles with " + fuelType + "exists";
            return listByFuel;
        }



    }

    /**
     Returns a String containing the details of all the vehicles in vehicles whose manufacturer is equal to that passed in as parameter.
     If no such vehicles exist, "There are no vehicles who have " + manufacturer + " in the list" should be returned.
     @param manufacturer A String representing the manufacturer to filter the vehicles by
     @return A String containing the details of all the vehicles matching the manufacturer filter
     */
    public String listAllVehicleByChosenManufacturer(Manufacturer manufacturer){
        if (vehicles.isEmpty()) {
            return "no vehicles are stored";
        } else {
            String listByManu = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getManufacturer().equals(manufacturer))
                    listByManu += i + ": " + vehicles.get(i) + "\n";
            }
            if (listByManu.isEmpty())
                return "no vehicles who have " + manufacturer + " in the list";
            return listByManu;
        }


    }

    /**
     Returns a String containing all the vehicles that have the same year as the year passed in as a parameter.
     If no such vehicles exist, "No vehicles exist for " + year should be returned.
     @param year An int representing the year to filter the vehicles by
     @return A String containing the details of all the vehicles matching the year filter
     */
    public String listAllVehiclesEqualToAGivenYear(int year){
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String str = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getYear() == year)
                    str += i + ": " + vehicles.get(i) + "\n";
            }
            if (str.equals("")) {
                return "No vehicles from " + year;
            } else {
                return str;
            }
        }
    }

    /**
     Returns a String containing all the vehicles that have year > the year passed in as a parameter.
     If no such vehicles exist, "No vehicles exist later than " + year should be returned.
     @param year An int representing the year to filter the vehicles by
     @return A String containing the details of all the vehicles matching the year filter
     */
    public String listAllVehiclesAfterAGivenYear(int year) {
        if (vehicles.isEmpty()) {
            return "No Vehicles";
        } else {
            String str = "";
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getYear() > year)
                    str += i + ": " + vehicles.get(i) + "\n";
            }
            if (str.equals("")) {
                return "No vehicles exist later than " + year;
            } else {
                return str;
            }
        }
    }

    /**
     Returns the number of vehicles in the system (in vehicles).
     @return An int representing the number of vehicles in the system
     */
     public int numberOfVehicles(){
        return vehicles.size();
     }

    /**
     Returns the number of scooters in the system (in vehicles).
     @return An int representing the number of scooters in the system
     */
     public int numberOfScooters(){
         int num = 0;
         for (Vehicle vehicle : vehicles) {
             if (vehicle instanceof Scooter) {
                 num++;
             }
         }
         return num;
     }

    /**
     Returns the number of electric cars in the system (in vehicles).
     @return An int representing the number of electric cars in the system
     */
     public int numberOfElectricCars(){
         int numElec = 0;
         for (Vehicle vehicle : vehicles) {
             if (vehicle instanceof ElectricCar) {
                 numElec++;
             }
         }
         return numElec;
     }

    /**

     Returns the number of carbon fuel cars in the system (in vehicles).
     @return An int representing the number of carbon fuel cars in the system
     */
     public int numberOfCarbonCars(){
         int numCarbon= 0;
         for (Vehicle vehicle : vehicles) {
             if (vehicle instanceof CarbonFuelCar) {
                 numCarbon++;
             }
         }
         return numCarbon;
     }


    /**

     Returns the number of vehicles in the system (in vehicles) whose manufacturer is equal to the passed parameter.
     @param manufacturer the manufacturer to search for in the list of vehicles
     @return the number of vehicles whose manufacturer is equal to the passed parameter
     */
    public int numberOfVehicleByChosenManufacturer(Manufacturer manufacturer){
         if (vehicles.isEmpty()) {
             return 0;
         } else {
             int num = 0;
             for (int i = 0; i < vehicles.size(); i++) {
                 if (vehicles.get(i).getManufacturer().equals(manufacturer))
                     num++;
             }

             return num;
         }

     }

     //-------------------------
    //   validation
    //----------------------------
    /**
     This method checks if the regNumber is a new reg number i.e. it does not already exist in the collection.
     @param regNumber the registration number of the vehicle to be checked
     @return true if that reg number does not exist in the vehicles collection, false if that reg number does exist in the vehicles collection
     */
    public boolean isValidNewRegNumber(String regNumber){
         for(Vehicle vehicle: vehicles)     //todo - declare and instantiate vehicles
            if (vehicle.getRegNumber().equals(regNumber))
               return false;
       return true;
    }


    //----------------------------
    //   Sorting
    //---------------------------

    /**
     This method sorts the vehicles object in descending order by cost.
     */
    public void sortByCostDescending(){
    for(int i= vehicles.size() +1; i<=0; i++){
        int lowestIndex = 0;
        for(int j= 0; j >=i; j--){
            if(vehicles.get(j).getCost() > (vehicles.get(lowestIndex).getCost())){
                lowestIndex = j;
            }
        }
        swapVehicles(vehicles, i, lowestIndex);
    }

    }
    /**
     This method sorts the vehicles object in ascending order by age of vehicle.
     */
    public void sortByAgeAscending(){
        for(int i = vehicles.size() - 1; i>= 0; i--){
            int highestIndex = 0;
            for(int j = 0; j <= i; j++){
                if(vehicles.get(j).getAge() < (vehicles.get(highestIndex).getAge())){
                    highestIndex = j;
                }
            }
            swapVehicles(vehicles, i, highestIndex);
        }
    }

    /**
     This method sorts the vehicles object in ascending order by carbon footprint of vehicle.
     */
    public void sortByCarbonFootprintAscending(){
        for(int i = vehicles.size() - 1; i>= 0; i--){
            int highestIndex = 0;
            for(int j = 0; j <= i; j++){
                if(vehicles.get(j).getCarbonFootPrint() < (vehicles.get(highestIndex).getCarbonFootPrint())){
                    highestIndex = j;
                }
            }
            swapVehicles(vehicles, i, highestIndex);
        }
    }

    /**
     This is a private method that swaps the objects at positions i and j in the collection vehicles.
     This method should be used in sorting methods.
     @param vehicles The list of vehicles to be sorted
     @param i The index of the first object to be swapped
     @param j The index of the second object to be swapped
     */
    private void swapVehicles(List<Vehicle> vehicles, int i, int j){
    Vehicle smaller = vehicles.get(i);
    Vehicle bigger = vehicles.get(j);

    vehicles.set(i, bigger);
    vehicles.set(j, smaller);
    }

    //-------------------------
    //     misc.
    //-------------------------
    /**
     Returns a List of the top 5 carbon fuel vehicles (i.e. those with the lowest carbon footprint).
     If there are less than 5 carbon fuel vehicles, the returned list will have less than 5 elements.
     If there are no carbon fuel vehicles in the system, an empty list will be returned.
     The returned list is sorted by carbon footprint in ascending order.
     @return a List of the top 5 carbon fuel vehicles sorted by carbon footprint in ascending order, or an empty list if no carbon fuel vehicles exist.
     */
    public List<Vehicle> topFiveCarbonVehicles() {
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j == 4; j++) {
                if (vehicles.get(j).getCarbonFootPrint() < (vehicles.get(highestIndex).getCarbonFootPrint())) {
                    highestIndex = j;
                }
            }
            swapVehicles(vehicles, i, highestIndex);
        }
        return null;
    }



    // checks if regNumber is a new reg number i.e. it does not already exist in the collection
//    public boolean isValidNewRegNumber(String regNumber){
//        for(Vehicle vehicle: vehicles)     // - declare and instantiate vehicles
//            if (vehicle.getRegNumber().equals(regNumber))
//                return false;
//        return true;
//    }

    public String searchByVehicleReg(String regNumber) {
        String matchReg = "";
        for(Vehicle vehicle : vehicles) {
            if (vehicle.getRegNumber().toUpperCase().contains(regNumber.toUpperCase())) {
                matchReg += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
            }
        }

        if (matchReg.equals("")){
            return "No vehicles match your search";
        }
        else{
            return matchReg;
        }
    }

    //---------------------
    // Persistence methods
    //---------------------
    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
      @SuppressWarnings("unchecked")
     public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
                                            ElectricCar.class, Scooter.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        vehicles = (List<Vehicle>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the ArrayList
     * to the xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(vehicles);
        out.close();
    }

    public String fileName(){
        return file.toString();
    }


}
