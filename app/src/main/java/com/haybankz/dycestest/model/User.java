package com.haybankz.dycestest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(indices = {@Index(value = {"id", "user_name"}, unique = true)})
public class User implements Serializable {


    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "wallet_balance")
    private int walletBalance;

    @Ignore
    public User(int id, String userName, String password, String name, int walletBalance) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.walletBalance = walletBalance;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", walletBalance=" + walletBalance +
                '}';
    }


}
