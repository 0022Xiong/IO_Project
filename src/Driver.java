
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
        ioModule = new IOModule();
        System.out.println("Please select the mode:(add(a),search(s),delete(d),output(o)[WIP]");
        char mainMode = input.next().charAt(0);
            if(mainMode == 'a' || mainMode == 'A'){
                System.out.println("Now in the ADD mode");
                System.out.println("Please choose way to add: type-in(t) or import from .txt(i)[WIP]");
                char subMode = input.next().charAt(0);
                if(subMode == 't' || subMode == 'T'){
                    ioModule.itemAddTypeIn();
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
                System.out.println("Please enter the code: ");
                String codeSearch = input.nextLine();
                ioModule.itemSearch(codeSearch);

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
