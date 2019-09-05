package com.example.jira.api;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class test {

    public static void main(String[] args){
        // This code sample uses the 'Unirest' library:
        // http://unirest.io/java.html
        HttpResponse<JsonNode> response = Unirest.get("https://rachid.atlassian.net/rest/agile/1.0/sprint/1")
                .basicAuth("sirabraham2016@gmail.com", "CQ9by9RLrYvfOd3GYgoX9B9A")
                .header("Accept", "application/json")
                .asJson();

        System.out.println(response.getBody());
    }
}