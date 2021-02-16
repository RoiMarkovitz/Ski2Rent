package finalproject.ski2rent.objects;

public class RentedBoard extends Board {

    private int length = 0;
    private int quantity = 0;

    public RentedBoard(){}

    public int getLength() {
        return length;
    }

    public RentedBoard setLength(int length) {
        this.length = length;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public RentedBoard setQuantity(int quantity) {
        this.quantity = quantity;
        this.price = price * quantity;
        return this;
    }


    public String description() {
        String str = super.description();
        return str + " " + getLength();
    }
}
