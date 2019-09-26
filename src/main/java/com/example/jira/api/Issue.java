package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class Issue extends ApiResponse<Issue> {
   /* private String expand ;*/
    private  int id ;
    private  String self ;
    private String key ;
    private Fields fields ;
    private double storyPoint = 0 ;

    public double getStoryPoint() {
        int boardId = 28;
        System.out.println("ID"+fields.project);
        if(storyPoint == 0){
            WebClient client = (new Client()).getClient();
            StoryPoint response = client.get()
                    .uri("/rest/agile/1.0/issue/" + key + "/estimation?boardId=" + boardId)
                    .retrieve()
                    .bodyToMono(StoryPoint.class).block();
            storyPoint = response.getValue();
        }
        return storyPoint;
    }


    public int getId() {
        return id;
    }

    public String getSelf() {
        return self;
    }

    public String getKey() {
        return key;
    }

    public Fields getFields() {
        return fields;
    }

    public String type (){
        return fields.issuetype.getName();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", self='" + self + '\'' +
                ", key='" + key + '\'' +
                ", fields=" + fields +
                ", storyPoint=" + getStoryPoint() +
                '}';
    }
}


class Fields {
    public String summary ;
    public ArrayList versions ;
    public Sprint sprint ;
    public Project project ;
    public String epic ;
    public Status status ;
    public  IssueType issuetype;
    public Priority priority ;

    @Override
    public String toString() {
        return "{summary: "+summary + " status : "+status+"}";
    }
}

class  commonFields {
    private String self ;
    private int id ;
    private String name ;
    private String iconUrl;

    public String getSelf() {
        return self;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    @Override
    public String toString() {
        return "{id: "+id+" self : "+self + " name : "+name+"}";
    }
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