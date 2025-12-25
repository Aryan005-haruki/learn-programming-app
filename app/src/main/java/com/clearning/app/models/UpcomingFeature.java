package com.clearning.app.models;

public class UpcomingFeature {
    private String name;
    private String description;
    private String status; // "Coming Soon", "In Progress", "Planned"
    private String emoji;

    public UpcomingFeature(String name, String description, String status, String emoji) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getEmoji() {
        return emoji;
    }
}
