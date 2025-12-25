package com.clearning.app.models;

import java.io.Serializable;
import java.util.ArrayList;

// Make Quiz Serializable so it can be passed via Intent
public class Quiz implements Serializable {
    private String quizId;
    private String chapterId;
    private String question;
    private ArrayList<String> options;
    private int correctAnswer;
    private String explanation;
    private int userAnswer;
    
    // Hinglish fields for bilingual support
    private String questionHinglish;
    private ArrayList<String> optionsHinglish;
    private String explanationHinglish;

    // Empty constructor for Firebase
    public Quiz() {
        this.options = new ArrayList<>();
        this.userAnswer = -1; // -1 means not answered
    }

    public Quiz(String quizId, String chapterId, String question, ArrayList<String> options,
                int correctAnswer, String explanation) {
        this.quizId = quizId;
        this.chapterId = chapterId;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.userAnswer = -1;
    }

    // Getters and Setters
    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isCorrect() {
        return userAnswer == correctAnswer;
    }

    public boolean isAnswered() {
        return userAnswer != -1;
    }
    
    // Hinglish getters and setters
    public String getQuestionHinglish() {
        return questionHinglish;
    }
    
    public void setQuestionHinglish(String questionHinglish) {
        this.questionHinglish = questionHinglish;
    }
    
    public ArrayList<String> getOptionsHinglish() {
        return optionsHinglish;
    }
    
    public void setOptionsHinglish(ArrayList<String> optionsHinglish) {
        this.optionsHinglish = optionsHinglish;
    }
    
    public String getExplanationHinglish() {
        return explanationHinglish;
    }
    
    public void setExplanationHinglish(String explanationHinglish) {
        this.explanationHinglish = explanationHinglish;
    }
}
