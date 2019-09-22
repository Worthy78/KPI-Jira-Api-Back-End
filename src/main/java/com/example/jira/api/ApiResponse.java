package com.example.jira.api;


import java.util.List;

public class ApiResponse <T>  extends ResponseHeader {
 private  List<T> values ;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
