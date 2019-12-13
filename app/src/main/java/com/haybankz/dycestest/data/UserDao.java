package com.haybankz.dycestest.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.haybankz.dycestest.model.User;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {



    //gets a list of all customers in the db
    @Query("SELECT * FROM user where  user_name = :userName and password = :password")
    User signIn(String userName, String password);

    @Update(onConflict = IGNORE)
    void updateUser(User user);



}
