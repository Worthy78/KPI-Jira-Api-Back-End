package com.example.jira.api;

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
        /*
        Board scrumBoard = new Board();
        List<Board> boardList = scrumBoard.getAllBoard();

        System.out.println("BOARD LIST :");
        for (Board temp : boardList) {
            System.out.println(temp);
        }

        System.out.println("BOARD SPRINT  LIST:  ");
        List<Sprint> boardSprintList = boardList.get(0).getAllSprint();
        System.out.println(boardSprintList.size() +" boardSprintList :  ");
        for (Sprint temp : boardSprintList) {
            System.out.println(temp);
        }

        System.out.println("BOARD SPRINT ISSUE LIST:  ");
       // ISSUE
        List<Issue> boardSprintIssueList = boardSprintList.get(0).getAllIssue();
        System.out.println(boardSprintIssueList.size() +" boardSprintIssueList :  ");
        for (Issue temp : boardSprintIssueList) {
            System.out.println(temp);
        }
        */
        List<Project> projects = (new Client()).getAllProject();
        System.out.println(" PROJECTS ! :  ");
        for (Project temp : projects) {
            System.out.println(temp);
        }
    }
}
