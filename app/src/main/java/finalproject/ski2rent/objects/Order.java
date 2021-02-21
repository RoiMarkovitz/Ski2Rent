package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class Order {

    public enum eStatus {Ordered, Picked, Returned, Late, Cancelled};
    public static int idGenerator;

    private int id = 0;
    private String customerKey = "";
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

    public Order setStatus(eStatus status) {
        // returned and cancelled are final order states, they cant change.
        if (this.status == eStatus.Returned || this.status == eStatus.Cancelled) {
            return this;
        }

        // if cancel is allowed and the input is "cancel", then update order status to cancelled
        if (isCancelAllowed() && status == eStatus.Cancelled) {
            this.status = eStatus.Cancelled;
            return this;
        }

        // if return order is allowed and the input is "return", then update order status to returned
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

    // can return order only after it has been picked up
    public boolean isOrderReturnable() {
        return System.currentTimeMillis() >= pickupDate;
    }

    // can pickup order only between the returnDate and the pickup date
    public boolean isOrderPickable() {
        return System.currentTimeMillis() >= pickupDate && pickupDate < returnDate;
    }

    // can cancel order only up to 24 hours before pickup date
    public boolean isCancelAllowed() {
        return pickupDate - (24 * 60 * 60 * 1000) > System.currentTimeMillis()
                && this.status != eStatus.Cancelled;

    }

} // Order
