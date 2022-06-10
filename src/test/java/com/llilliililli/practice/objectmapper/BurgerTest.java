package com.llilliililli.practice.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    //java 객체를 json으로 변환
    @Test
    public void java_to_json() throws JsonProcessingException {

        //준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("검은빵","순쇠고기 패티","토마토","마요네즈 소스");
        Burger burger = new Burger("기네스버거",6000, ingredients);

        //수행
        String json = objectMapper.writeValueAsString(burger);

        //예상
        String expected = "{\"name\":\"기네스버거\",\"price\":6000,\"ingredients\":[\"검은빵\",\"순쇠고기 패티\",\"토마토\",\"마요네즈 소스\"]}";

        //검증
        assertEquals(expected,json);
        //System.out.println(json);

        // JSON 결과 예쁘게 출력
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());

    }

    //json을 java 객체로 변환
    @Test
    public void json_to_java() throws JsonProcessingException {

        //준비
        ObjectMapper objectMapper = new ObjectMapper();
        //String json = "{\"name\":\"기네스버거\",\"price\":6000,\"ingredients\":[\"검은빵\",\"순쇠고기 패티\",\"토마토\",\"마요네즈 소스\"]}";
        /*
            {
                "name" : "기네스버거"
                "price" : 6000
                "ingredients" : [ "검은빵","순쇠고기 패티","토마토","마요네즈 소스" ]
            }
        */
         ObjectNode objectNode = objectMapper.createObjectNode();
         objectNode.put("name","기네스버거");
         objectNode.put("price",6000);

         ArrayNode arrayNode = objectMapper.createArrayNode();
         arrayNode.add("검은빵");
         arrayNode.add("순쇠고기 패티");
         arrayNode.add("토마토");
         arrayNode.add("마요네즈 소스");
         objectNode.set("ingredients",arrayNode);

         String json = objectNode.toString();


        //수행
        Burger burger = objectMapper.readValue(json,Burger.class);

        //예상
        List<String> ingredients = Arrays.asList("검은빵","순쇠고기 패티","토마토","마요네즈 소스");
        Burger expected = new Burger("기네스버거",6000, ingredients);

        //검증
        assertEquals(expected.toString(),burger.toString());

        // JSON 결과 예쁘게 출력
        JsonNode jsonNode = objectMapper.readTree(json);

        System.out.println("json :: \n"+jsonNode.toPrettyString());
        System.out.println("java :: "+burger);



    }

}