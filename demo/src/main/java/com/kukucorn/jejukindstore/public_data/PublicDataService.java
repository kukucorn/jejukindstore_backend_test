package com.kukucorn.jejukindstore.public_data;

import com.kukucorn.jejukindstore.domain.store.Store;
import com.kukucorn.jejukindstore.domain.store.StoreRepository;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenu;
import com.kukucorn.jejukindstore.domain.storemenu.StoreMenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PublicDataService {

    private final StoreRepository storeRepository;
    private final StoreMenuRepository storeMenuRepository;

    public void uploadStoreDataToDB() {

        List<JejuKindStore> storeList = getStoreListFromUrl("");

        for(int i = 0; i < storeList.size(); i++) {
            JejuKindStore storeInfo = storeList.get(i);

            // 가게
            Store store = storeInfo.toStore();
            storeRepository.save(store);

            // 메뉴
            List<StoreMenu> storeMenu = storeInfo.toStoreMenuList(store);
            for(StoreMenu menu : storeMenu) {
                storeMenuRepository.save(menu);
            }

            // 태그
            // 가게 태그
        }
    }

    public List<JejuKindStore> getStoreListFromUrl(String strUrl) {
        try {
            URL url = convertStringToUrl(strUrl);
            String xmlString = getXMLStringFromUrl(url);
            Document document = convertStringToXMLDocument(xmlString);
            return convertDocumentToList(document);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL convertStringToUrl(String strUrl) {

        try {
            StringBuilder urlBuilder = new StringBuilder("http://210.99.248.79/rest/GoodPriceStoreService/getGoodPriceStoreList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=2To0T7JGsCWoVHuRZ9JJpXgH43XEM7voKHbKqE%2FMW0WITJeIu2LPplMYBUwbukV6hGN6Z3IwsrTc9ea8RhQzEg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*시작 페이지*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3760", "UTF-8")); /*페이지 사이즈*/

            URL url = new URL(urlBuilder.toString());

            return url;
        } catch (UnsupportedEncodingException encodingException) {
            encodingException.printStackTrace();
        } catch (MalformedURLException urlException) {
            urlException.printStackTrace();
        }
        return null;
    }

    private String getXMLStringFromUrl(URL url) throws Exception {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // utf8로 디코딩
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());

        return sb.toString();
    }

    private Document convertStringToXMLDocument(String xml) throws Exception {

        // Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            // Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            // Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private List<JejuKindStore> convertDocumentToList(Document document) {

        final String[] nodeNames = {};

        NodeList nodeList = document.getElementsByTagName("list");
        List<JejuKindStore> storeList = new ArrayList<>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node storeInfoRootNode = nodeList.item(i);

            JejuKindStore storeInfo = new JejuKindStore((Element)storeInfoRootNode);

            storeList.add(storeInfo);
        }

        return storeList;
    }
}
