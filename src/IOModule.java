import java.util.Scanner;
import java.util.ArrayList;

public class IOModule {
    Scanner input = new Scanner(System.in);

    public ArrayList<Storage> storage = new ArrayList<Storage>();

    public String listRecord = "Error";
    public int currentItem = 0;

    public void itemAddTypeIn() {
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
        System.out.println("Add more?(Y/n)");
        char sub2Mode = input.next().charAt(0);
        if (sub2Mode == 'y' || sub2Mode == 'Y') {
            clearScreen();
            input.nextLine();//fix the bug
            itemAddTypeIn();
        }

    }//input type-in end

    public Storage itemSearch(int subMode) {
        String search = input.nextLine();
        Storage searchItem = null;
        if (storage.isEmpty()) {
            return null;
        }
        else {
            System.out.println("You probably want to find: ");
            boolean result = false;
            for (int i = 0; i < currentItem; i++){
                switch (subMode){
                    case 1 -> result = storage.get(i).getItemName().contains(search);
                    case 2 -> result = storage.get(i).getItemCode().contains(search);
                    case 3 -> result = String.valueOf(storage.get(i).getItemNum()).contains(search);
                    case 4 -> result = String.valueOf(storage.get(i).getItemPrice()).contains(search);
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

    public void itemDelete() {
        System.out.println("Tips: It is suggested that you should make a backup before you delete the data from the list. ");
        System.out.println("Please enter the number of the data you want to delete: ");
        int num = input.nextInt();
        storage.set(num - 1, null);
        System.out.println("Finish!");
        System.out.println("Preview: ");
        System.out.println(getItemList());
        System.out.println("Delete more?(Y/n)");
        char subMode = input.next().charAt(0);
        if (subMode == 'y' || subMode == 'Y') {
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
