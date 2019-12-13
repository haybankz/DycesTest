package com.haybankz.dycestest.data;

import android.content.Context;

import com.haybankz.dycestest.model.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Context context){
        userDao = AppDatabase.getInstance(context).userDao();
    }

    public User signIn(String username, String password){
        return userDao.signIn(username, password);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }
}
