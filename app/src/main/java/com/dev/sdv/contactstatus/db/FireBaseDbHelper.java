package com.dev.sdv.contactstatus.db;

import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseDbHelper implements DbHelper.UserCRUD, DbHelper.StatusCRUD {

    private boolean isChildExists(String child, String childId, DbHelper.OnChildExistsListener listener) {
        DatabaseReference ref = getDbRef().child(child);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(childId)) {
                    listener.onChildExists();
                } else {
                    listener.onChildNotExists();
                }
            }

            @Override public void onCancelled(DatabaseError databaseError) {
                listener.onCanceled(databaseError);
            }
        });
        return false;
    }

    @Override public void saveUser(User user, DbHelper.OnUserChangeListener listener) {
        isChildExists(DbHelper.FirebaseReference.USERS, user.getUid(), new DbHelper.OnChildExistsListener() {
            @Override public void onChildExists() {
                listener.onUserChangeSuccess();
            }

            @Override public void onChildNotExists() {
                User dbUser = new User(user.getUid(), user.getName(), user.getEmail(), user.getPhotoUrl(), user.getPhoneNumber());
                getDbRef().child(DbHelper.FirebaseReference.USERS).child(user.getUid()).setValue(dbUser);
                listener.onUserChangeSuccess();
            }

            @Override public void onCanceled(DatabaseError databaseError) {
                listener.onUserChangeFailed(databaseError.getMessage());
            }
        });
    }

    @Override public boolean updateUser(User user, DbHelper.OnUserChangeListener listener) {
        return false;
    }

    @Override public boolean deleteUser(User user, DbHelper.OnUserChangeListener listener) {
        return false;
    }

    @Override public User findUserById(String id) {
        return null;
    }

    @Override public List<User> findAll() {
        return null;
    }

    DatabaseReference getDbRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    DatabaseReference getDbRef(String child) {
        return FirebaseDatabase.getInstance().getReference(child);
    }

    @Override
    public void saveStatus(Status status, DbHelper.OnStatusChangeListener listener) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUSES);
            ref.child(status.getUid()).setValue(status.toMap());
            listener.onStatusChangeSuccess();
        } catch (Exception ex) {
            listener.onStatusChangeFailed(ex.getMessage());
        }
    }

    @Override
    public boolean updateStatusByUserId(Status status, DbHelper.OnStatusChangeListener listener) {
        return false;
    }

    @Override
    public boolean deleteStatusByUserId(Status status, DbHelper.OnStatusChangeListener listener) {
        return false;
    }

    @Override public void getStatusById(String uid, DbHelper.OnStatusRetrievedListener listener) {
        DatabaseReference ref = getDbRef().child(DbHelper.FirebaseReference.STATUSES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                Status status = dataSnapshot.child(uid).getValue(Status.class);
                listener.onStatusRetrieveSuccess(status);
            }

            @Override public void onCancelled(DatabaseError databaseError) {
                listener.onStatusRetrieveFailed(databaseError.getMessage());
            }
        });
    }
}
