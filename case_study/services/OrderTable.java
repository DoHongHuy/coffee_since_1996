package case_study.services;

import case_study.Untils.CSVUtils;
import case_study.model.OrderTables;

import java.util.ArrayList;
import java.util.List;

public class OrderTable implements IOrderTable {
        public static final String HUY = "data/orderTable.csv";

        //Singleton Design Pattern
        private static OrderTable instance;
        private OrderTable() {
        }
        public static OrderTable getInstance() {
            if (instance == null)
                instance = new OrderTable();
            return instance;
        }
    public  List<OrderTables> findAll() {
        List<OrderTables> order = new ArrayList<>();
        List<String> records = CSVUtils.read(HUY);
        for (String record : records) {
            order.add(OrderTables.parses(record));
        }
        return order;
    }

    public  void add(OrderTables newOrder) {
        List<OrderTables> orders = findAll();
        orders.add(newOrder);
        CSVUtils.write(HUY, orders);
    }
}
