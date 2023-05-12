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

            String fileLine = bufferedReader.readLine();

            String[] headerParts = fileLine.split("\\|");

            String dealerName = headerParts[0];
            String dealerAddress = headerParts[1];
            String dealerPhone = headerParts[2];

            Dealership dealer = new Dealership(dealerName, dealerAddress, dealerPhone);

            while((fileLine = bufferedReader.readLine()) != null) {

                String[] vehicleLineParts = fileLine.split("\\|");

                int vehicleVin = Integer.parseInt(vehicleLineParts[0]);
                int vehicleYear = Integer.parseInt(vehicleLineParts[1]);
                String vehicleMake = vehicleLineParts[2];
                String vehicleModel = vehicleLineParts[3];
                String vehicleType = vehicleLineParts[4];
                String vehicleColor = vehicleLineParts[5];
                int vehicleOdometer = Integer.parseInt(vehicleLineParts[6]);
                double vehiclePrice = Double.parseDouble(vehicleLineParts[7]);

                Vehicle vehicle = new Vehicle(vehicleVin, vehicleYear,
                        vehicleMake, vehicleModel, vehicleType,
                        vehicleColor, vehicleOdometer, vehiclePrice);

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

            bufferedWriter.write(
                    String.format("%s|%s|%s", dealer.getName(), dealer.getAddress(), dealer.getPhone())
            );

            for (Vehicle vehicle: dealer.getAllVehicles()) {
                bufferedWriter.write(
                        String.format("%n%d|%d|%s|%s|%s|%s|%d|%.2f",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getOdometer(),
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
