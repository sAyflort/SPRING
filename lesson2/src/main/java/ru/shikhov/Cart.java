package ru.shikhov;

import org.springframework.stereotype.Component;
import ru.shikhov.product.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> content = new ArrayList<>();

    public void insert(Product product) {
        content.add(product);
    }

    public void delete(int id) {
        for (int i = 0; i < content.size(); i++) {
            if(content.get(i).getId() == id) {
                content.remove(content.get(i));
                break;
            }
        }
    }

    public List<Product> showProduct() {
        return content;
    }
}
