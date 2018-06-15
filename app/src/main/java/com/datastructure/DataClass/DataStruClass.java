package com.datastructure.DataClass;

import java.util.List;

public class DataStruClass {


    private boolean status;
    private String question;
    private String answer;

    /**
     * No args constructor for use in serialization
     */
    public DataStruClass() {
    }

    /**
     * @param answer
     * @param question
     */
    public DataStruClass(String question, String answer, boolean status) {
        super();
        this.status = status;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}