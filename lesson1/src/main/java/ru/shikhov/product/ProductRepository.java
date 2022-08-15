package ru.shikhov.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }

}
