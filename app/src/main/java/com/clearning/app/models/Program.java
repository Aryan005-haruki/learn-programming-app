package com.clearning.app.models;

public class Program {
    private String title;
    private String description;
    private String code;
    private String output;

    public Program(String title, String description, String code, String output) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.output = output;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getOutput() {
        return output;
    }
}
