import java.io.*;
import java.util.Scanner;

public class ListFile {

    public IOModule ioModule;

    Scanner input = new Scanner(System.in);

    //Auto output
    public void itemOutput(String listRecord) throws IOException {
        System.out.println("Preview: ");
        ioModule = new IOModule();
        System.out.print(ioModule.getItemList());
        System.out.println("Tips: Don't forget to type in the name of the file. \n      Also don't forget the .txt!");
        System.out.println("If the file is not exist, then create one. ");
        System.out.println("Please enter the output location: ");
        String destinationFileOutput = input.nextLine();
        try (FileOutputStream output = new FileOutputStream(destinationFileOutput, true)) {
            this.ioModule = new IOModule();
            byte[] outputListBytes = listRecord.getBytes();
            output.write(outputListBytes);
            System.out.println("List has been output. ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }//Auto output module end

    //Import needs test
    public String setImportDestination() throws IOException{
        System.out.println("Please enter the read location: ");
        String destinationFileImport = input.nextLine();
        try {
            File read = new File(destinationFileImport);
            if (read.exists()){
                System.out.println(destinationFileImport + " is exist. ");
            }
            else {
                System.out.println(destinationFileImport + " is not exist. Please enter a valid location. ");
                setImportDestination();
            }
            return destinationFileImport;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readTxtList(String destinationFileImport) {

        String importItemName = "";
        String importItemCode = "";
        int importItemNum = 0;
        double importItemPrice = 0;

        int importItemBytes;

        int[] importBytes;
        int colonNumber = 0;

        int[] line;
        try(FileInputStream readFile1 = new FileInputStream(destinationFileImport)){
            while((readFile1.read()) != -1){
                if ((readFile1.read()) == 58){
                    colonNumber++;
                }
            }
            line = new int[colonNumber];
        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 1: read the valid line

        try (FileInputStream readFile2 = new FileInputStream(destinationFileImport)){
            int lineNumber = 0;
            int charDetect = -1;
            while(readFile2.read() != -1) {
                if (readFile2.read() == 58) {
                    lineNumber++;
                }
                if (readFile2.read() != 10) {
                    if(readFile2.read() == 32) {
                        charDetect++;
                    }//before space
                    else if(readFile2.read() != 32 && charDetect >= 0) {
                        charDetect++;
                    }//after space
                }//before enter
                else {
                    line[lineNumber] = charDetect;
                    charDetect = -1;
                }//enter

            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 2: set the quantity of char

        try (FileInputStream readFile3 = new FileInputStream(destinationFileImport)){
            int lineNumber = -1;
            while (((importItemBytes = readFile3.read()) != -1)){
                if (readFile3.read() == 58) {
                    lineNumber++;
                }
                if (lineNumber % 6 == 2) {
                    importBytes = new int[line[lineNumber]];
                    for (int i = 0; i <= line[lineNumber]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemName = String.valueOf((char) importBytes[i]);
                    }

                }//name import

                else if (lineNumber % 6 == 3) {
                    importBytes = new int[line[lineNumber]];
                    for (int i = 0; i <= line[lineNumber]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemCode = String.valueOf((char) importBytes[i]);
                    }

                }//code import

                else if (lineNumber % 6 == 4) {
                    importBytes = new int[line[lineNumber]];
                    for (int i = 0; i <= line[lineNumber]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemNum = Integer.parseInt(String.valueOf((char) importBytes[i]));
                    }

                }//quantity import

                else if (lineNumber % 6 == 5) {
                    importBytes = new int[line[lineNumber]];
                    for (int i = 0; i <= line[lineNumber]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemPrice = Double.parseDouble(String.valueOf((char) importBytes[i]));
                    }

                }//Price import

                ioModule = new IOModule();
                ioModule.storage.add(new Storage(
                        importItemName,
                        importItemCode,
                        importItemNum,
                        importItemPrice));

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 3: import to the storage arraylist in the programme

        ioModule.currentItem = colonNumber / 5;
        System.out.println(ioModule.currentItem + "group(s) of data has been imported. ");
        System.out.println("Preview: ");
        System.out.println(ioModule.getItemList());

    }//read module end

//    public void itemImport() throws IOException {
//        readTxtList(setImportDestination());
//        importMenu();
//    }

//    public int importMenu(){
//        ioModule.clearScreen();
//        System.out.println("////////////////////////////////////////");
//        System.out.print("""
//                MENU
//                1) search
//                2) Edit
//                3) Delete
//                """);
//        System.out.println("////////////////////////////////////////");
//        return input.nextInt();
//    }

//    public void actionImportMenu(int option){
//        option=importMenu();
//        IOModule ioModule = new IOModule();
//        while(option !=0){
//
//            switch(option){
////                case 1->itemSearch();
//               // case 2-
//                case 3 -> ioModule.itemDelete();
//
//
//            }
//
//        }
//
//    }
//
}
