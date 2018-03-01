package com.example.gustavo.lista;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Gustavo on 28/02/2018.
 */

public interface UserDAO {
    @Query("SELECT * FROM users WHERE codigo=:userId")
    Flowable<ModeloLlenar> getUserById(int userId);

    @Query("SELECT* FROM users")
    Flowable<List<ModeloLlenar>>getAllUsers();

    @Insert
    void insertUser(ModeloLlenar ... users);

    @Update
    void  updateUser(ModeloLlenar ... users);

    @Delete
    void deleteUser(ModeloLlenar user);

    @Query("DELETE FROM users")
    void deleteAllUsers();



}
