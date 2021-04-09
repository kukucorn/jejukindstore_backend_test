package com.kukucorn.jejukindstore.domain.store;

import com.kukucorn.jejukindstore.web.dto.StoreSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    public void 가계정보_저장_및_조회하기() {
        // given
        String name = "짜글이";
        String location = "구미";
        String address = "구미시 산호대로 24길 9-13 성은 B 옆집";
        String telephone = "010-3333-2222";

        StoreSaveRequestDto dto = new StoreSaveRequestDto(name, location, address, telephone);

        // when
        Store store = storeRepository.save(dto.toEntity());

        // then
        List<Store> all = storeRepository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getAddress()).isEqualTo(address);
    }
}
