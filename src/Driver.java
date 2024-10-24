import java.util.Scanner;

public class Driver {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("////////////////////////////////////////");
        System.out.println("IO SYSTEM FOR SHOPS");
        System.out.println("////////////////////////////////////////");
        System.out.println("Loading...");
        Driver driver = new Driver();

        driver.setup();
    }

    public void setup(){
        System.out.println("Please select the mode:(input,search,delete)");

    }

}
