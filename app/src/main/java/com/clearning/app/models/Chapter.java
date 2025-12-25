package com.clearning.app.models;

public class Chapter {
    private String chapterId;
    private String courseId;
    private String title;
    private int order;
    private int totalLessons;
    private boolean isCompleted;
    private int completedLessons;

    // Empty constructor for Firebase
    public Chapter() {
    }

    public Chapter(String chapterId, String courseId, String title, int order, int totalLessons) {
        this.chapterId = chapterId;
        this.courseId = courseId;
        this.title = title;
        this.order = order;
        this.totalLessons = totalLessons;
        this.isCompleted = false;
        this.completedLessons = 0;
    }

    // Getters and Setters
    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
        // Auto-update completion status
        this.isCompleted = (completedLessons >= totalLessons);
    }

    public int getProgressPercentage() {
        if (totalLessons == 0) return 0;
        return (completedLessons * 100) / totalLessons;
    }
}
