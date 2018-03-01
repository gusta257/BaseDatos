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

public interface IUserDataSource {

    Flowable<ModeloLlenar> getUserById(int userId);

    Flowable<List<ModeloLlenar>>getAllUsers();

    void insertUser(ModeloLlenar ... users);

    void  updateUser(ModeloLlenar ... users);

    void deleteUser(ModeloLlenar user);

    void deleteAllUsers();
}
