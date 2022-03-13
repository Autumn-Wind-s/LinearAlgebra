package it.domain;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Answer {
    private int aid;
    private User user;
    private Question question;
    private String text;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp publishtime;


    public Timestamp getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Timestamp publishtime) {
        this.publishtime = publishtime;
    }

    public Answer(int aid, User user, Question question, String text, Timestamp publishtime) {
        this.aid = aid;
        this.user = user;
        this.question = question;
        this.text = text;
        this.publishtime = publishtime;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "aid=" + aid +
                ", user=" + user +
                ", question=" + question +
                ", text='" + text + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Answer() {
    }


}
