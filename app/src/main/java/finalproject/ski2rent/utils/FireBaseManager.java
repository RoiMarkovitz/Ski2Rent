package finalproject.ski2rent.utils;

import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import finalproject.ski2rent.callbacks.CallBack_AllBoardsForRent;
import finalproject.ski2rent.callbacks.CallBack_AllPriceData;
import finalproject.ski2rent.callbacks.CallBack_GetAllOrdersData;
import finalproject.ski2rent.callbacks.CallBack_GetCustomerData;
import finalproject.ski2rent.callbacks.CallBack_GetLatestOrderIdNumber;
import finalproject.ski2rent.callbacks.CallBack_GetOrderData;
import finalproject.ski2rent.callbacks.CallBack_PriceData;
import finalproject.ski2rent.callbacks.CallBack_BoardForRent;
import finalproject.ski2rent.callbacks.CallBack_GetShoppingCartData;
import finalproject.ski2rent.callbacks.CallBack_UpdateCustomerData;
import finalproject.ski2rent.callbacks.CallBack_UpdateOrderData;
import finalproject.ski2rent.callbacks.CallBack_UpdateShoppingCartData;
import finalproject.ski2rent.objects.Board;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.Customer;
import finalproject.ski2rent.objects.Order;
import finalproject.ski2rent.objects.PriceRecord;
import finalproject.ski2rent.objects.ShoppingCart;

public class FireBaseManager {
    public boolean isShoppingCartReturned = false;
    public ShoppingCart shoppingCart;

    private static FireBaseManager instance;

    public static FireBaseManager getInstance() {
        return instance;
    }

    private FireBaseManager() {}

    public static void init() {
        if (instance == null) {
            instance = new FireBaseManager();
        }
    }

    public boolean isCurrentUserLoggedIn() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getUidCurrentUser() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

       // if ()

