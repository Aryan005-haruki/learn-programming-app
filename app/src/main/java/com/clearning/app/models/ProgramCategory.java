package com.clearning.app.models;

public class ProgramCategory {
    private String name;
    private String description;
    private String emoji;
    private int programCount;

    public ProgramCategory(String name, String description, String emoji, int programCount) {
        this.name = name;
        this.description = description;
        this.emoji = emoji;
        this.programCount = programCount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmoji() {
        return emoji;
    }
    
    public int getProgramCount() {
        return programCount;
    }
}
