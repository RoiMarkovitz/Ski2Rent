package finalproject.ski2rent.objects;

import java.util.ArrayList;

public class BoardForRent extends Board {

    private ArrayList<Integer> lengths = new ArrayList<>();

    public BoardForRent() {}

    public ArrayList<Integer> getLengths() {
        return lengths;
    }

    public BoardForRent setLengths(ArrayList<Integer> lengths) {
        this.lengths = lengths;
        return this;
    }


}
