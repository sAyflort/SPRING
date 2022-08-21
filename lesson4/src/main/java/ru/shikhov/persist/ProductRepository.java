package ru.shikhov.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        insert(new Product("Watermelon", 1000000l, "08.21.2022", "08.21.2022"));
        insert(new Product("Apple", 10l, "08.21.2022", "08.21.2022"));
        insert(new Product("Pear", 50l, "08.21.2022", "08.21.2202"));
        insert(new Product("Pepper", 70l, "08.21.2022", "08.21.2022"));
        insert(new Product("Potato", 70l, "08.21.2022", "08.21.2022"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
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
