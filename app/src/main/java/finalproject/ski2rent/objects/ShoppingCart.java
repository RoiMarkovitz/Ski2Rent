package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<RentedBoard> boardsInCart = new ArrayList<>();
    // TODO CUSTOMER ID

    public ShoppingCart() {}

    public ArrayList<RentedBoard> getBoardsInCart() {
        return boardsInCart;
    }

    public ShoppingCart setBoardsInCart(ArrayList<RentedBoard> boardsInCart) {
        this.boardsInCart = boardsInCart;
        return this;
    }


    // TODO probably not needed because of firebase
    public void addToCart(RentedBoard boardToCart) {
        for (int i = 0; i < boardsInCart.size(); i++) {
            if (boardsInCart.get(i).isSameKey(boardToCart.getKey())) {
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
