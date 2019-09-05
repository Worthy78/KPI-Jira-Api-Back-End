package com.example.jira.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class createboard {

    public static void main(String[] args) {
        // The payload definition using the Jackson library
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            root.put("name", "<string>");
            root.put("type", "<string>");
            root.put("filterId", 2154);
            ObjectNode location = root.putObject("location");
            {
                location.put("type", "<string>");
                location.put("projectKeyOrId", "<string>");
            }
        }

        // Connect Jackson ObjectMapper to Unirest
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // This code sample uses the 'Unirest' library:
        // http://unirest.io/java.html
        HttpResponse<JsonNode> response = Unirest.post("https://your-domain.atlassian.net/rest/agile/1.0/board")
                .basicAuth("email@example.com", "<api_token>").header("Accept", "application/json")
                .header("Content-Type", "application/json").body(payload).asJson();

        System.out.println(response.getBody());
    }
}