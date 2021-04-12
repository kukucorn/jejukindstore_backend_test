package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JejuKindStore {

    private String name;
    private String location;
    private String address;
    private String telephone;
    private String ceo;
    private String category;
    private String goods_price;

    @Builder
    public JejuKindStore(String name, String location, String address, String telephone, String ceo, String category, String goods_price) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.telephone = telephone;
        this.ceo = ceo;
        this.category = category;
        this.goods_price = goods_price;
    }

    public Store toStore() {
        return Store.builder()
                .name(name)
                .location(location)
                .address(address)
                .telephone(telephone)
                .build();
    }
}
