package finalproject.ski2rent.objects;

import android.text.format.DateFormat;

import java.util.ArrayList;

public class Order {

    public enum eStatus {Ordered, Picked, Returned, Late, Cancelled};
    public static int idGenerator;

    private int id = 0;
    private String customerKey = "";
    // maybe add discount of the order if needed to show later, not so important
    private ArrayList<RentedBoard> boards = new ArrayList<>();
    private long orderDate = 0;
    private long pickupDate = 0;
    private long returnDate = 0;
    private eStatus status;

    public Order() {}

    public Order(String customerKey, ArrayList<RentedBoard> boards, long pickupDate, long returnDate) {
        this.boards = boards;
        this.orderDate = System.currentTimeMillis();
        this.customerKey = customerKey;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;

        // late and return possible
//        this.pickupDate = System.currentTimeMillis() - (24 * 60 * 60 * 1000) * 6;
//        this.returnDate = System.currentTimeMillis() - (24 * 60 * 60 * 1000) * 3;
        // picked and return possible
//        this.pickupDate = System.currentTimeMillis() - (24 * 60 * 60 * 1000) * 2;
//        this.returnDate = pickupDate + (24 * 60 * 60 * 1000) * 7;
        // Ordered and cancel possible
//        this.pickupDate = System.currentTimeMillis() + (24 * 60 * 60 * 1000) * 7;
//        this.returnDate = pickupDate + (24 * 60 * 60 * 1000) * 4;
        // Ordered and nothing is possible. 24 hours window
//        this.pickupDate = System.currentTimeMillis() + (12 * 60 * 60 * 1000);
//        this.returnDate = pickupDate + (24 * 60 * 60 * 1000) * 4;

        this.status = eStatus.Ordered;
        this.id = ++idGenerator;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public Order setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
        return this;
    }

    public eStatus getStatus() {
        return status;
    }

//    public Order setStatus(eStatus status) {
//        this.status = status;
//        return this;
//    }

    public Order setStatus(eStatus status) {
        if (this.status == eStatus.Returned || this.status == eStatus.Cancelled) {
            return this;
        }

        if (isCancelAllowed() && status == eStatus.Cancelled) {
            this.status = eStatus.Cancelled;
            return this;
        }

        if (isOrderReturnable() && status == eStatus.Returned) {
            this.status = eStatus.Returned;
            return this;
        }

        this.status = status;
        return this;
    }

    public void updateStatus() {
        if (this.status == eStatus.Returned || this.status == eStatus.Cancelled) {
            return;
        }

        if (returnDate < System.currentTimeMillis()) {
            this.status = eStatus.Late;
            return;
        }

        if (isOrderPickable()) {
            this.status = eStatus.Picked;
            return;
        }
    }

    public long getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(long orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public Order setReturnDate(long returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public long getPickupDate() {
        return pickupDate;
    }

    public Order setPickupDate(long pickupDate) {
        this.pickupDate = pickupDate;
        return this;
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public ArrayList<RentedBoard> getBoards() {
        return boards;
    }

    public Order setBoards(ArrayList<RentedBoard> boards) {
        this.boards = boards;
        return this;
    }

    public double calculateTotalPrice() {
        double amount = 0;
        for (int i = 0; i < boards.size(); i++) {
            amount = amount + boards.get(i).getPrice();
        }
        return amount;
    }

    public boolean isOrderReturnable() {
        return System.currentTimeMillis() >= pickupDate;
    }

    public boolean isOrderPickable() {
        return System.currentTimeMillis() >= pickupDate && pickupDate < returnDate;
    }

    public boolean isCancelAllowed() {
        return pickupDate - (24 * 60 * 60 * 1000) > System.currentTimeMillis()
                && this.status != eStatus.Cancelled;

    }

    public String description() {
        String dateFormat = DateFormat.format("dd.MM.yy", orderDate).toString();
        StringBuilder stringBuilder = new StringBuilder("Order id: " + getId()  + "\n order date: " + dateFormat + "\n");
        for (int i = 0; i < boards.size(); i++) {
            stringBuilder.append(boards.get(i).description() + "\n");
        }

        return stringBuilder.toString();

    }

} // Order
