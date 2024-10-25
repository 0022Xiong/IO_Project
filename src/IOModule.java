import java.util.Scanner;

public class IOModule {
    Scanner input = new Scanner(System.in);

    Storage storage[];

    public void itemTypeIn(){
        System.out.println("Please enter the name of the item: ");
        String itemName = input.nextLine();
        System.out.println("Please enter the stock of the item: ");
        int itemNum = input.nextInt();
        System.out.println("Please enter the price of the item: ");
        Double itemPrice = input.nextDouble();

    }


}
