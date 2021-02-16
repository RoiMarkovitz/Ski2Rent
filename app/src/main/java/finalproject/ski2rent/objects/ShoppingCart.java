package finalproject.ski2rent.objects;

import java.util.ArrayList;

// TODO maybe make singleton

public class ShoppingCart {

    private ArrayList<RentedBoard> boardsInCart = new ArrayList<>();
    // TODO USER ID

    public ShoppingCart() {}

    public ArrayList<RentedBoard> getBoardsInCart() {
        return boardsInCart;
    }

    public ShoppingCart setBoardsInCart(ArrayList<RentedBoard> boardsInCart) {
        this.boardsInCart = boardsInCart;
        return this;
    }

    public double calculateTotalPrice() {
        double amount = 0;
        for (int i = 0; i < boardsInCart.size(); i++) {
            amount = amount + boardsInCart.get(i).getPrice();
        }
        return amount;
    }
}
