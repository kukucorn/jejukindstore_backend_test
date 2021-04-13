package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Getter
public class JejuKindStore {

    private String name;
    private String location;
    private String address;
    private String telephone;
    private String category;
    private String goods_price;

    @Builder
    public JejuKindStore(String name, String location, String address, String telephone, String category, String goods_price) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.telephone = telephone;
        this.category = category;
        this.goods_price = goods_price;
    }

    public JejuKindStore(Element storeInfoRootElement) {
        this.name = getContentOrEmpty(storeInfoRootElement, "name");
        this.location = getContentOrEmpty(storeInfoRootElement, "location");
        this.address = getContentOrEmpty(storeInfoRootElement, "address");
        this.telephone = getContentOrEmpty(storeInfoRootElement, "telephone");
        this.category = getContentOrEmpty(storeInfoRootElement, "category");
        this.goods_price = getContentOrEmpty(storeInfoRootElement, "goods_price");
    }

    private String getContentOrEmpty(Element element, String tagName) {
        try {
            return element.getElementsByTagName(tagName).item(0).getTextContent();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public Store toStore() {
        return Store.builder()
                .name(name)
                .location(location)
                .address(address)
                .telephone(telephone)
                .category(category)
                .build();
    }
}
