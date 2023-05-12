package org.yup.cardealership;

public class Program {
    /*
    responsible for starting the application via its main() method
    and then creating the user interface and getting it started
    */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.display();
    }
}
