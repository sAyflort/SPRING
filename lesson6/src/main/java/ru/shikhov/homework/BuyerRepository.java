package ru.shikhov.homework;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class BuyerRepository extends Repository{
    public BuyerRepository(EntityManagerFactory emFactory) {
        super(emFactory);
    }

    public Optional<Buyer> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Buyer.class, id)));
    }

    public List<Buyer> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllBuyers", Buyer.class).getResultList());
    }

    public void updateOrSave(Buyer buyer) {
        executeInTranslation(entityManager -> {
            if(buyer.getId() == null) {
                entityManager.persist(buyer);
            } else {
                entityManager.merge(buyer);
            }
        });
    }

    public void deleteById(Long id) {
        executeInTranslation(entityManager ->
                entityManager.createNamedQuery("deleteBuyersById").setParameter("id", id).executeUpdate());
    }

    public List<Product> getProductsById(Long id) {
        return findById(id).get().getProductList();
    }
}
