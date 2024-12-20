import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class IOModule {
    Scanner input = new Scanner(System.in);
    public ListFile listFile;
    public Driver driver = new Driver();
    public ArrayList<Storage> storage = new ArrayList<>();

    public String listRecord = "Error";
    public int currentItem = 0;

    public void itemAddTypeIn() {
        System.out.println("Now in the ADD mode");
        System.out.println("\u001b[31mWarning: avoid colons(:) and space \u001b[0m");
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
                ==>""");
        int option1 = input.nextInt();
        clearScreen();
        if (option1 == 1) {
            input.nextLine();//fix the bug
            itemAddTypeIn();
        }
        else {
            System.out.println("""
                    Do you want to output the list you are creating?
                    ---
                    1) Yes
                    2) No
                    ==>""");
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
        System.out.println("Press Enter to continue...");
        input.nextLine();
        importMenu();
    }

    public void importMenu() throws IOException {
        clearScreen();
        System.out.print("""
                IMPORT MENU
                ---
                1) Search
                2) Edit
                3) Delete
                4) Save changes
                0) Exit import menu
                ==>""");
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
        else if (option == 4) {
            listFile.itemChangesOutput(listFile.ioModule.listRecord);
        }
        else {
            driver.displayMenu();
            driver.actionMenu();
        }

    }

    public void itemSearch() throws IOException {
        clearScreen();
        System.out.println("Now in the SEARCH mode");
        System.out.print("""
                    Please select the mode:
                    1) By name
                    2) By code
                    3) By quantity
                    4) By price
                    ==>""");
        int option = input.nextInt();
        Storage searchItem = null;
        System.out.println("Please enter in the context: ");
        input.nextLine();//fix the bug
        String search = input.nextLine();
        if (listFile.ioModule.storage.isEmpty()) {
            System.out.println("There's nothing in the list. ");
        }
        else {
            System.out.println("You probably want to find: ");
            boolean result = false;
            int trigger = 0;
            for (int i = 0; i < listFile.ioModule.currentItem; i++){
                if (option == 1) {
                    result = listFile.ioModule.storage.get(i).getItemName().contains(search);
                } else if (option == 2) {
                    result = listFile.ioModule.storage.get(i).getItemCode().contains(search);
                } else if (option == 3) {
                    result = String.valueOf(listFile.ioModule.storage.get(i).getItemNum()).contains(search);
                } else if (option == 4) {
                    result = String.valueOf(listFile.ioModule.storage.get(i).getItemPrice()).contains(search);
                }
                if (result) {
                    trigger++;
                    searchItem = listFile.ioModule.storage.get(i);
                    System.out.println(">No." + (i + 1) + "\n" + searchItem);
                }
            }
            if(trigger == 0){
                System.out.println("Sorry, no result.");
            }
            System.out.println("Press Enter to continue");
            input.nextLine();
            System.out.println("""
                    After searching, you are going to:
                    1) Search more items in the list
                    2) Back to the IMPORT MENU
                    """);
            int subOption = input.nextInt();
            if(subOption == 1){
                itemSearch();
            }
            else{
                importMenu();
            }

        }

    }

    public void itemEdit() throws IOException {
        clearScreen();
        System.out.println("\u001b[31mTips: Remember to save the changes! \u001b[0m");
        System.out.println("Please enter the number of the data you want to edit: ");
        int num = input.nextInt();
        System.out.println("Current data: ");
        System.out.println(listFile.ioModule.getItemListOrdered(num));
        System.out.println("\u001b[31mWarning: avoid colons(:) and space \u001b[0m");
        System.out.println("Please enter the name of the item: ");
        input.nextLine();//fix the bug
        String itemName = input.nextLine();
        System.out.println("Please enter the code of the item: ");
        String itemCode = input.nextLine();
        System.out.println("Please enter the quantity of the item: ");
        int itemNum = input.nextInt();
        System.out.println("Please enter the price of the item: ");
        double itemPrice = input.nextDouble();
        listFile.ioModule.storage.set(num - 1,new Storage(
                        itemName,
                        itemCode,
                        itemNum,
                        itemPrice
                )
        );
        listFile.ioModule.listRecord = listFile.ioModule.getItemList();
        System.out.println("Finish!");
        System.out.println("""
                After editing, you are going to:
                1) Edit more items
                2) Back to the IMPORT MENU
                ==>""");
        int option = input.nextInt();
        if (option == 1) {
            itemEdit();
        }
        else {
            importMenu();
        }
    }

    public void itemDelete() throws IOException {
        clearScreen();
        System.out.println("\u001b[31mTips: Remember to save the changes! \u001b[0m");
        System.out.println("Please enter the number of the data you want to delete: ");
        int num = input.nextInt();
        listFile.ioModule.storage.set(num - 1, null);
        System.out.println("Finish!");
        listFile.ioModule.listRecord = listFile.ioModule.getItemList();
        System.out.println("Preview: ");
        System.out.println(listFile.ioModule.listRecord);
        System.out.println("""
                After deleting, you are going to:
                1) Delete more items
                2) Back to the IMPORT MENU
                ==>""");
        int option = input.nextInt();
        if (option == 1) {
            itemDelete();
        }
        else {
            importMenu();
        }

    }//delete end

    public String getItemListOrdered(int orderedItem) {
        String listOfItem = "";
        listOfItem += "No." + orderedItem + "\n" + storage.get(orderedItem - 1);
        return listOfItem;
    }

    public String getItemListCurrent() {
        String listOfItem = "";
        listOfItem += "No." + currentItem + "\n" + storage.get(currentItem - 1);
        return listOfItem;
    }

    public String getItemList() {
        String listOfItem = "";
        for (int i = 1; i < currentItem + 1; i++) {
            listOfItem += "\n" + ">No." + i + "\n" + storage.get(i - 1);
        }
        return listOfItem;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
