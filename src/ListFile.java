import java.io.*;
import java.util.Scanner;

public class ListFile {

    public IOModule ioModule = new IOModule();

    Scanner input = new Scanner(System.in);

    //Auto output
    public void itemOutput(String listRecord) throws IOException {
        System.out.println("Preview: ");
        ioModule = new IOModule();
        System.out.print("\n" + listRecord);
        System.out.println("\u001b[31mTips: Don't forget to type in the name of the file. \n      Also don't forget the .txt!\u001b[0m");
        System.out.println("If the file does not exist, then create one. ");
        System.out.println("Please enter the output path: ");
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
        System.out.println("Please enter the read path: ");
        String destinationFileImport = input.nextLine();
        try {
            File read = new File(destinationFileImport);
            if (read.exists()){
                System.out.println(destinationFileImport + " is exist. ");
            }
            else {
                System.out.println(destinationFileImport + " is not exist. Please enter a valid location. ");
                destinationFileImport = "";
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
            int index = -1;
            int charDetect = 0;
            int character;
            while((character = readFile2.read()) != -1) {
                if (character == 58) {
                    index++;
                }
                if (character != 10) {
                    if(character == 32) {
                        charDetect++;
                    }//before space
                    else if(character != 32 && charDetect > 0) {
                        charDetect++;
                    }//after space
                }//before enter
                else if (character == 10 && index != -1 && charDetect != 0){
                    line[index] = charDetect - 1;
                    charDetect = 0;
                }//enter

            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 2: set the quantity of char

        try (FileInputStream readFile3 = new FileInputStream(destinationFileImport)){
            int index = -1;
            int lineNumber = 0;
            int charDetect = 0;
            int importItemBytes;
            String transInt = "";
            String transDouble = "";
            int indexBytes = 0;
            importBytes = new int[16];
            while (((importItemBytes = readFile3.read()) != -1)) {
                if (importItemBytes == 58) {
                    index++;
                    lineNumber++;
                    importBytes = new int[line[index]];
                }

                if (lineNumber % 4 == 1) {
                    if (importItemBytes != 10) {
                        if (importItemBytes == 32) {
                            charDetect++;
                        }//before space
                        else if (importItemBytes != 32 && charDetect > 0) {
                            importBytes[indexBytes] = importItemBytes;
                            importItemName += String.valueOf((char) importBytes[indexBytes]);
                            indexBytes++;
                        }//after space
                    }
                    else if (importItemBytes == 10){
                        charDetect = 0;
                        indexBytes = 0;
                    }
                }//name import

                else if (lineNumber % 4 == 2) {
                    if (importItemBytes != 10) {
                        if (importItemBytes == 32) {
                            charDetect++;
                        }//before space
                        else if (importItemBytes != 32 && charDetect > 0) {
                            importBytes[indexBytes] = importItemBytes;
                            importItemCode += String.valueOf((char) importBytes[indexBytes]);
                            indexBytes++;
                        }//after space
                    }
                    else if (importItemBytes == 10){
                        charDetect = 0;
                        indexBytes = 0;
                    }
                }//code import

                else if (lineNumber % 4 == 3) {
                    if (importItemBytes != 10) {
                        if (importItemBytes == 32) {
                            charDetect++;
                        }//before space
                        else if (importItemBytes != 32 && charDetect > 0) {
                            importBytes[indexBytes] = importItemBytes;
                            transInt += String.valueOf((char) importBytes[indexBytes]);

                            indexBytes++;
                        }//after space
                    }
                    else if (importItemBytes == 10){
                        charDetect = 0;
                        indexBytes = 0;
                    }
                }//quantity import

                else if (lineNumber % 4 == 0 && lineNumber != 0) {
                    if (importItemBytes != 10) {
                        if (importItemBytes == 32) {
                            charDetect++;
                        }//before space
                        else if (importItemBytes != 32 && charDetect > 0) {
                            importBytes[indexBytes] = importItemBytes;
                            transDouble += String.valueOf((char) importBytes[indexBytes]);
                            indexBytes++;
                        }//after space
                    }
                    else if (importItemBytes == 10){
                        charDetect = 0;
                        indexBytes = 0;
                    }
                }//Price import

                if (lineNumber / 4 == 1 && importItemBytes == 10) {
                    importItemNum = Integer.parseInt(transInt);
                    importItemPrice = Double.parseDouble(transDouble);
                    ioModule.storage.addLast(new Storage(
                            importItemName,
                            importItemCode,
                            importItemNum,
                            importItemPrice));
                    importItemName = "";
                    importItemCode = "";
                    lineNumber = 0;
                    transInt = "";
                    transDouble = "";

                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }//Phrase 3: import to the storage arraylist in the programme

        ioModule.currentItem = colonNumber / 4;
        System.out.println(ioModule.currentItem + " group(s) of data has been imported. ");
        ioModule.listRecord = ioModule.getItemList();
        System.out.println("Preview: ");
        System.out.println(ioModule.listRecord);

    }//read module end

    public void itemChangesOutput(String listRecord) {
        ioModule.clearScreen();
        System.out.println("Preview: ");
        ioModule = new IOModule();
        System.out.print("\n" + listRecord);
        System.out.println("\u001b[31mTips: Don't forget to type in the name of the file. \n      Also don't forget the .txt!\u001b[0m");
        System.out.println("If the file does not exist, then create one. ");
        System.out.println("Please enter the output path: ");
        String destinationFileOutput = input.nextLine();
        try (FileOutputStream output = new FileOutputStream(destinationFileOutput)) {
            this.ioModule = new IOModule();
            byte[] outputListBytes = listRecord.getBytes();
            output.write(outputListBytes);
            System.out.println("List has been output. ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//For delete and edit
        ioModule = new IOModule();

    }

}
