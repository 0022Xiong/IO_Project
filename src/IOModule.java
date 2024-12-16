import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class IOModule {
    Scanner input = new Scanner(System.in);
    public ListFile listFile;
    public ArrayList<Storage> storage = new ArrayList<Storage>();

    public String listRecord = "Error";
    public int currentItem = 0;

    public void itemAddTypeIn() {
        System.out.println("Now in the ADD mode");
        System.out.println("Warning: avoid colons(:) ");
        System.out.println("Please enter the name of the item: ");
        String itemName = input.nextLine();
        System.out.println("Please enter the code of the item: ");
        String itemCode = input.nextLine();
        System.out.println("Please enter the quantity of the item: ");
        int itemNum = input.nextInt();
        System.out.println("Please enter the price of the item: ");
        double itemPrice = input.nextDouble();
        boolean isAdded = storage.add(new Storage(
                        itemName,
                        itemCode,
                        itemNum,
                        itemPrice
                )
        );
        if (isAdded) {
            System.out.println("Finish!");
            currentItem++;
        } else
            System.out.println("No item added");

        System.out.println("Preview: ");
        System.out.print(getItemListCurrent());
        System.out.println("""
                Add more?
                ---
                1) Yes
                2) No
                ==>
                """);
        int option1 = input.nextInt();
        clearScreen();
        if (option1 == 1) {
            input.nextLine();//fix the bug
            itemAddTypeIn();
        }
        else {
            System.out.println("""
                    Do you want to output the list you are creating? (Y/n)
                    ---
                    1) Yes
                    2) No
                    ==>
                    """);
            int option2 = input.nextInt();
            if (option2 == 1){
                listRecord = getItemList();
                try {
                    listFile = new ListFile();
                    listFile.itemOutput(listRecord);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }

    }//input type-in end

    public void itemImport() throws IOException {
        listFile = new ListFile();
        System.out.println("Loading......");
        String importDestination = listFile.setImportDestination();
        listFile.readTxtList(importDestination);
        clearScreen();
        System.out.print("""
                MENU
                ---
                1) Search
                2) Edit
                3) Delete
                ==>
                """);
        int option = input.nextInt();
        if (option == 1){
            itemSearch();
        }
        else if (option == 2) {
            itemEdit();
        }
        else if (option == 3) {
            itemDelete();
        }

    }

    public Storage itemSearch() {
        clearScreen();
        System.out.println("Now in the SEARCH mode");
        System.out.print("""
                    Please select the mode:
                    1) By name
                    2) By code
                    3) By quantity
                    4) By price
                    """);
        int option = input.nextInt();
        String search = input.nextLine();
        Storage searchItem = null;
        if (storage.isEmpty()) {
            return null;
        }
        else {
            System.out.println("You probably want to find: ");
            boolean result = false;
            for (int i = 0; i < currentItem; i++){
                if (option == 1) {
                    result = storage.get(i).getItemName().contains(search);
                } else if (option == 2) {
                    result = storage.get(i).getItemCode().contains(search);
                } else if (option == 3) {
                    result = String.valueOf(storage.get(i).getItemNum()).contains(search);
                } else if (option == 4) {
                    result = String.valueOf(storage.get(i).getItemPrice()).contains(search);
                }
                if (result) {
                    searchItem = storage.get(i);
                    System.out.println(searchItem);
                }
            }
        }
        return searchItem;
    }

//        if (!storage.isEmpty()) {
//            for (Storage s : storage)
//                if (s.getItemCode().equals(codeSearch))
//                    searchItem = s;
//                else
//                    System.out.println("Sorry, no result. ");
//        }
//        else {
//            System.out.println("There is nothing in the list. ");
//            return null;
//        }
    public void itemEdit() {

    }


    public void itemDelete() {
        System.out.println("Tips: It is suggested that you should make a backup before you delete the data from the list. ");
        System.out.println("Please enter the number of the data you want to delete: ");
        int num = input.nextInt();
        storage.set(num - 1, null);
        System.out.println("Finish!");
        System.out.println("Preview: ");
        System.out.println(getItemList());
        System.out.println("""
                Delete more?
                ---
                1) Yes
                2) No
                ==>
                """);
        int option = input.nextInt();
        if (option == 1) {
            clearScreen();
            itemDelete();
        }

    }//delete end

    public String getItemListCurrent() {
        String listOfItem = "";
        listOfItem += "No." + currentItem + "\n" + storage.get(currentItem - 1);
        return listOfItem;
    }

    public String getItemList() {
        String listOfItem = "";
        for (int i = 1; i < currentItem + 1; i++) {
            listOfItem += "No." + i + "\n" + storage.get(i - 1) + "\n";
        }
        return listOfItem;
    }


    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
