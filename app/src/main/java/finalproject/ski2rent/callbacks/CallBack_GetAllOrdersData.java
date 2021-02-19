package finalproject.ski2rent.callbacks;

import java.util.ArrayList;

import finalproject.ski2rent.objects.Order;

public interface CallBack_GetAllOrdersData {
    void retrieveAllOrders(ArrayList<Order> orders);
}
