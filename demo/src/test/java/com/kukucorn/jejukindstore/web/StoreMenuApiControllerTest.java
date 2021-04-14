package com.kukucorn.jejukindstore.web;

import com.kukucorn.jejukindstore.domain.storemenu.StoreMenuRepository;
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
public class StoreMenuApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private StoreMenuRepository menuRepository;

    @Test
    public void storeMenuList_조회한다() {
        // given
        Integer store_id = 4;

        String url = "http://localhost:" + port + "/api/v1/store/" + store_id + "/menu";

        // when
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        // then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(entity.getBody());
    }
}
