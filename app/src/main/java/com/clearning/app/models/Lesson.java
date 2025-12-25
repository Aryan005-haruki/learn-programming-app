package com.clearning.app.models;

public class Lesson {
    private String lessonId;
    private String chapterId;
    private String title;
    private String explanation;
    private String explanationHinglish; // Hinglish version
    private String syntax;
    private String exampleCode;
    private String output;
    private String notes;
    private String practiceQuestion;
    private String practiceQuestionHinglish; // Hinglish version
    private int order;
    private boolean isCompleted;

    // Empty constructor for Firebase
    public Lesson() {
    }

    public Lesson(String lessonId, String chapterId, String title, String explanation,
                  String syntax, String exampleCode, String output, String notes,
                  String practiceQuestion, int order) {
        this.lessonId = lessonId;
        this.chapterId = chapterId;
        this.title = title;
        this.explanation = explanation;
        this.explanationHinglish = explanation; // Default to English
        this.syntax = syntax;
        this.exampleCode = exampleCode;
        this.output = output;
        this.notes = notes;
        this.practiceQuestion = practiceQuestion;
        this.order = order;
        this.isCompleted = false;
    }

    // Getters and Setters
    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanationHinglish() {
        return explanationHinglish;
    }

    public void setExplanationHinglish(String explanationHinglish) {
        this.explanationHinglish = explanationHinglish;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getExampleCode() {
        return exampleCode;
    }

    public void setExampleCode(String exampleCode) {
        this.exampleCode = exampleCode;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPracticeQuestion() {
        return practiceQuestion;
    }

    public void setPracticeQuestion(String practiceQuestion) {
        this.practiceQuestion = practiceQuestion;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    
    // Hinglish getter/setter for practice question
    public String getPracticeQuestionHinglish() {
        return practiceQuestionHinglish;
    }
    
    public void setPracticeQuestionHinglish(String practiceQuestionHinglish) {
        this.practiceQuestionHinglish = practiceQuestionHinglish;
    }
}
