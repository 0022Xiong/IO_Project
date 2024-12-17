import java.io.*;
import java.util.Scanner;

public class ListFile {

    public IOModule ioModule;

    Scanner input = new Scanner(System.in);

    //Auto output
    public void itemOutput(String listRecord) throws IOException {
        System.out.println("Preview: ");
        ioModule = new IOModule();
        System.out.print("\n" + listRecord);
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
            int character;
            while((character = readFile1.read()) != -1){
                if ((character) == 58){
                    colonNumber++;
                }
            }
            line = new int[colonNumber];
        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 1: read the valid line

        try (FileInputStream readFile2 = new FileInputStream(destinationFileImport)){
            int lineNumber = -1;
            int charDetect = 0;
            int character;
            while((character = readFile2.read()) != -1) {
                if (character == 58) {
                    lineNumber++;
                }
                if (character != 10) {
                    if(character == 32) {
                        charDetect++;
                    }//before space
                    else if(character != 32 && charDetect > 0) {
                        charDetect++;
                    }//after space
                }//before enter
                else if (character == 10 && lineNumber != -1){
                    line[lineNumber] = charDetect - 1;
                    charDetect = 0;
                }//enter

            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 2: set the quantity of char

        try (FileInputStream readFile3 = new FileInputStream(destinationFileImport)){
            int index = -1;
            int lineNumber = 0;
            int count = 0;
            while (((importItemBytes = readFile3.read()) != -1)){
                if (importItemBytes == 58) {
                    index++;
                    lineNumber++;
                }

                if (lineNumber % 4 == 1) {
                    importBytes = new int[line[index]];
                    for (int i = 0; i < line[index]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemName = String.valueOf((char) importBytes[i]);
                        count++;
                    }

                }//name import

                else if (lineNumber % 4 == 2) {
                    importBytes = new int[line[index]];
                    for (int i = 0; i < line[index]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemCode = String.valueOf((char) importBytes[i]);
                        count++;
                    }

                }//code import

                else if (lineNumber % 4 == 3) {
                    importBytes = new int[line[index]];
                    for (int i = 0; i < line[index]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemNum = Integer.parseInt(String.valueOf((char) importBytes[i]));
                        count++;
                    }

                }//quantity import

                else if (lineNumber % 4 == 0 && lineNumber != 0) {
                    importBytes = new int[line[index]];
                    for (int i = 0; i < line[index]; i++) {
                        importBytes[i] = importItemBytes;
                        importItemPrice = Double.parseDouble(String.valueOf((char) importBytes[i]));
                        count++;
                    }

                }//Price import

                if (count / 5 == 1){
                    ioModule = new IOModule();
                    ioModule.storage.add(new Storage(
                            importItemName,
                            importItemCode,
                            importItemNum,
                            importItemPrice));
                    count = 0;
                }

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 3: import to the storage arraylist in the programme

        ioModule.currentItem = colonNumber / 4;
        System.out.println(ioModule.currentItem + " group(s) of data has been imported. ");
        System.out.println("Preview: ");
        System.out.println(ioModule.listRecord);

    }//read module end

}
