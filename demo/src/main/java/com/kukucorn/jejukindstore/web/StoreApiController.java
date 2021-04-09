package com.kukucorn.jejukindstore.web;

import com.kukucorn.jejukindstore.service.StoreService;
import com.kukucorn.jejukindstore.web.dto.StoreListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/api/v1/store")
    public List<StoreListResponseDto> findAll() {
        return storeService.findAll();
    }
}
