

public class Storage {

    private String itemName;
//    private int itemCode;
    private int itemNum;
    private double itemPrice;

    public Storage(String itemName, int itemNum, double itemPrice){
        setItemName(itemName);
        setItemNum(itemNum);
        setItemPrice(itemPrice);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        if(itemNum >= 0)
            this.itemNum = itemNum;
        else {
            System.out.println("invalid number");
        }
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        if(itemPrice >= 0.0)
            this.itemPrice = itemPrice;
        else {
            System.out.println("invalid price");
        }
    }
}
