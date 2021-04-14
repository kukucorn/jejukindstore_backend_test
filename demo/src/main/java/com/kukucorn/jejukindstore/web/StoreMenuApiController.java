package com.kukucorn.jejukindstore.web;

import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import com.kukucorn.jejukindstore.service.StoreMenuService;
import com.kukucorn.jejukindstore.web.dto.StoreMenuListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreMenuApiController {

    private final StoreMenuService storeMenuService;

    @GetMapping("/api/v1/store/{store_id}/menu")
    public List<StoreMenuListResponseDto> findAllMenu(@PathVariable Integer store_id) { return storeMenuService.findStoreMenuListByStoreId(store_id); }
}