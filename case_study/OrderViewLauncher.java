package case_study;


import case_study.model.Order;

import java.util.Scanner;

public class OrderViewLauncher {
    public static void run(){
        orderMenu();
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();
        OrderView orderView = new OrderView();
        System.out.println("\nChọn chức năng");
        System.out.print("⭆ ");
        int choices = Integer.parseInt(scanner.nextLine());
        switch (choices){
            case 1:
                orderView.addOder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            case 3:
                orderView.addTable();;
                break;
            case 4:
                orderView.showOrderTable();
                break;
            case 5:
                orderView.searchTable();
                break;
//            case 5:
//                orderView.searchTable();
//                break;
            case 8:
                return;
            case 0:
                UserLogin.ShowQl();
                break;
            default:
                System.out.println("Chọn sai! vui lòng nhập lại");
                run();

        }
    }
    public static void orderMenu() {
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬     ORDER MENU        ✬ ✬ ✬ ✬  ✬");
        System.out.println("✬                                               ✬");
        System.out.println("✬     1. Tạo order                              ✬");
        System.out.println("✬     2. Xem danh sách order                    ✬");
        System.out.println("✬     3. Order table                            ✬");
        System.out.println("✬     4. Xem danh sách order table              ✬");
        System.out.println("✬     5. Xem sản phẩm với table đã order        ✬");
        System.out.println("✬     0. Quay lại                               ✬");
        System.out.println("✬     8. Thoát chương trình                     ✬");
        System.out.println("✬                                               ✬");
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
    }

    public static void main(String[] args) {
        OrderViewLauncher.run();
    }
}
