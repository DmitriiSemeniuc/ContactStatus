package com.dmitrii.semeniuc.contactstatus.repository;

import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.db.FireBaseDbHelper;
import com.dmitrii.semeniuc.contactstatus.models.User;

import java.util.List;

public class UserRepository implements Repository.UserCRUD {

    private FireBaseDbHelper dbHelper;

    public UserRepository(FireBaseDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override public void save(User user, DbHelper.OnUserChangeListener listener) {
        dbHelper.saveUser(user, listener);
    }

    @Override public int update(Object item) {
        return 0;
    }

    @Override public int delete(Object item) {
        return 0;
    }

    @Override public Object findById(int id) {
        return null;
    }

    @Override public List<?> findAll() {
        return null;
    }
}
