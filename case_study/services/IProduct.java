package case_study.services;

import case_study.model.Product;

import java.util.List;

public interface IProduct {
    List<Product> findAll();
    void add(Product newProduct);

    void update(Product newProduct);

    Product findById(int id);

    boolean exist(int id);

    boolean existByName(String name);

    boolean existsById(int id);

    void deleteById(int id);



    List<Product> findAllOrderByPriceASC();

    List<Product> findAllOrderByPriceDESC();
}
