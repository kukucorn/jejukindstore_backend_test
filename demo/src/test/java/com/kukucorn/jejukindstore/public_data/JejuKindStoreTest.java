package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class JejuKindStoreTest {

    @Test
    public void JejuKindStore_goods_prices_parsing_test() {
        // given
        String address = "옥계동";
        String name = "아무개집";
        String location = "어딘가";
        String telephone = "callmebaby";
        String category = "뭐하는 집인고";
        String[] goods_prices = {
                "커트 7,000원(경로 6,000원), 파마 25,000원(경로 15,000원), 염색 25,000원"
                ,"우거지탕6000원   , 김치찌개 6,000원, 된장찌개 6,000원,비빔밥(여름) 5,000원"
                , "된장찌개 5,000원, 김치찌개 5,000원, 샤브전골(1인분) 7,000원,내장탕 6,000원, 해장국 6,000원"
                , "된장찌개 6,000원, 참치찌개 6,000원, 김치찌개 6,000원만두찌개 6,000원, 찌개백반 6,000원"
                , "부시리초밥 7,900원, 홍용초밥(부시리)(2인분) 15,800원, 백용초밥(광어)(2인분) 17,800원"
                , "커트 7,000원(삼푸안할 경우 6,000원),커트+염색(고급염색약 사용) 13,000원"
                , "닭소금구이(320g) 11,000원, 닭갈비(320g) 12,000원, 냉면 6,000원,김치찌개 서비스"
                , "뽈살(180g) 9,500원, 항정,가브리,뒷목살(180g) 10,000원, 두루치기(공기밥 별도 1,000원) 6,000원"};

        for(String goods_price: goods_prices) {
            JejuKindStore jejuKindStore = JejuKindStore.builder()
                    .address(address)
                    .goods_price(goods_price)
                    .category(category)
                    .location(location)
                    .telephone(telephone)
                    .name(name)
                    .build();
            Store store = jejuKindStore.toStore();

            // when
            List<StoreMenu> menuList = jejuKindStore.toStoreMenuList(store);

            // then
            assertThat(menuList.size()).isGreaterThan(0);
            log.info("goods_prices : {}, \nmenuList : {}", goods_price, menuList);
        }
    }
}
