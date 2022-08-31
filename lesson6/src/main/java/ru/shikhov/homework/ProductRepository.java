package ru.shikhov.homework;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class ProductRepository extends Repository{


    public ProductRepository(EntityManagerFactory emFactory) {
        super(emFactory);
    }

    public Optional<Product> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Product.class, id)));
    }

    public List<Product> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAll", Product.class).getResultList());
    }

    public void updateOrSave(Product product) {
        executeInTranslation(entityManager -> {
            if(product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    public void deleteById(Long id) {
        executeInTranslation(entityManager ->
                entityManager.createNamedQuery("deleteById").setParameter("id", id).executeUpdate());
    }

    public List<Buyer> getProductsById(Long id) {
        return findById(id).get().getBuyerList();
    }
}
