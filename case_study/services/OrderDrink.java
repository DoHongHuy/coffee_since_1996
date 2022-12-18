//package case_study.services;
//
//import case_study.Untils.CSVUtils;
//import case_study.aateam.OrderItem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderDrink implements IorderDrink {
//    private final static String PATH = "xx.csv";
//    private static OrderDrink instance;
//
//    private OrderDrink() {
//    }
//
//    public static OrderDrink instance() {
//        if (instance == null) {
//            instance = new OrderDrink();
//
//        }
//        return instance;
//    }
//
//    @Override
//    public List<OrderItem> findAll() {
//        List<OrderItem> OrderIteam = new ArrayList<>();
//        List<String> records = CSVUtils.read(PATH);
//        for (String record : records) {
//            OrderIteam.add(new OrderItem(record));
//        }
//        return OrderIteam;
//    }
//
//    @Override
//    public void add(OrderItem newOrder) {
//        List<OrderItem> orderItems = findAll();
//        orderItems.add(new OrderItem());
//        CSVUtils.write(PATH, orderItems);
//    }
//
//    @Override
//    public void fix(OrderItem newOrder) {
//        List<OrderItem> orderItems = findAll();
//        CSVUtils.write(PATH, orderItems);
//    }
//
//    @Override
//    public void delete(OrderItem newOrder) {
//
//    }
//
//    @Override
//    public void display() {
//
//    }
//
//    @Override
//    public void displayOrder() {
//
//    }
//
//    @Override
//    public OrderItem getOrderById(int id) {
//        List<OrderItem> orderItems = findAll();
//        for (OrderItem orderItem : orderItems) {
//            if (orderItem.getId() == id)
//                return orderItem;
//        }
//        return null;
//    }
//
//
//}