import java.io.*;
import java.util.Scanner;

public class Driver {

    public IOModule ioModule;
    public ListFile listFile;
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
        System.out.println("Please select the mode:(add(a),search(s),delete(d)");
//      edit(e)
        char mainMode = input.next().charAt(0);
            if(mainMode == 'a' || mainMode == 'A'){
                System.out.println("Now in the ADD mode");
                System.out.println("Please choose way to add: type-in(t)");
//                 or import from .txt(i)"
                char subMode = input.next().charAt(0);
                if(subMode == 't' || subMode == 'T'){
                    ioModule.itemAddTypeIn();
                    System.out.println("Do you want to output the list you are creating? (Y/n)");
                    char decisionOutput = input.next().charAt(0);
                    if (decisionOutput == 'Y' || decisionOutput == 'y') {
                        try {
                            ioModule.listRecord = ioModule.getItemList();
                            this.listFile = new ListFile();
                            listFile.output(ioModule.listRecord);
//                            String destinationFile = listFile.setOutputDestination();
//                            listFile.autoOutputList(destinationFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

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
                else{
                    System.out.println("Invalid word");
                }

            }//input end
            else if(mainMode == 's' || mainMode == 'S'){
                System.out.println("Now in the SEARCH mode");
                System.out.println("Please enter the code of the item: ");
                String codeSearch = input.nextLine();
                ioModule.itemCodeSearch(codeSearch);

            }//search end
            else if(mainMode == 'd' || mainMode == 'D'){
                System.out.println("Now in the DELETE mode");
                ioModule.itemDelete();

            }//delete end

//            if(mode == 'e' || mode == 'E'){
//                System.out.println("Now in the EDIT mode");
//            }//edit end

            else{
                System.out.println("Invalid word. Please enter \"input\", \"search\", \"delete\" or \"i\", \"s\", \"d\". ");
                setup();
            }

    }

}
