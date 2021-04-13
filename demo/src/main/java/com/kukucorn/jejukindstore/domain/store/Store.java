package com.kukucorn.jejukindstore.domain.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String location;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 45, nullable = false)
    private String telephone;

    @Column(length = 45, nullable = false)
    private String category;

    @Builder
    public Store(String name, String location, String address, String telephone, String category) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.telephone = telephone;
        this.category = category;
    }
}
