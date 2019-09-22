package com.example.jira.api;

import java.util.List;

public class Issue {
    private String expand ;
    private  int id ;
    private  String self ;
    private String key ;
    private Fields fields ;
}


class Fields {
    public String summary ;
    public  int customfield_10104 ;
    public String versions ;
    public Sprint Sprint ;
    public String epic ;
    public Status status ;
    public  IssueType issuetype;
    public Priority priority ;
}

class  commonFields {
    String self ;
    int id ;
    String name ;
    String iconUrl;
}
class Status extends  commonFields {
    String description ;
/**
 * statusCategory
 */
}

class IssueType extends  commonFields{
    String description ;
 int avatarId ;
 boolean subtask ;
}

class Priority extends commonFields {
}