package org.yup.cardealership;

public class Program {
    /*
    responsible for starting the application via its main() method
    and then creating the user interface and getting it started
    */
    public static void main(String[] args) {
        Dealership dealer = new Dealership("DealsOnWheels", "here", "243-261-5184");

        Vehicle vehicle1 = new Vehicle(18079, 2013,
                "Toyota", "Corolla", "sedan", "silver",
                317081, 7000);

        Vehicle vehicle2 = new Vehicle(58147, 2015,
                "Psyonix", "Venom", "coupe", "red",
                517070, 9000);

        Vehicle vehicle3 = new Vehicle(96182, 2015,
                "Projekt", "Roach", "horse", "brown",
                515081, 4000);

        dealer.addVehicle(vehicle1);
        dealer.addVehicle(vehicle2);
        dealer.addVehicle(vehicle3);

        for (Vehicle vehicle: dealer.getAllVehicles()) {
            System.out.printf("%s %s %s%n",
                    vehicle.getYear(), vehicle.getMake(), vehicle.getModel()
            );
        }
    }
}
