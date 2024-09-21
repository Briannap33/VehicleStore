package main;

import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;

public class Driver {


    private VehicleAPI vehicleAPI;
    private ManufacturerAPI manufacturerAPI;

    public static void main(String[] args) throws Exception {
        new main.Driver().start();
    }

    public void start() {

        vehicleAPI = new VehicleAPI(new File("vehicles.xml"));   //todo - write constructor for VehicleAPi
        manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));

        loadAllData();  //load all data once the serializers are set up
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------Vehicle Store-------------
                |  1) Manufacturer CRUD MENU     |
                |  2) Vehicle Store CRUD MENU    |
                |  3) Reports MENU               |
                |--------------------------------|
                |  4) Search Manufacturers       |
                |  5) Search Vehicles            |  
                |  6) Sort Vehicles              | 
                |--------------------------------|
                |  10) Save all                  |
                |  11) Load all                  |
                |--------------------------------|
                |  0) Exit                       |
                 --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManufacturerMenu();
                case 2 -> runVehicleMenu();
                case 3 -> runReportsMenu();
                case 4 -> runSearchManufacturers();
                case 5 -> runSearchVehicles();
                case 6 -> runSortingMenu();
                case 10 -> saveAllData();
                case 11 -> loadAllData();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //----------------------
    //  Developer Menu Items
    //----------------------
    private int manufacturerMenu() {
        System.out.println("""
                 --------Manufacturer Menu---------
                |  1) Add a manufacturer           |
                |  2) Delete a manufacturer        |
                |  3) Update manufacturer details  |
                |  4) List all manufacturers       |
                |  5) Find a manufacturer          |
                |  0) Return to main menu          |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runManufacturerMenu() {
        int option = manufacturerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addManufacturer();
                case 2 -> deleteManufacturer();
                case 3 -> updateManufacturer();
                case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                case 5 -> findManufacturer();
                case 6 -> listVehiclesByManufacturerName();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerMenu();
        }
    }

    private void addManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

        if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void deleteManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the developer name: ");
        if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private void updateManufacturer() {
        Manufacturer manufacturer = getManufacturerByName();
        if (manufacturer != null) {
            int numEmployees = ScannerInput.readNextInt("Please enter number of Employees: ");
            if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                System.out.println("Number of Employees Updated");
            else
                System.out.println("Number of Employees NOT Updated");
        } else
            System.out.println("Manufacturer name is NOT valid");
    }

    private void findManufacturer() {
        Manufacturer developer = getManufacturerByName();
        if (developer == null) {
            System.out.println("No such manufacturer exists");
        } else {
            System.out.println(developer);
        }
    }

    private void listVehiclesByManufacturerName() {
        String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

        System.out.println(manufacturerAPI.listAllVehiclesByManufacturerName(manufacturer));
    }


    //---------------------
    //  App Store Menu
    //---------------------

    private int vehicleMenu() {
        System.out.println("""
                 --------Vehicle Menu---------
                |  1) Add a vehicle           |
                |  2) Delete a vehicle        |
                |  3) List all vehicles       |
                |  4) Update vehicle          |
                |  0) Return to main menu     |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runVehicleMenu() {
        int option = vehicleMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addVehicle();
                case 2 -> deleteVehicle();
                case 3 -> System.out.println(vehicleAPI.listAllVehicles());
                case 4 -> updateVehicle();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = vehicleMenu();
        }
    }
    //  public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {

    private void addVehicle() {
        int vehicleType = ScannerInput.readNextInt("""
                -----------------------------------------------------------|
                |     Which type of vehicle do you wish to add?            |
                |     1) Carbon Fuel Car                                   |
                |     2) Electric Car                                      |
                |     3) Scooter                                           |
                -----------------------------------------------------------|
                ==>> """);




        Manufacturer manufacturer = getManufacturerByName();
        if (manufacturer != null) {
            String regNumber = ScannerInput.readNextLine("Please enter Reg number of new Vehicle: ");


            if (vehicleAPI.isValidNewRegNumber(regNumber)) {
                String model = ScannerInput.readNextLine("\tmodel : ");
                float cost = ScannerInput.readNextFloat("\tcost : ");
                int year = ScannerInput.readNextInt("\tYear of registration");
                switch (vehicleType) {
                    case 1, 2 -> {
                        int power = ScannerInput.readNextInt("\tpower : ");
                        int secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
                        int topSpeed = ScannerInput.readNextInt("\ttop speed : ");
                        float torque = ScannerInput.readNextFloat("\tpower: ");
                        switch (vehicleType) {
                            case 1 -> {
                                // Carbon Fuel Car -
                                float fuelConsumption = ScannerInput.readNextFloat("\tfuel: ");
                                float carbonEmission = ScannerInput.readNextFloat("\tcarbonEmission: ");
                                int engineSize = ScannerInput.readNextInt("\tengineSize: ");
                                char auto = ScannerInput.readNextChar("\tisAutomatic: ");
                                boolean automatic = Utilities.YNtoBoolean(auto);
                                String fuelType = ScannerInput.readNextLine("\tFuel: ");

                                vehicleAPI.addVehicle(new CarbonFuelCar(manufacturer, regNumber, model, cost, year, power, secs0To60, topSpeed, torque, fuelConsumption, carbonEmission, engineSize, automatic, fuelType));

                            }
                            case 2 -> {
                                //Electric Car   -
                                float engineKWatts = ScannerInput.readNextFloat("\tengineKWatts: ");
                                int range = ScannerInput.readNextInt("\trange: ");

                                vehicleAPI.addVehicle(new ElectricCar(manufacturer, regNumber, model, cost, year, power, secs0To60, topSpeed, torque, engineKWatts, range));

                            }

                        }
                    }
                    case 3 -> {
                        //Scooter

                        int power = ScannerInput.readNextInt("\tpower : ");
                        float weight = ScannerInput.readNextFloat("\tweight : ");
                        int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight");
                        // write addVehicle(.)
                        vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight, topRiderWeight));

                    }

                }
            } else {
                System.out.println("Vehicle reg number  already exists.");
            }
        } else {
            System.out.println("Manufacturer name is NOT valid");
        }
    }

    private void updateVehicle() {
        int typeVehicle = ScannerInput.readNextInt("""
                ----------------------------------------------------------|
                |    Which type of vehicle do you wish to update?         |
                |    1) Carbon Fuel Car                                   |
                |    2) Electric Car                                      |
                |    3) Scooter                                           |
                ----------------------------------------------------------|
                ==>> """);


        if (vehicleAPI.numberOfVehicles() > 0) {
            //only ask the user to choose the product to update if products exist
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the vehicle to update ==> ");
            Vehicle choosenOne = vehicleAPI.getVehicleByIndex(indexToUpdate);

            if (choosenOne != null) {
                Boolean answer = true;
                //  String regNumber = ScannerInput.readNextLine("Enter the reg number:  ");
                String regNumber = choosenOne.getRegNumber();
                String model = ScannerInput.readNextLine("Enter the model:  ");
                float cost = ScannerInput.readNextFloat("Enter the Cost:  ");
                String manufacturer = ScannerInput.readNextLine("Enter manufacturer name: ");
                Manufacturer m = manufacturerAPI.getManufacturerByName(manufacturer);
                int year = ScannerInput.readNextInt("Enter the year: ");

                switch (typeVehicle) {
                    case 3 -> {
                        int power = ScannerInput.readNextInt("Enter power: ");
                        float weight = ScannerInput.readNextFloat("Enter vehicle weight: ");
                        int topRiderWeight = ScannerInput.readNextInt("Enter weight of top rider: ");
                        Scooter s = new Scooter(regNumber, model, cost, m, year, power, weight, topRiderWeight);
                        answer = vehicleAPI.updateScooter(regNumber, s);
                    }
                    case 1, 2 -> {
                        int power = ScannerInput.readNextInt("Enter power : ");
                        int secs0To60 = ScannerInput.readNextInt("Enter time from 0 to 60 :  ");
                        int topSpeed = ScannerInput.readNextInt("Enter top speed : ");
                        float torque = ScannerInput.readNextFloat("Enter power: ");
                        switch (typeVehicle) {
                            case 2 -> {
                                float engineKWatts = ScannerInput.readNextFloat(" Enter engine in KWatts: ");
                                int range = ScannerInput.readNextInt("Enter range: ");
                                ElectricCar ec = new ElectricCar(m, regNumber, model, cost, year, power, secs0To60, topSpeed, torque, engineKWatts, range);
                                answer = vehicleAPI.updateElectricCar(regNumber, ec);
                            }
                            case 1 -> {
                                float fuelConsumption = ScannerInput.readNextFloat("Enter fuel consumption: ");
                                float carbonEmission = ScannerInput.readNextFloat("Enter carbon emission: ");
                                int engineSize = ScannerInput.readNextInt("Enter engine size: ");
                                char isAutomatic = ScannerInput.readNextChar(" Is the vehicle automatic: ");
                                boolean automatic = Utilities.YNtoBoolean(isAutomatic);
                                String fuelType = ScannerInput.readNextLine("Enter fuel type: ");
                                CarbonFuelCar cfc = new CarbonFuelCar(m, regNumber, model, cost, year, power, secs0To60, topSpeed, torque, fuelConsumption, carbonEmission, engineSize, automatic, fuelType);
                                answer = vehicleAPI.updateCarbonFuelCar(regNumber, cfc);


                            }
                        }
                    }
                }
                if (answer)
                    System.out.println("Update Successful");
                else {
                    System.out.println("Update NOT Successful");
                }
            } else {
                System.out.println("There are no products for this index number");
            }
        }
    }




    private void deleteVehicle() {
        listAllVehicles();
        if (vehicleAPI.numberOfVehicles() > 0) {
            int indexToDel = ScannerInput.readNextInt("Enter index number of vehicle you want to delete: ");

            Vehicle vehicleToDel = vehicleAPI.deleteVehicleByIndex(indexToDel);
            if (vehicleToDel != null) {
                System.out.println("Vehicle deleted successfully. Deleted vehicle was: " + vehicleToDel.getModel());
            } else {
                System.out.println(" Delete was unsuccessful.");
            }
        }
    }



    private int reportsMenu() {
        System.out.println("""
                 --------Reports Menu-------------
                |  1) Manufacturers Overview      |
                |  2) Vehicles Overview           |
                |  0) Return to main menu         |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManufacturerReports();
                case 2 -> runVehicleReportMenu();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = vehicleMenu();
        }


    }


    private int vehicleReportsMenu() {
        System.out.println(""" 
                 ---------- Vehicle Reports Menu  ---------------------
                | 1) List all vehicles                                 | 
                | 2) List all Electric Cars                            |
                | 3) List all Carbon Fuel Cars                         |
                | 4) List all Scooters                                 |
                | 5) List all Vehicles registered in a given year      |
                | 6) List all Vehicles registered after a given year   |
                | 7) List all carbon fuel by fuel type                 |
                | 8) List the top five carbon vehicles                 |
                | 0) Return to main menu                               | 
                  ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    private void runVehicleReportMenu() {
        int option = vehicleReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> listAllVehicles();
                case 2 -> listAllElectricCars();
                case 3 -> listAllCarbonFuelCars();
                case 4 -> listAllScooters();
                case 5 -> listAllVehiclesRegisteredInGivenYear();
                case 6 -> listAllVehiclesRegisteredAfterGivenYear();
                case 7 -> listAllCarbonFuelByFuelType();
                case 8 -> ListTopFiveCarbonVehicles();
            }
            ScannerInput.readNextLine("\n press enter to continue..");
            option = vehicleReportsMenu();
        }
    }

    private void listAllVehicles() {
        System.out.println("List of vehicles are: ");
        System.out.println(vehicleAPI.listAllVehicles());
    }

    private void listAllElectricCars() {
        System.out.println("List of electric cars are: ");
        System.out.println(vehicleAPI.listAllElectricCars());
    }

    private void listAllCarbonFuelCars() {
        System.out.println("List of carbon fuel cars are: ");
        System.out.println(vehicleAPI.listAllCarbonFuelCars());
    }

     private void listAllScooters() {
         System.out.println("List of scooters are: ");
         System.out.println(vehicleAPI.listAllScooters());
     }

    private void listAllVehiclesRegisteredInGivenYear() {
        int setYear = ScannerInput.readNextInt("enter year: ");
        String result = vehicleAPI.listAllVehiclesEqualToAGivenYear(setYear);
        System.out.println(result);
    }

    private void listAllVehiclesRegisteredAfterGivenYear() {
        int setAfterYear = ScannerInput.readNextInt("enter year: ");
        String result = vehicleAPI.listAllVehiclesAfterAGivenYear(setAfterYear);
        System.out.println(result);
    }

    private void listAllCarbonFuelByFuelType() {
        String typeFuel = ScannerInput.readNextLine("Enter fuel type: ");
        String result = vehicleAPI.listAllCarbonFuelsByFuelType(typeFuel);
        System.out.println(result);
    }

    private void ListTopFiveCarbonVehicles() {
        System.out.println("The top five carbon vehicles are : ");
        System.out.println(vehicleAPI.topFiveCarbonVehicles());
    }


    private int manufacturerReportsMenu() {
        System.out.println(""" 
                 ---------- Manufacturers Reports Menu  -------------
                | 1) List Manufacturers                              | 
                | 2) List Manufacturers from a given manufacturer    |
                | 3) List Manufacturers by a given name              |
                | 0) Return to main menu                             | 
                  ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> listAllVehiclesFromaGivenManufacturer();   //todo write listAllVehiclesFromaGivenManufacturer
                case 3 -> listAllVehicleByChosenManufacturer();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerReportsMenu();
        }
    }

    private void listAllVehicleByChosenManufacturer() {
        String manu = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
        if (!(m == null))
            System.out.println(vehicleAPI.listAllVehicleByChosenManufacturer(m));
        else
            System.out.println("No manufacturer with tha name exists");
    }


    public void listAllVehiclesFromaGivenManufacturer() {
        String manu = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
        if (!(m == null))
            System.out.println(vehicleAPI.listAllVehicleByChosenManufacturer(m));     //todo write listAllVehicleByChosenManufacturer()
        else
            System.out.println("No manufacturer with tha name exists");
    }

    private int searchManufacturers() {
        System.out.println("""
                 --------Manufacturers search Menu---------
                |  1) Search by name                      |                 
                |  0) Return to main menu                 |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runSearchManufacturers() {
        int option = searchManufacturers();
        while (option != 0) {
            switch (option) {
                case 1 -> searchManufacturersByName();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = searchManufacturers();
        }
    }

    private void searchManufacturersByName() {
        String manuName = ScannerInput.readNextLine("Please enter a manufacturer name to search by:");
        System.out.println(manufacturerAPI.getManufacturerByName(manuName));

    }


    private int searchVehicles(){
        System.out.println("""
                --------Vehicle search Menu---------
               |  1) Search by reg number            |                 
               |  0) Return to main menu             |
                ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }
    private void runSearchVehicles() {
        int option = searchVehicles();
        while (option != 0) {
            switch (option) {
                case 1 -> searchByVehicleReg();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = searchVehicles();
        }
    }

    private void searchByVehicleReg() {
        String vehicleReg = ScannerInput.readNextLine("Please enter a vehicle name to search by:");
        System.out.println(vehicleAPI.searchByVehicleReg(vehicleReg));


    }


    //--------------------------------------------------
    //  Sorting Menu
    //--------------------------------------------------
    
    private int sortingMenu(){
    
     System.out.println("""
                --------Sorting Menu---------------------
               |  1) sort by cost descending             |
               |  2) sort by age ascending               |
               |  3) sort by Carbon footprint ascending  |                 
               |  0) Return to main menu                 |
                ----------------------------------""");
    return ScannerInput.readNextInt("==>>");
}
    private void runSortingMenu() {
        int option = sortingMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> sortByCostDescending();
                case 2 -> sortByAgeAscending();
                case 3 -> sortByCarbonFootprintAscending();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = vehicleMenu();
        }
    }

    private void sortByCostDescending() {
            vehicleAPI.sortByCostDescending();
            System.out.println(vehicleAPI.listAllVehicles());
        }


    private void sortByAgeAscending() {
        vehicleAPI.sortByAgeAscending();
        System.out.println(vehicleAPI.listAllVehicles());

    }

    private void sortByCarbonFootprintAscending() {
        vehicleAPI.sortByCarbonFootprintAscending();
        System.out.println(vehicleAPI.listAllVehicles());

    }


    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        try {
            vehicleAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData() {
        try {
            vehicleAPI.load();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }


    private String getValidRegNumber(){
            String vehicleRegNumber = ScannerInput.readNextLine("\tVehicle Reg Number (must be unique): ");
            if (vehicleAPI.isValidNewRegNumber(vehicleRegNumber)) {
                return vehicleRegNumber;
            } else {
                System.err.println("\tApp name already exists / is not valid.");
                return "";
            }
        }

        private Manufacturer getManufacturerByName(){
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
            if (manufacturerAPI.isValidManufacturer(manufacturerName)){
                return manufacturerAPI.getManufacturerByName(manufacturerName);
            }
            else{
                return null;
            }
        }


    }

