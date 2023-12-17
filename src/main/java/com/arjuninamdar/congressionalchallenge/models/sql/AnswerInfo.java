package com.arjuninamdar.congressionalchallenge.models.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ANSWER_INFO")
public class AnswerInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private int answerId;

    @Column(name= "ANSWER")
    private String answer;

    @Column(name = "TUTOR_ANSWERED_ID")
    private int tutorAnsweredId;

    @Column(name = "QUESTION_ANSWERED_ID")
    private int questionAnsweredId;

    @Column(name = "DATE_TIME_ANSWERED")
    private String dateTimeAnswered;

    @Column(name = "IS_ENABLED")
    private boolean isEnabled;

    public AnswerInfo() {

    }

    public AnswerInfo(int tutorAnsweredId, String answer, int questionAnsweredId, String dateTimeAnswered){
        this.tutorAnsweredId = tutorAnsweredId; 
        this.answer = answer; 
        this.questionAnsweredId = questionAnsweredId; 
        this.dateTimeAnswered = dateTimeAnswered; 
        this.isEnabled = true;
    }

    public int getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getTutorAnsweredId() {
        return this.tutorAnsweredId;
    }

    public void setTutorAnsweredId(int tutorAnsweredId) {
        this.tutorAnsweredId = tutorAnsweredId;
    }


    public int getQuestionAnsweredId() {
        return this.questionAnsweredId;
    }

    public void setQuestionAnsweredId(int questionAnsweredId) {
        this.questionAnsweredId = questionAnsweredId;
    }

    public String getDateTimeAnswered() {
        return this.dateTimeAnswered;
    }

    public void setDateTimeAnswered(String dateTimeAnswered) {
        this.dateTimeAnswered = dateTimeAnswered;
    }
    public boolean isIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
