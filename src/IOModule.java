import java.util.Scanner;

public class IOModule {
    Scanner input = new Scanner(System.in);

    private Storage storage[];
    private int maxItem;

    public IOModule(int capability){
        storage = new Storage[capability];
    }

    int currentItem = 0;
//    public IOModule() {
        public void itemTypeIn () {
            System.out.println("Please enter the name of the item: ");
            String itemName = input.nextLine();
            System.out.println("Please enter the stock of the item: ");
            int itemNum = input.nextInt();
            System.out.println("Please enter the price of the item: ");
            double itemPrice = input.nextDouble();
//        System.out.println(storage.toString());
            storage[currentItem] = new Storage(itemName, itemNum, itemPrice);
            currentItem++;

            System.out.println("Finish!");
            System.out.println("Preview: ");
            System.out.print(getItemList());
            System.out.println(maxItem - currentItem + "line(s) remain");
            System.out.println("Add more?(Y/n)");
            char sub2Mode = input.next().charAt(0);
            if (sub2Mode == 'y' || sub2Mode == 'Y')
                itemTypeIn();

        }

        public void itemDelete () {
            System.out.println("Please enter the number you want to delete: ");
            int num = input.nextInt();
            storage[num - 1] = null;
            System.out.println("Finish!");
            System.out.println("Preview: ");
            System.out.println(getItemList());
            System.out.println("Delete more?(Y/n)");
            char subMode = input.next().charAt(0);
            if (subMode == 'y' || subMode == 'Y')
                itemDelete();

        }

        public String getItemList () {
            String listOfItem = "";
            for (int i = 1; i <= currentItem + 1; i++) {
                listOfItem += i + ": " + storage[i - 1] + "\n";
            }
            return listOfItem;
        }
//    }


}
