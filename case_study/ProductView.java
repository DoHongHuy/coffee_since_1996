package case_study;


import case_study.services.IProduct;
import case_study.Untils.AppUtils;
import case_study.Untils.InstantUtils;
import case_study.model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductView {//Single Responsibility Principle (SOLID)
    private final IProduct productService; //Dependency Inversion Principle (SOLID)
    private final Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public static void main(String[] args) {
        ProductView productView = new ProductView();
        productView.add();
    }
    public void add() {
        do {
//            long id = inputId(InputOption.ADD);
            long id = System.currentTimeMillis() / 1000;
            String name = inputName(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            long table = inputTable(InputOption.ADD);
            String description = inputDescription();
            Product product = new Product( id, name, price, quantity,table, description);
            productService.add(product);
            System.out.println("Thêm sản phẩm thành công \n");
        } while (AppUtils.isRetry(InputOption.ADD));
    }
    public void update() {
        boolean isRetry;
        do {
            showProducts(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("************************       MENU   ***************************************");
            System.out.println("                     1.Sửa id                                                ");
            System.out.println("                     2.Sửa tên                                               ");
            System.out.println("                     3.Sửa giá sản phẩm                                      ");
            System.out.println("                     4.Sửa số lượng                                          ");
            System.out.println("                     5.Quay lại MENU                                         ");
            System.out.println("                                                                             ");
            System.out.println("*****************************************************************************");
            System.out.println("Chọn chức năng: ");
            int option = AppUtils.retryChoose(1, 4);
            case_study.model.Product newProduct = new case_study.model.Product();
            newProduct.setId(id);
            switch (option) {
                case 1:
                    int ID = inputId(InputOption.UPDATE);
                    newProduct.setId(id);
                    productService.update(newProduct);
                    System.out.println("Id cập nhập thành công");
                    ProductView.Show();
                    break;
                case 2:
                    String name = inputName(InputOption.UPDATE);
                    newProduct.setName(name);
                    productService.update(newProduct);
                    System.out.println("Tên sản phẩm cập nhập thành công");
                    ProductView.Show();
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Bạn đã sửa giá  thành công");
                    ProductView.Show();
                    break;
                case 4:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Số lượng  đã cập nhật thành công");
                    ProductView.Show();
                    break;
                case 5:
                    ProductView.Show();
                    break;
            }
            isRetry = option != 6 && AppUtils.isRetry(InputOption.UPDATE);
        }
        while (isRetry);
    }


    //Tái sử dụng khi sort tránh đổi thứ tự list gốc
    public void showProducts(InputOption inputOption) {
        System.out.println("-----------------------------------------DANH SÁCH NƯỚC-------------------------------------------");
        System.out.printf("%-8s %-30s %-25s %-10s %-25s %-20s\n", "Id", "Tên nước", "Giá bán", "Số lượng", "Ngày tạo", "Ngày cập nhật");
        for (case_study.model.Product product : productService.findAll()) {
            System.out.printf("%-8s %-30s %-25s %-10s %-25s %-20s\n",
                    product.getId(),
                    product.getName(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }
//    public void showProducts(InputOption inputOption) {
//        System.out.println("-----------------------------------------DANH SÁCH NƯỚC-------------------------------------------");
//        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Tên nước", "Giá bán", "Số lượng", "Ngày tạo", "Ngày cập nhật", "Mô tả");
//        for (Product product : productService.findAll()) {
//            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
//                    product.getId(),
//                    product.getName(),
//                    AppUtils.doubleToVND(product.getPrice()),
//                    product.getQuantity(),
//                    InstantUtils.instantToString(product.getCreatedAt()),
//                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
//                    product.getTable());
//        }
//        System.out.println("--------------------------------------------------------------------------------------------------\n");
//        if (inputOption == InputOption.SHOW)
//            AppUtils.isRetry(InputOption.SHOW);
//    }

    public void remove() {
        showProducts(InputOption.DELETE);
        int id;
        while (!productService.exist(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        }

        System.out.println("❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("❄  1. Nhấn 1 để xoá        ❄");
        System.out.println("❄  2. Nhấn 2 để quay lại   ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄  ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("Đã xoá sản phẩm thành công! \uD83C\uDF8A");
            AppUtils.isRetry(InputOption.DELETE);
        }

    }

    private String inputName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên bạn muốn sửa: ");
                break;
        }
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    private String inputDescription() {
        System.out.println("Mô tả sản phẩm: ");
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id");
                break;
            case UPDATE:
                System.out.println("Nhập id bạn muốn sửa");
                break;
            case DELETE:
                System.out.println("Nhập id bạn cần xoá: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại. Vui lòng nhập id khác!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy id! Vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
        } while (quantity <= 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <= 0)
                System.out.println("Giá phải lớn hơn 0 (giá > 0)");
        } while (price <= 0);
        return price;
    }

    private int inputTable(InputOption option) {
        int table;
        switch (option) {
            case ADD:
                System.out.println("Nhập Tên bàn");
                break;
            case UPDATE:
                System.out.println("Nhập tên bàn bạn muốn sửa");
                break;
            case DELETE:
                System.out.println("Nhập tên bàn cần xoá: ");
                break;
        }
        do {
            table = AppUtils.retryParseInt();
            if(table <= 0 ){
                System.out.println("Nhập sai");
            }
        }while (table < 0);
        return table;
        }
    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Tên bánh", "Giá Sản phẩm", "Số lượng", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (case_study.model.Product product : products) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
                    product.getId(),
                    product.getName(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getTable()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void sortByPriceOrderByASC(InputOption show) {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }

    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceDESC());
    }
    public static void menu(){
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪              Bill Cafe                  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                                           ⚪");
        System.out.println("⚪     1. Thêm nước uống                                     ⚪");
        System.out.println("⚪     2. Sửa sản phẩm nước uống                             ⚪");
        System.out.println("⚪     3. Xoá sản phẩm nc uống                               ⚪");
        System.out.println("⚪     4. Hiển thị danh sách sản phẩm                        ⚪");
        System.out.println("⚪     5. Hiển thị dánh sánh theo giá sản phẩm tăng đân      ⚪");
        System.out.println("⚪     6. Hiển thị dánh sánh theo giá sản phẩm giảm đân      ⚪");
        System.out.println("⚪     8. Quay lại                                           ⚪");
        System.out.println("⚪     0. Thoát chương trình                                 ⚪");
        System.out.println("⚪                                                           ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }

    public static void Show() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            menu();
            System.out.println("\nChọn chức năng ");
            System.out.print(" ⭆ ");
            option = Integer.parseInt(scanner.nextLine());
            ProductView productView = new ProductView();
            switch (option) {
                case 1:
                    productView.add();
                    break;
                case 2:
                    productView.update();
                    break;
                case 3:
                    productView.remove();
                    break;
                case 4:
                    productView.showProducts(InputOption.SHOW);
                    break;
                case 5:
                    productView.sortByPriceOrderByASC(InputOption.SHOW);
                    break;
                case 8:
                    UserLogin.ShowQl();
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
