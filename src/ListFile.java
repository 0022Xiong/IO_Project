import java.io.*;
import java.util.Scanner;

public class ListFile {

    public IOModule ioModule;

    Scanner input = new Scanner(System.in);

    //Auto output
    public void output(String listRecord) throws IOException {
        System.out.println("Tips: Don't forget to type in the name of the file. \n  Also don't forget the .txt!");
        System.out.println("If the file is not exist, then create one. ");
        System.out.println("Please enter the output location: ");
        String destinationFileOutput = input.nextLine();
        try (FileOutputStream output = new FileOutputStream(destinationFileOutput, true)) {
            this.ioModule = new IOModule();
            byte[] itemListBytes = listRecord.getBytes();
            output.write(itemListBytes);
            System.out.println("List has been output. ");
            output.close();
        } catch (IOException e) {
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
//            readTxtList(null);
            throw new RuntimeException(e);
        }
    }

}
