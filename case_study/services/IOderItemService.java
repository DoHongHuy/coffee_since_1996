package case_study.services;


import case_study.model.OrderItem;

import java.util.List;

public interface IOderItemService {


    List<OrderItem> findAll();

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem getOrderItemById(int id);


    void update(long orderId, double price, double sum);
}
