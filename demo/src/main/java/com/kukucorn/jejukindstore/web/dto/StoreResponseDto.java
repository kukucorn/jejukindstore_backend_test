package com.kukucorn.jejukindstore.web.dto;

import com.kukucorn.jejukindstore.domain.store.Store;
import lombok.Getter;

@Getter
public class StoreResponseDto {

    private Integer id;
    private String name;
    private String location;
    private String address;
    private String telephone;

    public StoreResponseDto(Store entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.location = entity.getLocation();
        this.address = entity.getAddress();
        this.telephone = entity.getTelephone();
    }
}
