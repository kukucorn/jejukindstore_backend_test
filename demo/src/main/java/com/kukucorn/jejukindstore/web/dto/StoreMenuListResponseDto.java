package com.kukucorn.jejukindstore.web.dto;

import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import lombok.Getter;

@Getter
public class StoreMenuListResponseDto {

    private Integer id;
    private String name;
    private String price;
    private Integer store_id;

    public StoreMenuListResponseDto(StoreMenu entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.store_id = entity.getStore().getId();
    }
}
