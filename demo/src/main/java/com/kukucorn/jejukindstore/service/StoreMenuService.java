package com.kukucorn.jejukindstore.service;

import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenuRepository;
import com.kukucorn.jejukindstore.web.dto.StoreMenuListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public List<StoreMenuListResponseDto> findStoreMenuListByStoreId(Integer store_id) {
        return storeMenuRepository.findAllByStoreId(store_id).stream().map(StoreMenuListResponseDto::new).collect(Collectors.toList());
    }
}
