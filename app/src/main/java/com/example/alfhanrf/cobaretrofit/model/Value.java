package com.example.alfhanrf.cobaretrofit.model;

import java.util.List;

/**
 * Created by Alfhanrf on 6/1/2018.
 */

public class Value {

    String value;
    String message;

    List<Mahasiswa> result;

    public String getValue(){
        return value;
    }

    public String getMessage(){
        return message;
    }

    public List<Mahasiswa> getResult(){
        return result;
    }
}
