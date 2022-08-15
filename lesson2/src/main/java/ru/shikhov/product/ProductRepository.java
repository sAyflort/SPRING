package ru.shikhov.product;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository(value = "productRepository")
public class ProductRepository {
    private final Map<Integer, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicInteger identity = new AtomicInteger(0);

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(int id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        int id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    @PostConstruct
    public void init() {
        insert(new Product("Watermelon", 1000000));
        insert(new Product("Apple", 10));
        insert(new Product("Pear", 50));
        insert(new Product("Pepper", 70));
        insert(new Product("Potato", 70));
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }

}
