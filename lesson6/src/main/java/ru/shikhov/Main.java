/*
 1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя) и товарах (id, название, стоимость).
У каждого покупателя свой набор купленных товаров;
 2. Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;
 3. * Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать список покупателей этого товара;
 4.** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.
ВАЖНО И ОБЯЗАТЕЛЬНО! Dao классы и сервис должны являться Spring бинами (Вам нужен Spring Context без веб части).
Контроллеры создавать не надо.
ВАЖНО! *Выкидываете *код по подготовке данных и таблиц, и делаете отдельный скрипт и формируете базу заранее.
Покупателей и товары в базу складываете заранее, через код этого делать не надо (лишнее усложнение). SQL-скрипт прикрепите к работе.
 */

package ru.shikhov;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.shikhov.homework.Buyer;
import ru.shikhov.homework.BuyerRepository;
import ru.shikhov.homework.Product;
import ru.shikhov.homework.ProductRepository;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        BuyerRepository buyerRepository = new BuyerRepository(entityManagerFactory);
        ProductRepository productRepository = new ProductRepository(entityManagerFactory);

        Product product1 = new Product("watermelon", 10000);
        Product product2 = new Product("apple", 50);
        Buyer buyer = new Buyer("Name", "Last Name");
        buyer.addProduct(product1);
        buyer.addProduct(product2);

        buyerRepository.updateOrSave(buyer);
    }
}
