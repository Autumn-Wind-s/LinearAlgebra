package it.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Question {
    private int qid;
    private String questionName;
    private User user;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp uploadTime;
    private  String text;
    private List<Answer> answers;

    @Override
    public String toString() {
        return "Question{" +
                "qid=" + qid +
                ", questionName='" + questionName + '\'' +
                ", user=" + user +
                ", date=" + uploadTime +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {

        this.uploadTime = uploadTime;
    }

    public Question(int qid, String questionName, User user, Timestamp uploadTime, String text, List<Answer> answers) {
        this.qid = qid;
        this.questionName = questionName;
        this.user = user;
        this.uploadTime = uploadTime;
        this.text = text;
        this.answers = answers;
    }
}
