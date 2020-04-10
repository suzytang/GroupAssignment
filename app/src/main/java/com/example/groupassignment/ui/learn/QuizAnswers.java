package com.example.groupassignment.ui.learn;

import java.io.Serializable;

public class QuizAnswers implements Serializable {
    private int question;
    private String english;
    private int score;
    private String answer;
    private String translation;

    public QuizAnswers() {
    }

    public QuizAnswers(int question, String english, int score, String answer, String translation) {
        this.question = question;
        this.english = english;
        this.score = score;
        this.answer = answer;
        this.translation = translation;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
