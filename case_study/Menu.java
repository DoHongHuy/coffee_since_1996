package case_study;
import java.util.Scanner;
public class Menu {
        public static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  Bill Cafe  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                               ⚪");
        System.out.println("⚪     1. Đăng nhập quản lí       ⚪");
        System.out.println("⚪     0. Thoát                   ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }

    public static void show() {
        int option;
        do {
            menu();
            System.out.println("\nChọn chức năng ");
            System.out.print(" ⭆ ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    UserLogin.run();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }

        } while (option != 0);
    }
}
