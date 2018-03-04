package com.example.gustavo.lista;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Gustavo on 28/02/2018.
 */

public class UserDataSource implements IUserDataSource {
    private UserDAO userDAO;
    private static UserDataSource mInstance;

    public UserDataSource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserDataSource getInstance(UserDAO userDAO){
        if (mInstance == null){
            mInstance = new UserDataSource(userDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<ModeloLlenar>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void insertUser(ModeloLlenar... users) {
        userDAO.insertUser(users);
    }

    @Override
    public void updateUser(ModeloLlenar... users) {
        userDAO.updateUser(users);
    }

    @Override
    public void deleteUser(ModeloLlenar  user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void deleteAllUsers() {
        userDAO.deleteAllUsers();
    }
}

