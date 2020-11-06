package com.xw.util;



import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class BingImg {
    private final String low = "_800x480.jpg";
    private final String height = "_800x480.jpg";

    public static String visitBing() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://cn.bing.com/hpm?ensearch=1&FORM=BEHPTB&chunk=1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return exchange.getBody();
    }

    public static BingIndex parseHTMLStringToIndex() {
        BingIndex bingIndex = new BingIndex();
        String bing = visitBing();
        Document doc = Jsoup.parse(bing);
        Element body = doc.body();
        String imgURL = body.select("img").first().attributes().get("src");
        String title = body.select("h3").first().text();
        String article = body.select("span").first().text();
        String location = body.select("a").first().attributes().get("aria-label");

        if (StringUtils.isNotBlank(imgURL) && StringUtils.isNotBlank(title) && StringUtils.isNotBlank(article)) {
            String flag =  imgURL.substring(imgURL.indexOf("=")+1, imgURL.indexOf("."));
            String name = imgURL.substring(imgURL.indexOf(".")+1, imgURL.indexOf("_"));
            String id = imgURL.substring(imgURL.indexOf("-")+1, imgURL.lastIndexOf("_"));

            bingIndex.setId(id);
            bingIndex.setFlag(flag);
            bingIndex.setLocation(location);
            bingIndex.setTitle(title);
            bingIndex.setArticle(article);
            bingIndex.setName(name);
            bingIndex.setCreateDate(LocalDate.now());
        }


        return bingIndex;
    }
}
