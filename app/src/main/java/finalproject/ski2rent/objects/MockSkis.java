package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class MockSkis {
    public static ArrayList<BoardForRent> generateSkis() {
        ArrayList<BoardForRent> skis = new ArrayList<>();

        BoardForRent s1 = new BoardForRent();
        s1.setKey("S001");
        s1.setType(Board.eType.Ski);
        s1.setName("Punx");
        s1.setBrand("Atomic");
        s1.setLevel(Board.eLevel.Bronze);
        s1.setCamberProfile(Board.eCamberProfile.Rocker);
        s1.setImagePath("img_ski_punx");
        s1.getLengths().add(164);
        s1.getLengths().add(170);
        s1.getLengths().add(176);
        s1.getLengths().add(182);

        skis.add(s1);

        BoardForRent s2 = new BoardForRent();
        s2.setKey("S002");
        s2.setType(Board.eType.Ski);
        s2.setName("Poacher");
        s2.setBrand("K2");
        s2.setLevel(Board.eLevel.Bronze);
        s2.setCamberProfile(Board.eCamberProfile.Flat);
        s2.setImagePath("img_ski_poacher");
        s2.getLengths().add(163);
        s2.getLengths().add(170);
        s2.getLengths().add(177);
        s2.getLengths().add(184);

        skis.add(s2);

        BoardForRent s3 = new BoardForRent();
        s3.setKey("S003");
        s3.setType(Board.eType.Ski);
        s3.setName("Sprayer");
        s3.setBrand("Rossignol");
        s3.setLevel(Board.eLevel.Silver);
        s3.setCamberProfile(Board.eCamberProfile.HybridRocker);
        s3.setImagePath("img_ski_sprayer");
        s3.getLengths().add(138);
        s3.getLengths().add(148);
        s3.getLengths().add(158);
        s3.getLengths().add(168);
        s3.getLengths().add(178);

        skis.add(s3);

        BoardForRent s4 = new BoardForRent();
        s4.setKey("S004");
        s4.setType(Board.eType.Ski);
        s4.setName("Blackops");
        s4.setBrand("Rossignol");
        s4.setLevel(Board.eLevel.Silver);
        s4.setCamberProfile(Board.eCamberProfile.HybridCamber);
        s4.setImagePath("img_ski_blackops");
        s4.getLengths().add(164);
        s4.getLengths().add(178);

        skis.add(s4);

        BoardForRent s5 = new BoardForRent();
        s5.setKey("S005");
        s5.setType(Board.eType.Ski);
        s5.setName("Revolt");
        s5.setBrand("Volkl");
        s5.setLevel(Board.eLevel.Gold);
        s5.setCamberProfile(Board.eCamberProfile.Flat);
        s5.setImagePath("img_ski_revolt");
        s5.getLengths().add(157);
        s5.getLengths().add(165);
        s5.getLengths().add(173);
        s5.getLengths().add(181);

        skis.add(s5);

        BoardForRent s6 = new BoardForRent();
        s6.setKey("S006");
        s6.setType(Board.eType.Ski);
        s6.setName("Flames");
        s6.setBrand("Volkl");
        s6.setLevel(Board.eLevel.Gold);
        s6.setCamberProfile(Board.eCamberProfile.Camber);
        s6.setImagePath("img_ski_flames");
        s6.getLengths().add(155);
        s6.getLengths().add(168);
        s6.getLengths().add(187);

        skis.add(s6);

        return skis;
    }
}
