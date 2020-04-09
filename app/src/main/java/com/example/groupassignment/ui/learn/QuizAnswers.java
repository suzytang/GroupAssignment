package com.example.groupassignment.ui.learn;

public class QuizAnswers {
    private int question;
    private String english;
    private int score;
    private String answer;
    private String translation;

    public QuizAnswers(int count, String text, int score, String toString, String result) {
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
