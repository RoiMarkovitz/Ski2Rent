package finalproject.ski2rent.objects;

import java.util.ArrayList;

import finalproject.ski2rent.utils.FireBaseManager;

// TODO maybe make singleton - here it should be probably singelton

public class Prices {

    private static final Double[] DISCOUNTS = {0.0, 5.0, 8.0, 15.0};
    private static final int[] DAYS_BEFORE_DISCOUNT = {7, 14, 28};

    private ArrayList<PriceRecord> priceTable = new ArrayList<>();

    private static Prices instance;

    public static Prices getInstance() {
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new Prices();
        }
    }

    public Prices() {}

    public Prices(ArrayList<PriceRecord> priceTable) {
        setPriceTable(priceTable);
    }

    public ArrayList<PriceRecord> getPriceTable() {
        return priceTable;
    }

    public Prices setPriceTable(ArrayList<PriceRecord> priceTable) {
        this.priceTable = priceTable;
        return this;
    }

    public double calculateDiscount(int daysBeforePickup) {
        if (daysBeforePickup < DAYS_BEFORE_DISCOUNT[0]) {
            return DISCOUNTS[0];
        } else if (daysBeforePickup >= DAYS_BEFORE_DISCOUNT[0] && daysBeforePickup < DAYS_BEFORE_DISCOUNT[1]) {
            return DISCOUNTS[1];
        } else if (daysBeforePickup >= DAYS_BEFORE_DISCOUNT[1] && daysBeforePickup < DAYS_BEFORE_DISCOUNT[2]) {
            return DISCOUNTS[2];
        }  else {
            return DISCOUNTS[3];
            }
    }

    // Last line in priceTable will always be the extra payment per extra day
    public Double calculatePrice(int days, BoardForRent board, int daysBeforePickup) {
        double discountPercentage = calculateDiscount(daysBeforePickup) / 100;

        int maxRentDaysBeforeExtraDays = priceTable.get(priceTable.size() - 2).getDays();
        double price = 0;
        if (days > priceTable.get(maxRentDaysBeforeExtraDays - 1).getDays()) {
            if (board.getLevel().name().equals("Bronze")) {
                price = priceTable.get(maxRentDaysBeforeExtraDays - 1).getBronzePrice() + (days - maxRentDaysBeforeExtraDays) * priceTable.get(maxRentDaysBeforeExtraDays).getBronzePrice();
            } else if (board.getLevel().name().equals("Silver")) {
                price = priceTable.get(maxRentDaysBeforeExtraDays - 1).getSilverPrice() + (days - maxRentDaysBeforeExtraDays) * priceTable.get(maxRentDaysBeforeExtraDays).getSilverPrice();
            } else {
                price = priceTable.get(maxRentDaysBeforeExtraDays - 1).getGoldPrice() + (days - maxRentDaysBeforeExtraDays) * priceTable.get(maxRentDaysBeforeExtraDays).getGoldPrice();
            }
        } else {
            for (int i = 0; i < priceTable.size() - 1; i++) {
                if (days == priceTable.get(i).getDays()) {
                    if (board.getLevel().name().equals("Bronze")) {
                        price = priceTable.get(i).getBronzePrice();
                    } else if (board.getLevel().name().equals("Silver")) {
                        price =  priceTable.get(i).getSilverPrice();
                    } else {
                        price =  priceTable.get(i).getGoldPrice();
                    }
                    break;
                }
            }
        }
        if (discountPercentage != 0.0) {
            price = price - (price * discountPercentage);
        }

        return price;
    }

} // prices
