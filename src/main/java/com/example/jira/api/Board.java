package com.example.jira.api;


/*class Boards extends ResponseHeader  {
    private List<Board> values ;

    public List<Board> getValues() {
        return values;
    }
}*/

public class Board extends ApiResponse<Board>  {
    private int id ;

    private String name ;
    private String type ;
    private Location  location ;

    public int getId() {
        return id;
    }

   /* public String getSelf() {
        return self;
    }*/

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "id: "+id+" name : "+name + " type : "+type;
    }
}

class Location {
    private int projectId ;
    private String displayName ;
    private String projectName ;
    private String projectKey ;
    private String projectTypeKey ;
    private String avatarURI ;
    private  String name ;

    public int getProjectId() {
        return projectId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectTypeKey() {
        return projectTypeKey;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public String getName() {
        return name;
    }
}
