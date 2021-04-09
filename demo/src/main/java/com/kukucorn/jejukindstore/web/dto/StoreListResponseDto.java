package com.kukucorn.jejukindstore.web.dto;

import com.kukucorn.jejukindstore.domain.store.Store;
import lombok.Getter;

@Getter
public class StoreListResponseDto {

    private Integer id;
    private String name;
    private String location;
    private String address;

    public StoreListResponseDto(Store entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.location = entity.getLocation();
        this.address = entity.getAddress();
    }
}
