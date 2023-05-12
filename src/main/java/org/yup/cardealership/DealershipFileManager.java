package org.yup.cardealership;

import java.io.*;

public class DealershipFileManager {
    /*
    responsible for reading the dealership file, parsing the data,
    and creating a Dealership object full of vehicles from the file.
    Also responsible for saving a dealership and the vehicles
    back into the file in the same pipe-delimited format
     */
    public Dealership getDealership() {

        try {

            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read header line and split it into parts
            String fileLine = bufferedReader.readLine();
            String[] headerLineParts = fileLine.split("\\|");

            //create Dealer from header parts
            String dealerName = headerLineParts[0];
            String dealerAddress = headerLineParts[1];
            String dealerPhone = headerLineParts[2];

            Dealership dealer = new Dealership(dealerName, dealerAddress, dealerPhone);

            //create Vehicles from data in rows of file
            while((fileLine = bufferedReader.readLine()) != null) {

                //split line into parts
                String[] vehicleLineParts = fileLine.split("\\|");

                //create Vehicle from data
                int vehicleVin = Integer.parseInt(vehicleLineParts[0]);
                int vehicleYear = Integer.parseInt(vehicleLineParts[1]);
                String make = vehicleLineParts[2];
                String model = vehicleLineParts[3];
                String vehicleType = vehicleLineParts[4];
                String vehicleColor = vehicleLineParts[5];
                int mileage = Integer.parseInt(vehicleLineParts[6]);
                double vehiclePrice = Double.parseDouble(vehicleLineParts[7]);

                Vehicle vehicle = new Vehicle(
                        vehicleVin, vehicleYear, make, model,
                        vehicleType, vehicleColor, mileage, vehiclePrice
                );

                //add vehicle to dealership
                dealer.addVehicle(vehicle);
            }

            bufferedReader.close();
            fileReader.close();

            return dealer;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDealership(Dealership dealer) {

        try {

            FileWriter fileWriter = new FileWriter("inventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //write header line using dealership info
            bufferedWriter.write(
                    String.format("%s|%s|%s", dealer.getName(), dealer.getAddress(), dealer.getPhone())
            );

            //write lines using info for each vehicle
            for (Vehicle vehicle: dealer.getAllVehicles()) {
                bufferedWriter.write(
                        String.format("%n%d|%d|%s|%s|%s|%s|%d|%.2f",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getMileage(),
                                vehicle.getPrice()
                        )
                );
            }

            bufferedWriter.close();
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
