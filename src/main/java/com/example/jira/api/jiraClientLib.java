package com.example.jira.api;
import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.agile.Board;
import net.rcarz.jiraclient.agile.AgileClient;

public class jiraClientLib {
    public static void main(String[] args) {

        BasicCredentials creds = new BasicCredentials("batman", "pow! pow!");
        JiraClient jira = new JiraClient("https://jira.example.com/jira", creds);
        AgileClient agileClient = new AgileClient(jira);

        try {
            /* Retrieve all Boards */
            List<Board> allBoards = agileClient.getBoards();
        } catch (JiraException ex) {
            System.err.println(ex.getMessage());

            if (ex.getCause() != null) {
                System.err.println(ex.getCause().getMessage());
            }
        }
    }
}
