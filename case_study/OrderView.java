package case_study;

import case_study.Untils.AppUtils;
import case_study.Untils.ValidateUtils;
import case_study.model.Order;
import case_study.model.OrderItem;
import case_study.model.OrderTables;
import case_study.model.Product;
import case_study.services.*;

import java.util.List;
import java.util.Scanner;


public class OrderView {//Single Responsibility Principle (SOLID)

    private final IProduct productService;//Dependency Inversion Principle (SOLID)


    private final IOrderService orderService; //Dependency Inversion Principle (SOLID)


    private final IOderItemService oderItemService; //Dependency Inversion Principle (SOLID)
    private final IOrderTable oderTableService; //Dependency Inversion Principle (SOLID)
    private final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        oderItemService = OrderItemService.getInstance();
        oderTableService = OrderTable.getInstance();
    }

    public static void main(String[] args) {
        OrderView orderView = new OrderView();
//        orderView.addTable();
//        orderView.showOrderTable();
//        orderView.deleteTable();
        orderView.searchTable();
    }

    public OrderItem addOrderItems(long orderId) {
        oderItemService.findAll();
        ProductView productView = new ProductView();
        productView.showProducts(InputOption.ADD);
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập id sản phẩm: ");
        System.out.print(" ⭆ ");
        int Id = scanner.nextInt();
        scanner.nextLine();
        while (!productService.existsById(Id)) {
            System.out.println("Id sản phẩm không tồn tại");
            System.out.println("Nhập id sản phẩm: ");
            System.out.print(" ⭆ ");
            Id = scanner.nextInt();
        }
        Product product = productService.findById(Id);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng");
        System.out.print(" ⭆ ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        while (!checkQualityBakery(product, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print(" ⭆ ");
            quantity = scanner.nextInt();
        }
        String Name = product.getName();
//        String Name = product.getTitle();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        //bakeryService.update();
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, Id, Name, total);
        return orderItem;
    }

    public boolean checkQualityBakery(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }


    public void addTable() {
        try {
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập số bàn");
            System.out.print(" ⭆ ");
            String table = scanner.nextLine();
            while (!ValidateUtils.isTableValid(table)) {
                System.out.println("Tên bàn " + table + " không đúng." + " Vui lòng nhập lại!" + " (Chỉ có dưới 100 bàn)");
                System.out.println("Chỉ có 50 bàn Từ 1 ==> 50");
                System.out.println("Vui lòng nhập lại");
                System.out.print(" ⭆ ");
                table = scanner.nextLine();
            }
            OrderItem orderItem = addOrderItems(orderId);
            OrderTables orderTables = new OrderTables(orderId, table, orderItem);
            oderItemService.add(orderItem);
            oderTableService.add(orderTables);
            System.out.println("Tạo đơn hàng thành công");

            do {
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
                System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
//                System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
                System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addTable();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
//                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai. vui lòng nhập lại!");
        }
    }

    public void addOder() {
        try {
            orderService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên () ");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Nguyen Thien) ");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điên thoại");
            System.out.print(" ⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0389748374)");
                System.out.print(" ⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print(" ⭆ ");
            String address = scanner.nextLine();
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
                System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
                System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
                System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai. vui lòng nhập lại!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "   Id", "Tên ", "   SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "Giá");
            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getFullName(), order.getMobile(),
                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println(" ------------------------------------------------------------------------------------------------- Tổng tiền:" + order.getGrandTotal());
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = oderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------DANH SÁCH ORDER--------------------------------------------------------------------");
            System.out.println("                                                                                                                                          ");
            System.out.printf("%-15s %-20s %-12s %-23s %-10s %-10s %-15s %-21s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "   Giá", "   Tổng" + "               ");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-12s %-23s %-10s %-10d %-15f %-21f %-7s\n|",
                        order.getId(),
                        order.getFullName(),
                        order.getMobile(),
                        order.getAddress(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        newOrderItem.getPrice(),
                        newOrderItem.getPrice() * newOrderItem.getQuantity(),
                        "|");
            }
            System.out.println("                                                                                                                                      |");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showOrderTable() {
        List<OrderTables> orders = oderTableService.findAll();
        List<OrderItem> orderItems = oderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------DANH SÁCH ORDER TABLE--------------------------------------------------------------------");
            System.out.println("                                                                                                                                          ");
            System.out.printf("%-15s %-15s %-20s %-20s %-15s %-21s\n", "   Id", "Số bàn", "Tên sản phẩm", "Số lượng", "   Giá", "   Tổng" + "               ");
//            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (OrderTables order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getIdTable()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-15s %-20s %-20d %-15f %-21f \n",
                        order.getIdTable(),
                        order.getTable(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        newOrderItem.getPrice(),
                        newOrderItem.getPrice() * newOrderItem.getQuantity(),
                        "  ");
            }
            System.out.println("                                                                                                                                      ");
            System.out.println("                                                                                                                                      ");
            System.out.println("                                                                                                                                      ");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void deleteTable() {
        System.out.println("Nhập tên bàn");
        String searTable = scanner.nextLine();
        if (!ValidateUtils.isTableValid(searTable)) {
            System.out.println("Tên bàn " + searTable + " không đúng." + " Vui lòng nhập lại!" + " (Chỉ có dưới 100 bàn)");
            System.out.println("Chỉ có 50 bàn Từ 1 ==> 50");
            System.out.println("vui long nhập lại");
            System.out.print(" ⭆ ");
            deleteTable();
        } else {
            List<OrderTables> orders = oderTableService.findAll();
            List<OrderItem> orderItems = oderItemService.findAll();
            OrderItem newOrderItem = new OrderItem();
//        try {
            for (OrderTables order : orders) {
                if (order.getTable().toLowerCase().contains(searTable)) {
                    for (OrderItem orderItem : orderItems) {
                        if (orderItem.getOrderId() == order.getIdTable()) {
                            newOrderItem = orderItem;
                            break;
                        }
                    }
                    boolean b = order.getIdTable() == null;
                    boolean b1 = order.getTable() == null;

                    ;
                }
            }


        }
    }


    public void searchTable() {
        System.out.println("Nhập tên bàn");
        String searTable = scanner.nextLine();
        if (!ValidateUtils.isTableValid(searTable)) {
            System.out.println("Tên bàn " + searTable + " không đúng." + " Vui lòng nhập lại!" + " (Chỉ có dưới 100 bàn)");
            System.out.println("Chỉ có 50 bàn Từ 1 ==> 50");
            System.out.println("vui long nhập lại");
            System.out.print(" ⭆ ");
            searchTable();
        } else {
//             Long searTable = Long.parseLong(scanner.nextLine());
            List<OrderTables> orders = oderTableService.findAll();
            List<OrderItem> orderItems = oderItemService.findAll();
            OrderItem newOrderItem = new OrderItem();
            try {
                System.out.println("-                                         DANH SÁCH ORDER TABLE                                                                                -");
//                System.out.println("                                                                                                                                          ");
                System.out.printf("%-15s %-15s %-20s %-20s %-15s %-21s\n", "   Id", "Số bàn", "Tên sản phẩm", "Số lượng", "   Giá", "   Tổng" + "               ");
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (OrderTables order : orders) {
                    if (order.getTable().toLowerCase().contains(searTable)) {
                        for (OrderItem orderItem : orderItems) {
                            if (orderItem.getOrderId() == order.getIdTable()) {
                                newOrderItem = orderItem;
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                        }
                        System.out.printf("%-15d %-15s %-20s %-20d %-15f %-21f \n",
                                order.getIdTable(),
                                order.getTable(),
                                newOrderItem.getProductName(),
                                newOrderItem.getQuantity(),
                                newOrderItem.getPrice(),
                                newOrderItem.getPrice() * newOrderItem.getQuantity(),
                                "   ***********************************************************************                        ");

                    }
                }
                System.out.println();
                System.out.println();
                System.out.println();
                boolean is = true;
                do {
                    System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                    System.out.print(" ⭆ ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "q":
                            OrderViewLauncher.run();
                            break;
                        case "t":
                            AppUtils.exit();
                            break;
                        default:
                            System.out.println("Nhấn không đúng! vui lòng chọn lại");
                            is = false;
                    }
                } while (!is);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
