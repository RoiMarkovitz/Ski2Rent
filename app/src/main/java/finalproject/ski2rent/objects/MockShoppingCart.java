package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class MockShoppingCart {
    public static ArrayList<RentedBoard> generateSnowboards() {
        ArrayList<RentedBoard> boards = new ArrayList<>();

        RentedBoard b1 = new RentedBoard();
        b1.setKey("B001");
        b1.setType(Board.eType.Snowboard);
        b1.setName("SkateBanana");
        b1.setBrand("Libtech");
        b1.setLevel(Board.eLevel.Bronze);
        b1.setCamberProfile(Board.eCamberProfile.Rocker);
        b1.setImagePath("img_snowboard_skate_banana");
        b1.setLength(152);
        b1.setPrice(42.5);
        b1.setQuantity(2);

        boards.add(b1);

        RentedBoard b2 = new RentedBoard();
        b2.setKey("B002");
        b2.setType(Board.eType.Snowboard);
        b2.setName("MountainTwin");
        b2.setBrand("Jones");
        b2.setLevel(Board.eLevel.Bronze);
        b2.setCamberProfile(Board.eCamberProfile.Flat);
        b2.setImagePath("img_snowboard_mountain_twin");
        b2.setLength(159);
        b2.setPrice(48.5);
        b2.setQuantity(1);

        boards.add(b2);

        RentedBoard b3 = new RentedBoard();
        b3.setKey("B003");
        b3.setType(Board.eType.Snowboard);
        b3.setName("Frontier");
        b3.setBrand("Jones");
        b3.setLevel(Board.eLevel.Silver);
        b3.setCamberProfile(Board.eCamberProfile.HybridCamber);
        b3.setImagePath("img_snowboard_frontier");
        b3.setLength(156);
        b3.setPrice(60.0);
        b3.setQuantity(1);

        boards.add(b3);

        return boards;
    }
}