        return uid;

    }

    public void updateCustomerUser(Customer customer, CallBack_UpdateCustomerData callback) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String phone = firebaseUser.getPhoneNumber();

        if (customer == null) { // first time login event
            customer = new Customer()
                    .setUid(uid)
                    .setPhone(phone)
                    .setName(firebaseUser.getDisplayName());

            initShoppingCartForFirstTimeCustomerUser(uid);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");
        myRef.child(uid).setValue(customer);

        callback.updated();
    }

    public void readCustomerUserData(String uid, CallBack_GetCustomerData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");

        myRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer c = dataSnapshot.getValue(Customer.class);
                callback.retrieveCustomer(c);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });
    }

    private void initShoppingCartForFirstTimeCustomerUser(String customerKey) {
        ShoppingCart sc = new ShoppingCart();
        sc.setCustomerKey(customerKey);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shopping_carts");
        myRef.child(sc.getCustomerKey()).setValue(sc);

    }

    public void readShoppingCartDataFromServer(String customerKey, CallBack_GetShoppingCartData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shopping_carts");

        myRef.child(customerKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ShoppingCart sc = dataSnapshot.getValue(ShoppingCart.class);
                callback.retrieveShoppingCart(sc);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void updateShoppingCartToServer(ShoppingCart sc, CallBack_UpdateShoppingCartData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shopping_carts");
        myRef.child(sc.getCustomerKey()).setValue(sc);

        shoppingCart = sc;
        callback.updated();
    }

    public void readAllOrdersFromServer(CallBack_GetAllOrdersData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders").child("customers_id");
        ArrayList<Order> orders = new ArrayList<>();
        myRef.child(getUidCurrentUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    orders.add(child.getValue(Order.class));
                }
                callback.retrieveAllOrders(orders);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void readOrderDataFromServer(String customerKey, CallBack_GetOrderData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders").child("customers_id");

        myRef.child(customerKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order o = dataSnapshot.getValue(Order.class);
                callback.retrieveOrder(o);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void readOrderLatestIdNumberFromServer(CallBack_GetLatestOrderIdNumber callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders").child("latest_id");

        myRef.child("latest").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int latestOrderIdNumber = dataSnapshot.getValue(Integer.class);

                callback.retrieveLatestOrderIdNumber(latestOrderIdNumber);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void updateOrderToServer(String customerKey, Order o, CallBack_UpdateOrderData callback) {
        ArrayList<Order> orders = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders").child("customers_id").child(o.getCustomerKey());

        myRef.child(o.getId()+"").setValue(o);

        // update latest order id to server
        myRef = database.getReference("orders").child("latest_id");
        myRef.child("latest").setValue(o.getId());
        // update to clear shopping cart
        ShoppingCart sc = new ShoppingCart();
        sc.setCustomerKey(getUidCurrentUser());
        updateShoppingCartToServer(sc, new CallBack_UpdateShoppingCartData() {
            @Override
            public void updated() {
                callback.updated();
            }
        });

    }

    public void readAllPricesFromServer(CallBack_AllPriceData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prices");
        ArrayList<PriceRecord> priceTable = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    priceTable.add(child.getValue(PriceRecord.class));
                }
                callback.retrieveAllPriceRecord(priceTable);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }


    public void readPriceDataFromServer(String priceKey, CallBack_PriceData callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prices");

        myRef.child(priceKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PriceRecord p = dataSnapshot.getValue(PriceRecord.class);
                callback.retrievePriceRecord(p);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void readAllBoardForRentDataFromServer(Board.eType type, CallBack_AllBoardsForRent callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        if (type == Board.eType.Snowboard) {
            myRef = database.getReference("boards_for_rent").child("snowboards");
        } else {
            myRef = database.getReference("boards_for_rent").child("skis");
        }
        ArrayList<BoardForRent> boards = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    boards.add(child.getValue(BoardForRent.class));
                }
                callback.retrieveAllBoardsForRent(boards);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });

    }

    public void readBoardForRentDataFromServer(String boardKey, Board.eType type, CallBack_BoardForRent callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        if (type == Board.eType.Snowboard) {
            myRef = database.getReference("boards_for_rent").child("snowboards");
        } else {
            myRef = database.getReference("boards_for_rent").child("skis");
        }

        myRef.child(boardKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BoardForRent b = dataSnapshot.getValue(BoardForRent.class);
                callback.retrieveBoardForRent(b);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });
    }

    public void uploadBoardForRentToServer(BoardForRent board, Board.eType type) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        if (type == Board.eType.Snowboard) {
            myRef = database.getReference("boards_for_rent").child("snowboards");
        } else {
            myRef = database.getReference("boards_for_rent").child("skis");
        }
        myRef.child(board.getKey()).setValue(board);
    }

    public void uploadPriceToServer(PriceRecord priceRecord) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prices");
        myRef.child(priceRecord.getKey()).setValue(priceRecord);
    }

    public void loadPriceRecords() {
        HashMap<String, PriceRecord> pricesMap = new HashMap<>();
        pricesMap.put("1", new PriceRecord()
                .setKey("1")
                .setDays(1)
                .setBronzePrice(13.00)
                .setSilverPrice(27.00)
                .setGoldPrice(39.00));

        pricesMap.put("2", new PriceRecord()
                .setKey("2")
                .setDays(2)
                .setBronzePrice(26.00)
                .setSilverPrice(52.00)
                .setGoldPrice(75.00));

        pricesMap.put("3", new PriceRecord()
                .setKey("3")
                .setDays(3)
                .setBronzePrice(34.00)
                .setSilverPrice(67.00)
                .setGoldPrice(96.00));

        pricesMap.put("4", new PriceRecord()
                .setKey("4")
                .setDays(4)
                .setBronzePrice(45.00)
                .setSilverPrice(91.00)
                .setGoldPrice(129.00));

        pricesMap.put("5", new PriceRecord()
                .setKey("5")
                .setDays(5)
                .setBronzePrice(49.00)
                .setSilverPrice(99.00)
                .setGoldPrice(143.00));

        pricesMap.put("6", new PriceRecord()
                .setKey("6")
                .setDays(6)
                .setBronzePrice(57.00)
                .setSilverPrice(113.00)
                .setGoldPrice(148.00));

        pricesMap.put("extraDays", new PriceRecord()
                .setKey("extraDays")
                .setDays(-1)
                .setBronzePrice(8.00)
                .setSilverPrice(12.00)
                .setGoldPrice(16.00));

        for (Map.Entry mapElement : pricesMap.entrySet()) {
        //    String key = (String) mapElement.getKey();
            PriceRecord value = ((PriceRecord) mapElement.getValue());
            uploadPriceToServer(value);
        }

    }

    public void loadSnowboards() {

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

        for (int i = 0 ; i < snowboards.size(); i++) {
            uploadBoardForRentToServer(snowboards.get(i), Board.eType.Snowboard);
        }

    }

    public void loadSkis() {
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

        for (int i = 0 ; i < skis.size(); i++) {
            uploadBoardForRentToServer(skis.get(i), Board.eType.Ski);
        }

    }
}
