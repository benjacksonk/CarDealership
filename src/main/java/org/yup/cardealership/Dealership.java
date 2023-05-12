package org.yup.cardealership;

import java.util.ArrayList;

public class Dealership {
    /*
    hold information about the dealership (name, address, ...)
    and maintain a list of vehicles.
    Since it has the list of vehicles,
    it will also have the methods that search the list for matching vehicles
    as well as add/remove vehicles
     */
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<Vehicle>();
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle price is in the given range, appropriateVehicles list adds vehicle

            double price = vehicle.getPrice();

            if (price >= min && price <= max) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle make and model match given values, appropriateVehicles list adds vehicle
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle year is in the given range, appropriateVehicles list adds vehicle

            double year = vehicle.getYear();

            if (year >= min && year <= max) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle color matches given value, appropriateVehicles list adds vehicle
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle mileage is in the given range, appropriateVehicles list adds vehicle

            double mileage = vehicle.getMileage();

            if (mileage >= min && mileage <= max) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {

        ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();

        //for each Vehicle in inventory
        for (Vehicle vehicle: inventory) {

            //if vehicle type matches given value, appropriateVehicles list adds vehicle
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                appropriateVehicles.add(vehicle);
            }
        }

        return appropriateVehicles;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
