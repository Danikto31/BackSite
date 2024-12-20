package org.example.backbase.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name="goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private long sellerId;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private int count;

    @Column
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column
    private String categories;

    public Goods(String title, int cost, int count, String description, String categories) {
        this.title = title;
        this.cost = cost;
        this.count = count;
        this.description = description;
        this.categories = categories;
    }

    protected Goods() {

    }

    public Goods setSellerId(long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public long getId() {
        return id;
    }
}
