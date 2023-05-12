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

    private void displayMenu() {
        System.out.println("MENU");
        System.out.println("1) Show All Vehicles");
        System.out.println("else) Exit");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle: vehicles) {
            System.out.printf("%s %s %s%n", vehicle.getYear(), vehicle.getMake(), vehicle.getModel());
        }
    }

    public void display() {
        init();

        Scanner inputScanner = new Scanner(System.in);

        boolean loop = true;
        do {
            displayMenu();

            //read user command
            String userInput = inputScanner.nextLine();

            //process user request
            switch (userInput) {
                case "1" -> processGetAllVehiclesRequest();
                default  -> loop = false;
            }
        } while (loop);
    }

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }

    public Dealership getDealership() {
        return dealership;
    }
}
