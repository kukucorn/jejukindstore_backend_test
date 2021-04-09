package com.kukucorn.jejukindstore.service;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.store.StoreRepository;
import com.kukucorn.jejukindstore.web.dto.StoreListResponseDto;
import com.kukucorn.jejukindstore.web.dto.StoreResponseDto;
import com.kukucorn.jejukindstore.web.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public List<StoreListResponseDto> findAll() {
        return storeRepository.findAll().stream().map(StoreListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Integer save(StoreSaveRequestDto requestDto) {
        Store store = storeRepository.save(requestDto.toEntity());
        return store.getId();
    }
}