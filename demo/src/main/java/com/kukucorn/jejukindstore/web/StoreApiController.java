package com.kukucorn.jejukindstore.web;

import com.kukucorn.jejukindstore.service.StoreService;
import com.kukucorn.jejukindstore.web.dto.StoreListResponseDto;
import com.kukucorn.jejukindstore.web.dto.StoreResponseDto;
import com.kukucorn.jejukindstore.web.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/api/v1/store/{id}")
    public StoreResponseDto find(@PathVariable Integer id) {
        return storeService.find(id);
    }

    @GetMapping("/api/v1/store")
    public List<StoreListResponseDto> findAll() {
        return storeService.findAll();
    }

    @PostMapping("/api/v1/store")
    public Integer save(@RequestBody StoreSaveRequestDto requestDto) {
        return storeService.save(requestDto);
    }
}
