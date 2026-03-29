package cz.vibri.orderservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Product extends BaseEntity{

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", columnDefinition = "VARCHAR(30)")
    private ProductStatus productStatus;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    private Integer quantityOnHand;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(description, product.description) && productStatus == product.productStatus && Objects.equals(categories, product.categories) && Objects.equals(quantityOnHand, product.quantityOnHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, productStatus, quantityOnHand);
    }
}
