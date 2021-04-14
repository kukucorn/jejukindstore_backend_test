package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
public class JejuKindStore {

    private String name;
    private String location;
    private String address;
    private String telephone;
    private String category;
    private String goods_price;
    private Integer num;

    @Builder
    public JejuKindStore(String name, String location, String address, String telephone, String category, String goods_price, Integer num) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.telephone = telephone;
        this.category = category;
        this.goods_price = goods_price;
        this.num = num;
    }

    public JejuKindStore(Element storeInfoRootElement) {
        this.name = getContentOrEmpty(storeInfoRootElement, "name");
        this.location = getContentOrEmpty(storeInfoRootElement, "location");
        this.address = getContentOrEmpty(storeInfoRootElement, "address");
        this.telephone = getContentOrEmpty(storeInfoRootElement, "telephone");
        this.category = getContentOrEmpty(storeInfoRootElement, "category");
        this.goods_price = getContentOrEmpty(storeInfoRootElement, "goods_price");
        this.num = Integer.parseInt(getContentOrEmpty(storeInfoRootElement, "num"));
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
                .num(num).build();
    }

    public List<StoreMenu> toStoreMenuList(Store store) {

        List<StoreMenu> storeMenuList = new ArrayList<>();

        // pattern 정의 :      문자             , or +       (문자)         10,000 원 or 5만원 (문자)      ,
        String pattern = "([[a-zA-Zㄱ-ㅎ가-힣]+[\\+\\,]?]+(\\([^)]+\\))*)\\s*([0-9,]+만?원(\\([^)]+\\))*),?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(goods_price);

        while(m.find()) {
            String name = m.group(1);
            String price = m.group(3);
            StoreMenu menu = StoreMenu.builder()
                    .name(name)
                    .price(price)
                    .store(store).build();
            storeMenuList.add(menu);
        }

        // 문제 발생
        if(storeMenuList.size() == 0) {
            log.error("일치하는 pattern 이 없는 goods_price 를 가진 가계 \n store : {}, goods_price : {}", store, goods_price);
        }

        return storeMenuList;
    }
}
