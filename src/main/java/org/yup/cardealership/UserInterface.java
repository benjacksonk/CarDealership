package org.yup.cardealership;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    /*
    responsible for all output to the screen, reading of user input,
    and "dispatching" of the commands to the Dealership as needed.
    (ex: when the user selects "List all Vehicles",
    UserInterface would call the appropriate Dealership method
    and then display the vehicles it returns.)
     */
    private Dealership dealership;

    public UserInterface() {

    }

    private void init() {
        this.dealership = new DealershipFileManager().getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        if (vehicles.size() > 0) {

            System.out.println(" VIN   YEAR");

            //for each Vehicle in vehicles
            for (Vehicle vehicle : vehicles) {

                //show vehicle info
                System.out.printf("%d  %d  %s %s  -  %s, %s, %d mi, $ %.2f%n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getMileage(),
                        vehicle.getPrice()
                );
            }
        }
        else {
            System.out.println("Sorry, none in inventory.");
        }
    }

    public void display() {

        init();

        //show dealership info
        System.out.printf(
                "Welcome to %s!%n%s%n%s%n",
                dealership.getName(), dealership.getAddress(), dealership.getPhone()
        );

        boolean loop = true;
        do {
            //show the menu
            System.out.println("\nMENU");
            System.out.println("1) Show All Vehicles");
            System.out.println("2) Show Vehicles by Price");
            System.out.println("3) Show Vehicles by Make & Model");
            System.out.println("4) Show Vehicles by Year");
            System.out.println("5) Show Vehicles by Color");
            System.out.println("6) Show Vehicles by Mileage");
            System.out.println("7) Show Vehicles by Type");
            System.out.println("8) Add Vehicle");
            System.out.println("9) Remove Vehicle");
            System.out.println("0) Exit");

            //read user command
            String userInput = promptUserLine("");

            System.out.println();

            //process user request
            switch (userInput) {
                case "1" -> processGetAllVehiclesRequest();
                case "2" -> processGetByPriceRequest();
                case "3" -> processGetByMakeModelRequest();
                case "4" -> processGetByYearRequest();
                case "5" -> processGetByColorRequest();
                case "6" -> processGetByMileageRequest();
                case "7" -> processGetByVehicleTypeRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                default  -> loop = false;
            }
        } while (loop);
    }

    public void processGetByPriceRequest() {
        System.out.println("SEARCH");
        double min = Double.parseDouble(promptUserLine("Enter minimum price: $ "));
        double max = Double.parseDouble(promptUserLine("Enter maximum price: $ "));
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.println("SEARCH");
        String make = promptUserLine("Enter vehicle make: ");
        String model = promptUserLine("Enter vehicle model: ");
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.println("SEARCH");
        int min = Integer.parseInt(promptUserLine("Enter minimum year: "));
        int max = Integer.parseInt(promptUserLine("Enter maximum year: "));
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest() {
        System.out.println("SEARCH");
        String color = promptUserLine("Enter color: ");
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.println("SEARCH");
        int min = Integer.parseInt(promptUserLine("Enter minimum mileage: "));
        int max = Integer.parseInt(promptUserLine("Enter maximum mileage: "));
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("SEARCH");
        String vehicleType = promptUserLine("Enter vehicle type: ");
        System.out.println("VEHICLES");
        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("VEHICLES");
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {

        System.out.println("ADD VEHICLE");

        int vehicleVin = Integer.parseInt(promptUserLine("VIN: "));

        for (Vehicle vehicle: dealership.getAllVehicles()) {
            if (vehicle.getVin() == vehicleVin) {
                System.out.println("ERROR - That vehicle is already in inventory.");
                return;
            }
        }

        int vehicleYear = Integer.parseInt(promptUserLine("Year: "));
        String vehicleMake = promptUserLine("Make: ");
        String vehicleModel = promptUserLine("Model: ");
        String vehicleType = promptUserLine("Type: ");
        String vehicleColor = promptUserLine("Color: ");
        int vehicleOdometer = Integer.parseInt(promptUserLine("Mileage: "));
        double vehiclePrice = Double.parseDouble(promptUserLine("Price: "));

        Vehicle vehicle = new Vehicle(
                vehicleVin, vehicleYear, vehicleMake, vehicleModel,
                vehicleType, vehicleColor, vehicleOdometer, vehiclePrice
        );

        dealership.addVehicle(vehicle);
        new DealershipFileManager().saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {

        System.out.println("REMOVE VEHICLE");

        int vehicleVin = Integer.parseInt(promptUserLine("VIN: "));

        for (Vehicle vehicle: dealership.getAllVehicles()) {

            if (vehicle.getVin() == vehicleVin) {

                dealership.removeVehicle(vehicle);

                new DealershipFileManager().saveDealership(dealership);

                System.out.println("Vehicle removed.");

                return;
            }
        }

        System.out.println("ERROR - That vehicle is not in inventory.");
    }

    public Dealership getDealership() {
        return dealership;
    }

    private String promptUserLine(String string) {
        System.out.print(string);
        Scanner inputScanner = new Scanner(System.in);
        return inputScanner.nextLine();
    }
}
