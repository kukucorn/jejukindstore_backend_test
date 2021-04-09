package com.kukucorn.jejukindstore.web.dto;

import com.kukucorn.jejukindstore.domain.store.Store;
import lombok.Getter;

@Getter
public class StoreSaveRequestDto {

    private String name;
    private String location;
    private String address;
    private String telephone;

    public StoreSaveRequestDto(String name, String location, String address, String telephone) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.telephone = telephone;
    }

    public Store toEntity() {
        return Store.builder()
                .name(name)
                .location(location)
                .address(address)
                .telephone(telephone)
                .build();
    }
}
