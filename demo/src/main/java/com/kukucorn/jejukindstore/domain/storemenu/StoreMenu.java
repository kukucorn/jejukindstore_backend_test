package com.kukucorn.jejukindstore.domain.storemenu;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "store_menu")
public class StoreMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String price;

    @ManyToOne
    private Store store;

    @Builder
    public StoreMenu(int id, String name, String price, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }
}
