package com.kukucorn.jejukindstore.web;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.store.StoreRepository;
import com.kukucorn.jejukindstore.web.dto.StoreSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void store_등록된다() {
        // given
        String name = "공룡피자";
        String location = "옥계동";
        String address = "구미시 산호대로 24길 9-13 근처";
        String telephone = "054-000-0000";
        StoreSaveRequestDto requestDto = new StoreSaveRequestDto(name, location, address, telephone);

        String url = "http://localhost:" + port + "/api/v1/store";

        // when
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, requestDto, Integer.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0);

        List<Store> all = storeRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getTelephone()).isEqualTo(telephone);
    }

    @Test
    public void store_list_조회하다() {
        // given
        String url = "http://localhost:" + port + "/api/v1/store";

        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void store_조회하다() {
        // given
        String name = "알쌈닭";
        String location = "구미";
        String address = "구미 옥계동";
        String telephone = "010-1234-5678";

        Store store = storeRepository.save(new StoreSaveRequestDto(name, location, address, telephone).toEntity());

        String url = "http://localhost:" + port + "/api/v1/store/" + store.getId();

        // when
        ResponseEntity<Store> responseEntity = restTemplate.getForEntity(url, Store.class);

        // then
        assertThat(responseEntity.getBody().getName()).isEqualTo(name);
        assertThat(responseEntity.getBody().getAddress()).isEqualTo(address);
    }
}
