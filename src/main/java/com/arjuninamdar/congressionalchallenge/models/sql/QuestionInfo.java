package com.arjuninamdar.congressionalchallenge.models.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION_INFO")
public class QuestionInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUESTION_ID")
    private int questionId; 

    @Column(name="USER_ASKED_ID")
    private int userAskedId; 

    @Column(name="QUESTION")
    private String question; 

    @Column(name="IMAGE_PATH")
    private String imagePath;

    @Column(name="DATE_TIME_POSTED")
    private String dateTimePosted; 

    @Column(name="IS_ENABLED")
    private boolean isEnabled; 

    public QuestionInfo() {

    }
    

    public QuestionInfo(int userAskedId, String question, String imagePath, String dateTimePosted, boolean isEnabled) {
        this.userAskedId = userAskedId; 
        this.question = question; 
        this.imagePath = imagePath; 
        this.dateTimePosted = dateTimePosted; 
        this.isEnabled = isEnabled; 
    }


    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserAskedId() {
        return this.userAskedId;
    }

    public void setUserAskedId(int userAskedId) {
        this.userAskedId = userAskedId;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDateTimePosted() {
        return this.dateTimePosted;
    }

    public void setDateTimePosted(String dateTimePosted) {
        this.dateTimePosted = dateTimePosted;
    }

    public boolean getIsEnabled() {
        return isEnabled; 
    }

    public void setIsEnabled(boolean isEnabled) { 
        this.isEnabled = isEnabled; 
    }

    
}
