import java.util.Scanner;

public class IOModule {
//    Storage storage = new Storage();
    Scanner input = new Scanner(System.in);

    Storage storage[];
    int maxItem = 0;
    int currentItem = 0;

    public void itemTypeIn(){
        System.out.println("Please enter the name of the item: ");
        String itemName = input.nextLine();
        System.out.println("Please enter the stock of the item: ");
        int itemNum = input.nextInt();
        System.out.println("Please enter the price of the item: ");
        Double itemPrice = input.nextDouble();
//        System.out.println(storage.toString());
        storage[currentItem] = new Storage(itemName, itemNum, itemPrice);
        currentItem++;

    }

    public void itemDelete(){
        System.out.println("Please enter the number you want to delete: ");
        int num = input.nextInt();
        storage[num + 1] = null;
    }

    public String getItemList(){
        String listOfItem = "";
        for(int i = 0; i < currentItem; i++){
            listOfItem += i + ": " + storage[i] + "\n";
        }
        return listOfItem;
    }



}
