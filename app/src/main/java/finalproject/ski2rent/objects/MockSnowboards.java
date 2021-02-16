package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class MockSnowboards {
    public static ArrayList<BoardForRent> generateSnowboards() {
        ArrayList<BoardForRent> snowboards = new ArrayList<>();

        BoardForRent b1 = new BoardForRent();
        b1.setKey("B001");
        b1.setType(Board.eType.Snowboard);
        b1.setName("SkateBanana");
        b1.setBrand("Libtech");
        b1.setLevel(Board.eLevel.Bronze);
        b1.setCamberProfile(Board.eCamberProfile.Rocker);
        b1.setImagePath("img_snowboard_skate_banana");
        b1.getLengths().add(152);
        b1.getLengths().add(154);
        b1.getLengths().add(156);
        b1.getLengths().add(159);

        snowboards.add(b1);

        BoardForRent b2 = new BoardForRent();
        b2.setKey("B002");
        b2.setType(Board.eType.Snowboard);
        b2.setName("MountainTwin");
        b2.setBrand("Jones");
        b2.setLevel(Board.eLevel.Bronze);
        b2.setCamberProfile(Board.eCamberProfile.Flat);
        b2.setImagePath("img_snowboard_mountain_twin");
        b2.getLengths().add(151);
        b2.getLengths().add(154);
        b2.getLengths().add(157);
        b2.getLengths().add(160);

        snowboards.add(b2);

        BoardForRent b3 = new BoardForRent();
        b3.setKey("B003");
        b3.setType(Board.eType.Snowboard);
        b3.setName("Frontier");
        b3.setBrand("Jones");
        b3.setLevel(Board.eLevel.Silver);
        b3.setCamberProfile(Board.eCamberProfile.HybridCamber);
        b3.setImagePath("img_snowboard_frontier");
        b3.getLengths().add(152);
        b3.getLengths().add(156);
        b3.getLengths().add(159);
        b3.getLengths().add(162);

        snowboards.add(b3);

        BoardForRent b4 = new BoardForRent();
        b4.setKey("B004");
        b4.setType(Board.eType.Snowboard);
        b4.setName("TerrainWrecker");
        b4.setBrand("Libtech");
        b4.setLevel(Board.eLevel.Silver);
        b4.setCamberProfile(Board.eCamberProfile.HybridRocker);
        b4.setImagePath("img_snowboard_terrain_wrecker");
        b4.getLengths().add(154);
        b4.getLengths().add(157);
        b4.getLengths().add(160);

        snowboards.add(b4);

        BoardForRent b5 = new BoardForRent();
        b5.setKey("B005");
        b5.setType(Board.eType.Snowboard);
        b5.setName("Orca");
        b5.setBrand("Libtech");
        b5.setLevel(Board.eLevel.Gold);
        b5.setCamberProfile(Board.eCamberProfile.Camber);
        b5.setImagePath("img_snowboard_orca");
        b5.getLengths().add(147);
        b5.getLengths().add(150);
        b5.getLengths().add(153);
        b5.getLengths().add(156);
        b5.getLengths().add(159);
        b5.getLengths().add(162);

        snowboards.add(b5);

        BoardForRent b6 = new BoardForRent();
        b6.setKey("B006");
        b6.setType(Board.eType.Snowboard);
        b6.setName("FlagShip");
        b6.setBrand("Jones");
        b6.setLevel(Board.eLevel.Gold);
        b6.setCamberProfile(Board.eCamberProfile.Camber);
        b6.setImagePath("img_snowboard_flagship");
        b6.getLengths().add(151);
        b6.getLengths().add(154);
        b6.getLengths().add(158);
        b6.getLengths().add(161);
        b6.getLengths().add(164);

        snowboards.add(b6);

        return snowboards;
    }
}
