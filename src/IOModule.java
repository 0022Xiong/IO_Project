import java.util.Scanner;
import java.util.ArrayList;

public class IOModule {
    Scanner input = new Scanner(System.in);

//    private Storage storage[];
    public ArrayList<Storage> storage = new ArrayList<Storage>();
//    private int maxItem;
//
//    public IOModule(int capability){
//        this.maxItem = capability;
//        storage = new Storage[capability];
//    }
    public boolean add(Storage item){
        return storage.add(item);
    }

    int currentItem = 0;

    public void itemTypeIn () {
        input.nextLine();//fix the bug
        System.out.println("Please enter the name of the item: ");
        String itemName = input.nextLine();
        System.out.println("Please enter the code of the item: ");
        String itemCode = input.nextLine();
        System.out.println("Please enter the stock of the item: ");
        int itemNum = input.nextInt();
        System.out.println("Please enter the price of the item: ");
        double itemPrice = input.nextDouble();
   //     storage[currentItem] = new Storage(itemName, itemNum, itemPrice);
        boolean isAdded = storage.add(new Storage(
                itemName,
                itemCode,
                itemNum,
                itemPrice
                )
        );
        if (isAdded == true) {
            System.out.println("Finish!");
            currentItem++;
        }
        else
            System.out.println("No item added");

        System.out.println("Preview: ");
        System.out.print(getItemList());
//        System.out.println(maxItem - currentItem + " line(s) remain");
        System.out.println("Add more?(Y/n)");
        char sub2Mode = input.next().charAt(0);
        if (sub2Mode == 'y' || sub2Mode == 'Y'){
            clearScreen();
            itemTypeIn();
        }

    }//input type-in end
//        public void itemImport(){
//
//        }//input import end

    public void itemDelete () {
        System.out.println("Please enter the number you want to delete: ");
        int num = input.nextInt();
        storage.set(num - 1, null);
        System.out.println("Finish!");
        System.out.println("Preview: ");
        System.out.println(getItemList());
        System.out.println("Delete more?(Y/n)");
        char subMode = input.next().charAt(0);
        if (subMode == 'y' || subMode == 'Y'){
            clearScreen();
            itemDelete();
        }

    }//delete end

    public String getItemList () {
        String listOfItem = "";
        for (int i = 1; i < currentItem + 1; i++) {
            listOfItem += i + ": " + storage.get(i - 1) + "\n";
        }
        return listOfItem;
    }


    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
