package com.clearning.app.models;

public class Course {
    private String courseId;
    private String courseName;
    private String description;
    private int totalChapters;
    private String imageUrl;
    private int completedChapters;

    // Empty constructor for Firebase
    public Course() {
    }

    public Course(String courseId, String courseName, String description, int totalChapters, String imageUrl) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.totalChapters = totalChapters;
        this.imageUrl = imageUrl;
        this.completedChapters = 0;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCompletedChapters() {
        return completedChapters;
    }

    public void setCompletedChapters(int completedChapters) {
        this.completedChapters = completedChapters;
    }

    public int getProgressPercentage() {
        if (totalChapters == 0) return 0;
        return (completedChapters * 100) / totalChapters;
    }
}
