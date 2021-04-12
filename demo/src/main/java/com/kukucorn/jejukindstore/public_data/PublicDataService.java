package com.kukucorn.jejukindstore.public_data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PublicDataService {

    // (url) -> Dom
    public String getXMLFromUrl(String strUrl) throws Exception {

        StringBuilder urlBuilder = new StringBuilder("http://210.99.248.79/rest/GoodPriceStoreService/getGoodPriceStoreList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=2To0T7JGsCWoVHuRZ9JJpXgH43XEM7voKHbKqE%2FMW0WITJeIu2LPplMYBUwbukV6hGN6Z3IwsrTc9ea8RhQzEg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*시작 페이지*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지 사이즈*/
        urlBuilder.append("&" + URLEncoder.encode("ServceKey","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*요청키*/
        URL url = new URL(urlBuilder.toString());

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

    public List<JejuKindStore> XMLToList(String xml) throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("list");

        List<JejuKindStore> storeList = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

        }



        return null;
    }
}
