import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ListFile {

    public IOModule ioModule;

    Scanner input = new Scanner(System.in);

    //Auto output
    public void output(String listRecord) throws IOException {
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

    }//Auto output end


    //Import wip
    public String setImportDestination() throws IOException{
        System.out.println("Please enter the read location: ");
        String destinationFileImport = input.nextLine();
        try {
            File read = new File(destinationFileImport);
            if (read.exists())
                System.out.println(destinationFileImport + " is exist. ");
            else {
                System.out.println(destinationFileImport + " is not exist. Please enter a valid location. ");
                setImportDestination();
            }
            return destinationFileImport;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void readTxtList(String destinationFileImport) throws IOException{


        String importItemName;
        String importItemCode;
        int importItemNum;
        int importItemPrice;
//        System.out.println("Preview");
        try(FileInputStream readFile = new FileInputStream(destinationFileImport)){
            int importItemBytes;
            int[] importBytes;
            int count = 1;
            int subCount = 0;
            int lineCharDetect = 0;
            int lineCharRecord = 0;
            int i = 0;
            int j = 0;
            while ((importItemBytes = readFile.read()) != -1) {
                lineCharDetect++;
                lineCharRecord++;
                if ((importItemBytes = readFile.read()) == 10) {
                    count++;
                }
                if (count % 6 == 2 && lineCharDetect == 6){
                    importBytes = new int[setArrayVolume(destinationFileImport)];
                    importItemName = String.valueOf((char) importItemBytes);

                }


//                if ((importItemBytes = readFile.read()) != 32) {
//                    j++;
//                }
//                else if ((importItemBytes = readFile.read()) != 10) {
//                    i++;
//                }
//                else if {
//                    lineCharDetect = 0;
//                    lineCharRecord = 0;
//
//                }
//                importBytes = new int[i - j];// ensure the volume of the array



//                if (importItemBytes == 10) {
//
//
//                }


//                 {
//                    for (int i = 0 ; i < lineCharRecord - 6; i ++){
//                        importBytes[i] = importItemBytes;
//                    }
////                    importItemName = toString(importBytes);
////                    ioModule.storage.
////
////                    importBytes = new int[1];
//                } else if (count % 6 == 3 && lineCharDetect == 6) {
//
//                } else if (count % 6 == 4 && lineCharDetect == 10) {
//
//                } else if (count % 6 == 5 && lineCharDetect == 12) {
//                }

//                if (importItemBytes == 58){
//
//                }
                subCount++;


                System.out.print((char) importItemBytes);

            }
            }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private int setArrayVolume(String destinationFileImport) throws IOException {
        try (FileInputStream readFile = new FileInputStream(destinationFileImport)) {
            int i = 0;
            int j = 0;
            while (readFile.read() != 10) {
                if ((readFile.read()) != 32) {
                    j++;
                } else if (readFile.read() != 10) {
                    i++;
                }
            }
            return i - j;// ensure the volume of the array
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}