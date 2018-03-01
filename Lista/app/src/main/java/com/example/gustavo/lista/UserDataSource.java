package com.example.gustavo.lista;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Gustavo on 28/02/2018.
 */

public class UserDataSource implements IUserDataSource {

    private UserDAO userDAO;

    public UserDataSource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }



    @Override
    public Flowable<ModeloLlenar> getUserById(int userId) {
        return null;
    }

    @Override
    public Flowable<List<ModeloLlenar>> getAllUsers() {
        return null;
    }

    @Override
    public void insertUser(ModeloLlenar... users) {

    }

    @Override
    public void updateUser(ModeloLlenar... users) {

    }

    @Override
    public void deleteUser(ModeloLlenar... users) {

    }

    @Override
    public void deleteAllUsers() {

    }
}
