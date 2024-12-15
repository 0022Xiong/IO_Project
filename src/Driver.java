import java.io.*;
import java.util.Scanner;

public class Driver {

    public IOModule ioModule;
    public ListFile listFile;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("////////////////////////////////////////");
        System.out.println("IO SYSTEM FOR SHOPS");
        System.out.println("////////////////////////////////////////");
        System.out.println("Loading...");
        Driver driver = new Driver();

        driver.setup();
    }

    public void setup() {
        ioModule = new IOModule();
        System.out.println("Please select the mode:(add(a),search(s),delete(d)");
//      edit(e)
        char mainMode = input.next().charAt(0);
        if (mainMode == 'a' || mainMode == 'A') {
            ioModule.clearScreen();
            System.out.println("Now in the ADD mode");
//            System.out.println("Please choose way to add: type-in(t) or import from .txt(i)");
//            char subMode = input.next().charAt(0);
//            if (subMode == 't' || subMode == 'T') {
            ioModule.itemAddTypeIn();
            System.out.println("Do you want to output the list you are creating? (Y/n)");
            char decisionOutput = input.next().charAt(0);
            if (decisionOutput == 'Y' || decisionOutput == 'y') {
                try {
                    ioModule.listRecord = ioModule.getItemList();
                    this.listFile = new ListFile();
                    listFile.itemOutput(ioModule.listRecord);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //               }

            }

//                else if(subMode == 'i' || subMode == 'I'){
//                    try {
//                        listFile.setImportDestination();
//                        String destinationFile = listFile.setImportDestination();
//                        listFile.readTxtList(destinationFile);
//
//                    }catch (IOException e){
//                        throw new RuntimeException();
//                    }
//                }
            else {
                System.out.println("Invalid word");
            }

        }//input end
        else if (mainMode == 's' || mainMode == 'S') {
            ioModule.clearScreen();
            System.out.println("Now in the SEARCH mode");
            System.out.print("""
                    Please select the mode:
                    1) By name
                    2) By code
                    3) By quantity
                    4) By price
                    """);
            int subMode = input.nextInt();
            ioModule.itemSearch(subMode);

        }//search end
        else if (mainMode == 'd' || mainMode == 'D') {
            ioModule.clearScreen();
            System.out.println("Now in the DELETE mode");
            ioModule.itemDelete();

        }//delete end

//            if(mode == 'e' || mode == 'E'){
//                System.out.println("Now in the EDIT mode");
//            }//edit end

        else {
            ioModule.clearScreen();
            System.out.println("Invalid word. Please enter \"input\", \"search\", \"delete\" or \"i\", \"s\", \"d\". ");
            setup();
        }

    }

    private void dummyData() {
        Storage sample1 = new Storage(
                "1",
                "1",
                1,
                1.0
        );
    }

//    private int displayMenu() {
//        ioModule.clearScreen();
//        System.out.println("////////////////////////////////////////");
//        System.out.print("""
//                MENU
//                1) Add new item(s)
//                2) Edit the current list
//                3) Exit
//                """);
//        System.out.println("////////////////////////////////////////");
//        return input.nextInt();
//    }
//    private void actionMenu() {
//
//    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}