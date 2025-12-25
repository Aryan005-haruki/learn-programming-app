package com.clearning.app.models;

import java.util.HashMap;

public class User {
    private String uid;
    private String name;
    private String email;
    private String phone;
    private HashMap<String, Boolean> completedLessons;
    private HashMap<String, Integer> chapterScores;
    private int totalScore;
    private int lessonsCompleted;

    // Empty constructor for Firebase
    public User() {
        this.completedLessons = new HashMap<>();
        this.chapterScores = new HashMap<>();
        this.totalScore = 0;
        this.lessonsCompleted = 0;
    }

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.completedLessons = new HashMap<>();
        this.chapterScores = new HashMap<>();
        this.totalScore = 0;
        this.lessonsCompleted = 0;
    }

    // Getters and Setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, Boolean> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(HashMap<String, Boolean> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public HashMap<String, Integer> getChapterScores() {
        return chapterScores;
    }

    public void setChapterScores(HashMap<String, Integer> chapterScores) {
        this.chapterScores = chapterScores;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getLessonsCompleted() {
        return lessonsCompleted;
    }

    public void setLessonsCompleted(int lessonsCompleted) {
        this.lessonsCompleted = lessonsCompleted;
    }

    // Helper methods
    public void markLessonComplete(String lessonId) {
        if (!completedLessons.containsKey(lessonId)) {
            completedLessons.put(lessonId, true);
            lessonsCompleted++;
        }
    }

    public boolean isLessonCompleted(String lessonId) {
        return completedLessons.containsKey(lessonId) && completedLessons.get(lessonId);
    }

    public void updateChapterScore(String chapterId, int score) {
        chapterScores.put(chapterId, score);
        calculateTotalScore();
    }

    private void calculateTotalScore() {
        totalScore = 0;
        for (Integer score : chapterScores.values()) {
            totalScore += score;
        }
    }
}
