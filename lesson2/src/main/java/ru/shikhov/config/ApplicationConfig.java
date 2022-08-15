package ru.shikhov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.shikhov.Cart;
import ru.shikhov.product.ProductRepository;

@Configuration
public class ApplicationConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    @Scope("prototype")
    public Cart cart() {
        return new Cart();
    }
}
