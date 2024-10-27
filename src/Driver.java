
import java.util.Scanner;

public class Driver {
    IOModule ioModule = new IOModule();
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
        System.out.println("Please select the mode:(input(i),search(s),delete(d),output(o)[WIP]");
        char mainMode = input.next().charAt(0);
            if(mainMode == 'i' || mainMode == 'I'){
                System.out.println("Now in the INPUT mode");
//                System.out.println( "left");
                System.out.println("Please choose way to input: type-in(t) or import from .txt(i)[WIP]");
                char subMode = input.next().charAt(0);
                if(subMode == 't' || subMode == 'T'){
                    ioModule.itemTypeIn();
                    System.out.println("Finish!");
                    System.out.println("Preview: ");
                    System.out.print(ioModule.getItemList());
                }
//                if(subMode == 'i' || subMode == 'I'){
//
//                }
                else{
                    System.out.println("Invalid word");
                }//input end


            }
            else if(mainMode == 's' || mainMode == 'S'){
                System.out.println("Now in the SEARCH mode");
//                System.out.println("Enter the key word: ");

            }
            else if(mainMode == 'd' || mainMode == 'D'){
                System.out.println("Now in the DELETE mode");
                ioModule.itemDelete();
                System.out.println("Finish!");
                System.out.println("Preview: ");
                System.out.println(ioModule.getItemList());

            }
//            if(mode == 'o' || mode == 'O'){
//                System.out.println("Now in the OUTPUT mode");
//            }
            else{
                System.out.println("Invalid word. Please enter \"input\", \"search\", \"delete\" or \"i\", \"s\", \"d\". ");
                setup();
            }

    }

}
