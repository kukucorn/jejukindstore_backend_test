package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.store.StoreRepository;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PublicDataServiceTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Autowired
    private PublicDataService publicDataService;

    @Test
    public void responseData가_어떻게() throws Exception {
        // given
        String url = "http://210.99.248.79/rest/GoodPriceStoreService/getGoodPriceStoreList?serviceKey=2To0T7JGsCWoVHuRZ9JJpXgH43XEM7voKHbKqE%2FMW0WITJeIu2LPplMYBUwbukV6hGN6Z3IwsrTc9ea8RhQzEg%3D%3D";

        // when
        publicDataService.uploadStoreDataToDB();

        // then
        assertThat(storeRepository.count()).isGreaterThan(0);
        assertThat(storeMenuRepository.count()).isGreaterThan(0);
    }
}
