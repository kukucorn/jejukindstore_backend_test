package com.kukucorn.jejukindstore.domain.storemenu;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "store_menu")
@Entity(name = "store_menu")
public class StoreMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String price;

    @ManyToOne
    private Store store;

    @Builder
    public StoreMenu(String name, String price, Store store) {
        this.name = name;
        this.price = price;
        this.store = store;
    }

    @Override
    public String toString() {
        return String.format("\n name : %s    price : %s", name, price);
    }
}
