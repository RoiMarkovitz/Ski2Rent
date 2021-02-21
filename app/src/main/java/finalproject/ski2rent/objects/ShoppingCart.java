package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<RentedBoard> boardsInCart = new ArrayList<>();
    private String customerKey = "";
    private long pickupDate = 0;
    private long returnDate = 0;

    public ShoppingCart() {}

    public ArrayList<RentedBoard> getBoardsInCart() {
        return boardsInCart;
    }

    public ShoppingCart setBoardsInCart(ArrayList<RentedBoard> boardsInCart) {
        this.boardsInCart = boardsInCart;
        return this;
    }

    public long getPickupDate() {
        return pickupDate;
    }

    public ShoppingCart setPickupDate(long pickupDate) {
        this.pickupDate = pickupDate;
        return this;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public ShoppingCart setReturnDate(long returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public ShoppingCart setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
        return this;
    }

    public void addToCart(RentedBoard boardToCart) {
        for (int i = 0; i < boardsInCart.size(); i++) {
            if (boardsInCart.get(i).isSameKey(boardToCart.getKey())
                    && boardsInCart.get(i).getLength() == boardToCart.getLength()) {
                boardsInCart.get(i).setQuantity(boardsInCart.get(i).getQuantity() + 1);
                return;
            }
        }

        boardToCart.setQuantity(1);
        boardsInCart.add(boardToCart);
    }

    public double calculateTotalPrice() {
        double amount = 0;
        for (int i = 0; i < boardsInCart.size(); i++) {
            amount = amount + boardsInCart.get(i).getPrice();
        }
        return amount;
    }
}
