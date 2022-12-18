package case_study;



import java.util.Scanner;

public class UserLogin {
    static Scanner scanner = new Scanner(System.in);
    public static void ShowQl() {
        int option;
        do {
            Menu_Ql();
            System.out.println("\nChọn chức năng ");
            System.out.print(" ⭆ ");
            option = Integer.parseInt(scanner.nextLine());
            ProductView productView = new ProductView();
            switch (option) {
                case 1:
                  ProductView.Show();
                    break;
                case 2:
                    OrderViewLauncher.run();
                    break;
                case 3:
                    break;
                case 8:
                    Menu.show();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }

        } while (option != 9);
    }
    public static void run() {
        String usename;
        String password;
        System.out.println("Tên tài khoản ≧◠◡◠≦✌ " );
        System.out.println("==>");
        usename = scanner.nextLine();
        System.out.println("Mật khẩu");
        System.out.println("==>");
        password = scanner.nextLine();

        if (usename.equals("huy") && password.equals("123")) {
            System.out.println(" Đăng nhập thành công ☕☕☕ ☕\uD83C\uDF69\uD83C\uDF69☕\uD83C\uDF69\uD83C\uDF69☕\uD83C\uDF69\uD83C\uDF69:)))\n \n");
            UserLogin.ShowQl();
        } else
            System.out.println("Sai tên tài khoản hoặc mật khẩu");
            System.out.println("Vui lòng nhập lại!! \n \n");
        UserLogin.run();

    }

    public static void Menu_Ql() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪          Bill Cafe        ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                             ⚪");
        System.out.println("⚪     1. Quản lí thức uống và đồ ăn vặt       ⚪");
        System.out.println("⚪     2. Quản lí bán hàng                     ⚪");
        System.out.println("⚪     8. Đăng xuất                            ⚪");
        System.out.println("⚪     0. Thoát chương trình                   ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }



    public static void main(String[] args) {
        UserLogin.ShowQl();
    }

}
