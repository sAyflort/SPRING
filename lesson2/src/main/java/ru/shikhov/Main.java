package ru.shikhov;

/*
1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять и удалять товары по id.
3. Написать консольное приложение, позволяющее управлять корзиной.
4. При каждом запросе корзины из контекста, должна создаваться новая корзина.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.shikhov.config.ApplicationConfig;
import ru.shikhov.product.Product;
import ru.shikhov.product.ProductRepository;

import java.util.List;
import java.util.Scanner;

import static ru.shikhov.product.Commands.*;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        Cart cart = context.getBean("cart", Cart.class);

        System.out.println("Для создания новой корзины отправьте \"NEWCART\"");
        System.out.println("Для добавления продукта в корзину напишите \"ADD id\" продукта");
        System.out.println("Для удаления продукта из корзины напишите \"DEL id\" продукта");
        System.out.println("Чтобы показать список продуктов напишите \"SHOWPRODUCT\"");
        System.out.println("Чтобы показать список продуктов вашей корзины напишите \"SHOWCART\"");
        System.out.println("Для выхода напишите \"END\"");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if(command.split(" ").length > 1) {
                String[] msg = command.split(" ");
                try{
                    int id = Integer.parseInt(msg[1]);
                    if (msg[0].equals(ADD.command)) {
                        cart.insert(productRepository.findById(id));
                    }
                    if (msg[0].equals(DELETE.command)) {
                        cart.delete(id);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
            if (command.equals(SHOWCART.command)) {
                showProducts(cart.showProduct());
            }
            if (command.equals(SHOWPRODUCT.command)) {
                showProducts(productRepository.findAll());
            }
            if (command.equals(NEWCART.command)) {
                cart = context.getBean("cart", Cart.class);
            }
            if (command.equals(END.command)) {
                break;
            }
        }
    }

    public static void showProducts(List<Product> products) {
        for (Product product: products
             ) {
            System.out.println(product.getId()+" "+product.getTitle());
        }
    }
}
