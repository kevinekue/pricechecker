package com.myretail.pricechecker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 3/22/2021
 */


public class ProductInfo {

    //    public static Map<String, String> getProductInfo(int productId) throws IOException {
    public static String getProductInfo(int productId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://redsky.target.com/v3/pdp/tcin/" + productId + "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics," +
                "question_answer_statistics&key=candidate"))
                .GET()
                .build();
        System.out.println(request);
        String result = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
//        Map<String, String> productInfoMap = mapper.readValue(result, Map.class);
        JsonNode jNode = mapper.readTree(result);
        JsonNode jNode2 = mapper.readValue(result, JsonNode.class);
        System.out.println("Fields: "+jNode.size());
        System.out.println("Fields: "+jNode2.size());
        System.out.println("Title:"+ jNode.get("product").get("item").get("product_description").get("title").textValue() );
//        Iterator<String> fieldNames = jNode2.fieldNames();



        return result;

    }
}
