package com.dmitrii.semeniuc.contactstatus.db;

import com.dmitrii.semeniuc.contactstatus.models.Contact;
import java.util.List;

import com.dmitrii.semeniuc.contactstatus.models.Status;
import com.dmitrii.semeniuc.contactstatus.models.User;
import com.google.firebase.database.DatabaseError;

public interface DbHelper {

    interface UserCRUD {

        void saveUser(User user, OnUserChangeListener listener);

        boolean updateUser(User user, OnUserChangeListener listener);

        boolean deleteUser(User user, OnUserChangeListener listener);

        User findUserById(String id);

        List<User> findAll();
    }

    interface StatusCRUD {

        void saveStatus(Status status, OnStatusChangeListener listener);

        void updateStatus(Status status, OnStatusChangeListener listener);

        void deleteStatus(Status status, OnStatusChangeListener listener);

        void getStatusById(String uid, OnStatusRetrievedListener listener);
    }

    interface ContactCRUD {

        void saveContact(Contact contact, OnContactChangeListener listener);

        void updateContact(Contact contact, OnContactChangeListener listener);

        void deleteContact(Contact contact, OnContactChangeListener listener);

        void getContactById(String uid, OnContactRetrieveListener listener);

        void getAllContacts(String uid, OnContactRetrieveListener listener);
    }

    interface OnUserChangeListener {

        void onUserChangeSuccess();

        void onUserChangeFailed(String error);
    }

    interface OnStatusChangeListener {

        void onStatusChangeSuccess();

        void onStatusChangeFailed(String error);
    }

    interface OnStatusRetrievedListener {

        void onStatusRetrieveSuccess(Status status);

        void onStatusRetrieveFailed(String error);
    }

    interface OnContactChangeListener {

        void onContactChangeSuccess();

        void onContactChangeFailed();
    }

    interface OnContactRetrieveListener {

        void onContactsRetrieveSuccess(List<Contact> contacts);

        void onContactRetrieveFailed(String error);

        void onContactRetrieveSuccess(Contact contact);
    }

    interface OnChildExistsListener{

        void onChildExists();

        void onChildNotExists();

        void onCanceled(DatabaseError databaseError);
    }

    class FirebaseReference{

        public static final String USERS = "users";
        public static final String STATUSES = "statuses";
        public static final String STATUS_UPDATES = "statusUpdates";
        public static final String CONTACTS = "contacts";
    }
}
