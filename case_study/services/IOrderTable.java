package case_study.services;

import case_study.model.OrderTables;

import java.util.List;
public interface IOrderTable {

    List<OrderTables> findAll();

    void add(OrderTables newOrder);
//
//    void fix(OrderTable newOrder);
//
//    void delete(OrderTable newOrder);
//
//    void display();
//
//    void displayOrder();

//    OrderTable getOrderById(int id);


}
