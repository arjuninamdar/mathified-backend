package com.arjuninamdar.congressionalchallenge.models.http;

public class AnswerRequest {
    private int questionId;
    private String answer;

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
