package finalproject.ski2rent.objects;

public class MockPrices {

    public static Prices generatePriceTable() {
        Prices priceTable = new Prices();

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(1)
                .setBronzePrice(13.00)
                .setSilverPrice(27.00)
                .setGoldPrice(39.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(2)
                .setBronzePrice(26.00)
                .setSilverPrice(52.00)
                .setGoldPrice(75.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(3)
                .setBronzePrice(34.00)
                .setSilverPrice(67.00)
                .setGoldPrice(96.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(4)
                .setBronzePrice(45.00)
                .setSilverPrice(91.00)
                .setGoldPrice(129.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(5)
                .setBronzePrice(49.00)
                .setSilverPrice(99.00)
                .setGoldPrice(143.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(6)
                .setBronzePrice(57.00)
                .setSilverPrice(113.00)
                .setGoldPrice(148.00));

        priceTable.getPriceTable().add(new PriceRecord()
                .setDays(0)
                .setBronzePrice(8.00)
                .setSilverPrice(12.00)
                .setGoldPrice(16.00));



        return priceTable;
    }

}


