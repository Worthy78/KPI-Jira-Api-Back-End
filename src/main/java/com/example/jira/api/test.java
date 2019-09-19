package com.example.jira.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class test {
    public static void main(String[] args) {
/*        HttpResponse<JsonNode> response = Unirest.get("https://sambasg7.atlassian.net/rest/agile/1.0/sprint/2")
                        .basicAuth("sougousamba@gmail.com", "DARsOdtDKiu1iev0pReXC8D9")
                        .header("Accept", "application/json")
                        .asJson();
                System.out.println(response.getBody());
         }
*/
        WebClient client = WebClient
                .builder()
                .baseUrl("https://sambasg7.atlassian.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth("sougousamba@gmail.com", "DARsOdtDKiu1iev0pReXC8D9"))
                .build();

        Boards response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum")
                .retrieve()
                .bodyToMono(Boards.class).block();

        System.out.println(response.getValues());
    }
}
