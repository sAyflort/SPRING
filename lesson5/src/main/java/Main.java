import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.shikhov.homework.ProductRepository;
import ru.shikhov.model.Product;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductRepository productRepository = new ProductRepository(entityManager);

        /*entityManager.getTransaction().begin();

        entityManager.persist(new Product("Product1", 100));
        entityManager.persist(new Product("Product2", 100));
        entityManager.persist(new Product("Product3", 100));

        entityManager.getTransaction().commit();*/

        productRepository.updateOrSave(new Product("Product2", 1000));
        productRepository.updateOrSave(new Product("Product6", 5));

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void soutList(List<Product> productList) {
        for (Product p: productList
        ) {
            System.out.println(p);
        }
    }
}
