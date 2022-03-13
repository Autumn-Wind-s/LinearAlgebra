package it.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("user")
public class User {
    private  int uid;
    private  String username;
    private  String password;
    private String status;
    private  String email;
    private List<Algorithm> algorithms;
    private  List<Question> questions;
    private  String permissions;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", algorithms=" + algorithms +
                ", questions=" + questions +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Algorithm> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public User() {
    }

    public User(int uid, String username, String password, String status, String email, List<Algorithm> algorithms, List<Question> questions, String permissions) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.algorithms = algorithms;
        this.questions = questions;
        this.permissions = permissions;
    }
}
