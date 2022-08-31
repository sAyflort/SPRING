package ru.shikhov.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "buyers")
@NamedQueries({
        @NamedQuery(name = "findAllBuyers", query = "select b from Buyer b"),
        @NamedQuery(name = "deleteBuyersById", query = "delete from Buyer b where b.id = :id")
})
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false, unique = true)
    private String last_name;

    @Column
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name = "Buyers_products",
            joinColumns = @JoinColumn(name = "buyers_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void addAllProducts(List<Product> products) {
        products.addAll(products);
    }

    public Buyer(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
