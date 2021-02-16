package finalproject.ski2rent.objects;

public class PriceRecord {

    private int days = 0;
    private double bronzePrice = 0.0;
    private double silverPrice = 0.0;
    private double goldPrice = 0.0;

    public PriceRecord() {}

    public PriceRecord(int days, double bronzePrice, double silverPrice, double goldPrice) {
        setDays(days);
        setBronzePrice(bronzePrice);
        setSilverPrice(silverPrice);
        setGoldPrice(goldPrice);
    }

    public int getDays() {
        return days;
    }

    public PriceRecord setDays(int days) {
        this.days = days;
        return this;
    }

    public double getBronzePrice() {
        return bronzePrice;
    }

    public PriceRecord setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
        return this;
    }

    public double getSilverPrice() {
        return silverPrice;
    }

    public PriceRecord setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
        return this;
    }

    public double getGoldPrice() {
        return goldPrice;
    }

    public PriceRecord setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
        return this;
    }

} // PriceRecord
