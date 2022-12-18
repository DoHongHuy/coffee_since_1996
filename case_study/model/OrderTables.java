package case_study.model;

public class OrderTables {
    private String table;
    private Long idTable;
    public OrderTables(){

    }
    public  OrderTables(long Id, String table, OrderItem orderItem){
           this.idTable = Id ;
           this.table = table;

    }
    public  OrderTables(long orderId, String table){
this.idTable = orderId;
this.table = table;
    }
    public  OrderTables(String table){
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public static OrderTables parses(String records){
        String[] fields = records.split(",");
        OrderTables orders = new OrderTables();
        orders.idTable = Long.parseLong(fields[0]);
        orders.table = fields[1];
        return orders;

    }

    @Override
    public String toString() {
        return
                 idTable + ","
              + table

                ;
    }
}
