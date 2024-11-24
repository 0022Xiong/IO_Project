import java.io.*;
import java.util.Scanner;

public class ListFile {

    public IOModule ioModule;

    Scanner input = new Scanner(System.in);
    //Auto output
    public String setOutputDestination() throws IOException {
        System.out.println("Tips: Don't forget to type in the name of the file. \n Please enter the output location: ");
        String destinationFileOutput = input.nextLine();
        try {
            File output = new File(destinationFileOutput);
            if (output.createNewFile()){
                System.out.println("List file " + destinationFileOutput + " is created! ");
            }
            else {
                System.out.println("Invalid location. Please try again");
                setOutputDestination();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return destinationFileOutput;
    }

    public void autoOutputList(String destinationFile) throws IOException{
        try (BufferedWriter output = new BufferedWriter(new FileWriter(destinationFile))){
            output.write(ioModule.getItemList());
            System.out.println("List has been output. ");
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }//Auto output end

    //Import
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

    public void readTxtList(String destinationFile) throws IOException{
        try(BufferedReader read = new BufferedReader(new FileReader(destinationFile))){
            String line;
            while ((line = read.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
