package com.example.jira.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class test {
    public static void main(String[] args) {
/*        HttpResponse<JsonNode> response = Unirest.get("https://sambasg7.atlassian.net/rest/agile/1.0/sprint/2")
                        .basicAuth("sougousamba@gmail.com", "DARsOdtDKiu1iev0pReXC8D9")
                        .header("Accept", "application/json")
                        .asJson();
                System.out.println(response.getBody());
         }
         ("sirabraham2016@gmail.com", "CQ9by9RLrYvfOd3GYgoX9B9A")
*/
        WebClient client = WebClient
                .builder()
                .baseUrl("https://rachid.atlassian.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth("sirabraham2016@gmail.com", "CQ9by9RLrYvfOd3GYgoX9B9A"))
                .build();
     /*   ApiResponse<Board> response = new  ApiResponse<Board>();
        response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum")
                .retrieve()
                .bodyToMono(response.getClass()).block();

        List<Board> boardList = response.getValues() ;
   /*     for (Board temp : boardList) {
            System.out.println(temp);
        } /
        System.out.println(response.getValues().get(0).getName());     */


      Board  response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum")
                .retrieve()
                .bodyToMono(Board.class).block();

        List<Board> boardList = response.getValues() ;
   /*     for (Board temp : boardList) {
            System.out.println(temp);
        }*/
        for (Board temp : boardList) {
            System.out.println(temp);
        }
    
    }
}
