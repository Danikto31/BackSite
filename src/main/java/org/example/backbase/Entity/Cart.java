package org.example.backbase.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @EmbeddedId
    CartId id;

    @Column(nullable = false)
    int quantity;
}

@Embeddable
class CartId {
    long user_id;
    long good_id;
}
