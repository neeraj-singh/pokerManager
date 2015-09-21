package com.mobilesaaj.pokergame.Players;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class Player {
    private String name;
    private String emailId;
    private String mobile ;
    private String buyin;
    private int winningAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBuyin() {
        return buyin;
    }

    public void setBuyin(String buyin) {
        this.buyin = buyin;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(int winningAmount) {
        this.winningAmount = winningAmount;
    }
}
