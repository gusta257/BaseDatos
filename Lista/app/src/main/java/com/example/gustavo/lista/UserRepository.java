package com.example.gustavo.lista;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Gustavo on 1/03/2018.
 */

public class UserRepository implements IUserDataSource {

    public IUserDataSource mLocalDataSource;
    private static UserRepository mInstance;

    public UserRepository(IUserDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static UserRepository getInstance(IUserDataSource mLocalDataSource){
        if(mInstance == null){
            mInstance = new UserRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<ModeloLlenar> getUserById(int userId) {
        return mLocalDataSource.getUserById(userId);
    }

    @Override
    public Flowable<List<ModeloLlenar>> getAllUsers() {
        return mLocalDataSource.getAllUsers();
    }

    @Override
    public void insertUser(ModeloLlenar... users) {
        mLocalDataSource.insertUser(users);
    }

    @Override
    public void updateUser(ModeloLlenar... users) {
        mLocalDataSource.updateUser(users);
    }

    @Override
    public void deleteUser(ModeloLlenar users) {
        mLocalDataSource.deleteUser(users);
    }

    @Override
    public void deleteAllUsers() {
        mLocalDataSource.deleteAllUsers();
    }
}
