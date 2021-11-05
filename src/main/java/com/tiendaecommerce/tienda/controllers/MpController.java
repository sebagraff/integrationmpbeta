package com.tiendaecommerce.tienda.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MpController {
    @PostMapping("/mp")
    public ResponseEntity<?> savempapi(@RequestParam String phoneName, @RequestParam String phoneDescr, @RequestParam String phoneImg, @RequestParam String phoneQuant, @RequestParam int phonePrice){
        String resss = "";
        String init_point = "";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                "{\r\n    " +
                        "\"items\": " +
                        "[\r\n        {\r\n            " +
                        "\"id\":\"1234\"," +
                        "," +
                        "\r\n            " +
                        "\"title\": \""+phoneName+"\"," +
                        "\r\n            " +
                        "\"picture_url\": \""+phoneImg+"\"," +
                        "\r\n            " +
                        "\"description\": \""+phoneDescr+"\"," +
                        "\r\n            " +
                        "\"quantity\": "+phoneQuant+"," +
                        "\r\n            " +
                        "\"unit_price\": "+phonePrice+"" +
                        "\r\n        }" +
                        "\r\n    ]," +
                        "\r\n    " +
                        "\"payer\": {\r\n        " +
                        "\"name\": \"Lalo\"," +
                        "\r\n        " +
                        "\"surname\": \"Landa\"," +
                        "\r\n        " +
                        "\"email\": \"test_user_63274575@testuser.com\"," +
                        "\r\n        " +
                        "\"phone\": {\r\n            " +
                        "\"area_code\": \"11\"," +
                        "\r\n            " +
                        "\"number\": \"22223333\"" +
                        "\r\n        },\r\n        " +
                        "\"identification\": {\r\n            " +
                        "\"type\": \"\",\r\n            " +
                        "\"number\": \"\"\r\n        },\r\n        " +
                        "\"address\": {\r\n            " +
                        "\"street_name\": \"Calle Falsa\",\r\n            " +
                        "\"street_number\": 123,\r\n            " +
                        "\"zip_code\": \"111\"\r\n        }\r\n    },\r\n    " +
                        "\"back_urls\": {\r\n        " +
                        "\"success\": \"https://integrationmpbeta.herokuapp.com/web/index.html\",\r\n        " +
                        "\"failure\": \"https://integrationmpbeta.herokuapp.com/web/index.html\",\r\n        " +
                        "\"pending\": \"https://integrationmpbeta.herokuapp.com/web/index.html\"\r\n    },\r\n    " +
                        "\"auto_return\": \"all\",\r\n    " +
                        "\"payment_methods\": {\r\n        " +
                        "\"excluded_payment_methods\": [\r\n            {\r\n                " +
                        "\"id\": \"amex\"\r\n            }\r\n        ],\r\n        " +
                        "\"excluded_payment_types\": [\r\n            {\r\n                " +
                        "\"id\": \"atm\"\r\n            }\r\n        ],\r\n        " +
                        "\"installments\": 6\r\n    },\r\n    " +
                        "\"notification_url\": \"https://integrationmpbeta.herokuapp.com/webhooksmp\",\r\n    " +
                        "\"statement_descriptor\": \"MINEGOCIO\",\r\n   " +
                        " \"external_reference\": \"sebastiandavidgraff@gmail.com\",\r\n}");
        Request request = new Request.Builder()
                .url("https://api.mercadopago.com/checkout/preferences")
                .method("POST", body)
                .addHeader("x-integrator-id", "dev_24c65fb163bf11ea96500242ac130004")
                .addHeader("Authorization", "Bearer APP_USR-6317427424180639-042414-47e969706991d3a442922b0702a0da44-469485398")
                .addHeader("Content-Type", "text/plain")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            resss = resStr ;
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
            Map<String, Object> mapping = new ObjectMapper().readValue(resss, typeRef);
            init_point = (String) mapping.get("init_point");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(init_point, HttpStatus.OK);

    }
}