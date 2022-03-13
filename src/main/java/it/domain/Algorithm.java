package it.domain;

public class Algorithm {
    private int aid;
    private  User user;
    private String algorithmName;
    private  String text;

    @Override
    public String toString() {
        return "Algorithm{" +
                "aid=" + aid +
                ", user=" + user +
                ", algorithmName='" + algorithmName + '\'' +
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



    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Algorithm() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Algorithm(int aid, User user, String algorithmName, String text) {
        this.aid = aid;

        this.algorithmName = algorithmName;
        this.text = text;
    }
}
