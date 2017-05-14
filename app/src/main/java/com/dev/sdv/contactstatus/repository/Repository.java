package com.dev.sdv.contactstatus.repository;

import java.util.List;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;

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
    }
}
