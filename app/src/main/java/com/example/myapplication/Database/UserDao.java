package com.example.myapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("select * from user")
    List<User> getAllUser();

//    @Delete
//    void deleteUser(User user);

    @Query("delete  from user where id=:userId")
    void deleteUser(long userId);
}
