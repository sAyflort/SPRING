package ru.shikhov.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import ru.shikhov.model.Product;

import java.util.List;

public class ProductRepository {
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    public void updateOrSave(Product product) {
        entityManager.getTransaction().begin();
        String query = String.format("select p from Product p where p.title = \"%s\"", product.getTitle());
        try {
            Product p = (Product) entityManager.createQuery(query).getSingleResult();
            product.setId(p.getId());
            entityManager.merge(product);
        } catch (NoResultException e) {
            product.setId(1L);
            entityManager.merge(product);
        }
        entityManager.getTransaction().commit();
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }
}
