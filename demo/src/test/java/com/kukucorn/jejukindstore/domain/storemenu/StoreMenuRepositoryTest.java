package com.kukucorn.jejukindstore.domain.storemenu;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class StoreMenuRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMenuRepository menuRepository;

    @Test
    public void store_menu_findAll_test() {
        // given
        Integer store_id = 4;

        // when
        List<StoreMenu> menuList = menuRepository.findAllByStoreId(store_id);

        // then
        assertThat(menuList.size()).isGreaterThan(0);
        assertThat(menuList.get(0).getStore().getId()).isEqualTo(store_id);
        log.info("menuList : {}", menuList);
    }
}
