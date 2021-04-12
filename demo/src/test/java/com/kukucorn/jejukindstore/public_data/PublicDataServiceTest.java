package com.kukucorn.jejukindstore.public_data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicDataServiceTest {

    PublicDataService publicDataService = new PublicDataService();

    @Test
    public void responseData가_어떻게() throws Exception {
        // given
        String url = "http://210.99.248.79/rest/GoodPriceStoreService/getGoodPriceStoreList?serviceKey=2To0T7JGsCWoVHuRZ9JJpXgH43XEM7voKHbKqE%2FMW0WITJeIu2LPplMYBUwbukV6hGN6Z3IwsrTc9ea8RhQzEg%3D%3D";

        // when
        String xml = publicDataService.getXMLFromUrl(url);
        List<JejuKindStore> storeList = publicDataService.XMLToList(xml);

        // then
        assertThat(xml).contains("body");
    }
}
