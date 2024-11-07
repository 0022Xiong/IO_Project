
import java.util.Scanner;

public class Driver {

    public IOModule ioModule;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("////////////////////////////////////////");
        System.out.println("IO SYSTEM FOR SHOPS");
        System.out.println("////////////////////////////////////////");
        System.out.println("Loading...");
        Driver driver = new Driver();

        driver.setup();
    }

    public void setup(){
        System.out.println("Please set the maximum space: ");
        int maxItem = input.nextInt();
        ioModule = new IOModule(maxItem);
        System.out.println("Please select the mode:(input(i),search(s),delete(d),output(o)[WIP]");
        char mainMode = input.next().charAt(0);
            if(mainMode == 'i' || mainMode == 'I'){
                System.out.println("Now in the INPUT mode");
                System.out.println("Please choose way to input: type-in(t) or import from .txt(i)[WIP]");
                char subMode = input.next().charAt(0);
                if(subMode == 't' || subMode == 'T'){
                    ioModule.itemTypeIn();
                    }

//                if(subMode == 'i' || subMode == 'I'){
//
//                }
                else{
                    System.out.println("Invalid word");
                }

            }//input end
            else if(mainMode == 's' || mainMode == 'S'){
                System.out.println("Now in the SEARCH mode");
//                System.out.println("Enter the key word: ");

            }//search end
            else if(mainMode == 'd' || mainMode == 'D'){
                System.out.println("Now in the DELETE mode");
                ioModule.itemDelete();

            }//delete end
//            if(mode == 'o' || mode == 'O'){
//                System.out.println("Now in the OUTPUT mode");
//            }//output end
            else{
                System.out.println("Invalid word. Please enter \"input\", \"search\", \"delete\" or \"i\", \"s\", \"d\". ");
                setup();
            }

    }

}
