import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {

    public IOModule ioModule;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Driver driver = new Driver();
        driver.setup();
        driver.displayMenu();
        driver.actionMenu();

    }

    public void setup() {
        System.out.println("////////////////////////////////////////");
        System.out.println("IO SYSTEM FOR SHOPS");
        System.out.println("////////////////////////////////////////");
        System.out.println("Loading...");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){}
        ioModule = new IOModule();
        ioModule.clearScreen();

    }

    public void displayMenu() {
        ioModule = new IOModule();
        ioModule.clearScreen();
        System.out.print("""
                MENU
                ---
                1) Add new item(s)
                2) Import from a current list
                0) Exit
                ==>""");
    }

    public void actionMenu() throws IOException{
        int option = input.nextInt();
        while (option != 0) {
            switch (option) {
                case 1 -> ioModule.itemAddTypeIn();
                case 2 -> ioModule.itemImport();
                default -> System.out.println("Invalid option entered: " + option);
            }
            System.out.println("Press Enter to continue...");
            input.nextLine();
            input.nextLine();
            displayMenu();
            option = input.nextInt();
        }
        exit();
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

}