package com.example.andy.backend;

/**
 * Created by andy on 10/7/15.
 */
public class Joke {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Joke(String data) { setData(data); }
}
