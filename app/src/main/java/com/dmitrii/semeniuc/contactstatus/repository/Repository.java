package com.dmitrii.semeniuc.contactstatus.repository;

import java.util.List;

import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.models.Status;
import com.dmitrii.semeniuc.contactstatus.models.User;

interface Repository {

    interface UserCRUD{

        void save(User user, DbHelper.OnUserChangeListener listener);

        int update(Object item);

        int delete(Object item);

        Object findById(int id);

        List<?> findAll();
    }

    public interface StatusCRUD{

        void saveStatus(Status status, DbHelper.OnStatusChangeListener listener);

        void getStatusById(String statusId, DbHelper.OnStatusRetrievedListener listener);

        void registerOnStatusChangeListener(String uid);
    }
}
