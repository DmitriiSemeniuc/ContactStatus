package com.dmitrii.semeniuc.contactstatus.db;

import android.util.Log;

import com.dmitrii.semeniuc.contactstatus.main.contacts.MainContactsInteractor;
import com.dmitrii.semeniuc.contactstatus.models.Contact;
import com.dmitrii.semeniuc.contactstatus.models.Status;
import com.dmitrii.semeniuc.contactstatus.models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FireBaseDbHelper implements DbHelper.UserCRUD, DbHelper.StatusCRUD, DbHelper.ContactCRUD {

    public static final String TAG = FireBaseDbHelper.class.getSimpleName();

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
            ref = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUS_UPDATES);
            ref.child(status.getUid()).child("updated").setValue(ref.push().getKey());
            if(listener != null) listener.onStatusChangeSuccess();
        } catch (Exception ex) {
            if(listener != null) listener.onStatusChangeFailed(ex.getMessage());
        }
    }

    @Override
    public void updateStatus(Status status, DbHelper.OnStatusChangeListener listener) {
        saveStatus(status, listener);
    }

    @Override
    public void deleteStatus(Status status, DbHelper.OnStatusChangeListener listener) {
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

    ChildEventListener statusChangeListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

            // A new comment has been added, add it to the displayed list
            //Comment comment = dataSnapshot.getValue(Comment.class);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            //Comment newComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            //Comment movedComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            //Toast.makeText(mContext, "Failed to load comments.",
                    //Toast.LENGTH_SHORT).show();
        }
    };

    public void registerStatusChangeListener(String uid, MainContactsInteractor listener){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUS_UPDATES);
        Query statusQuery = ref.orderByChild(uid).equalTo(true);
        statusQuery.addChildEventListener(statusChangeListener);
    }

    @Override public void saveContact(Contact contact, DbHelper.OnContactChangeListener listener) {

    }

    @Override
    public void updateContact(Contact contact, DbHelper.OnContactChangeListener listener) {

    }

    @Override
    public void deleteContact(Contact contact, DbHelper.OnContactChangeListener listener) {

    }

    @Override public void getContactById(String uid, DbHelper.OnContactRetrieveListener listener) {
        DatabaseReference ref = getDbRef().child(DbHelper.FirebaseReference.CONTACTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                Contact contact = dataSnapshot.child(uid).getValue(Contact.class);
                listener.onContactRetrieveSuccess(contact);
            }

            @Override public void onCancelled(DatabaseError databaseError) {
                listener.onContactRetrieveFailed(databaseError.getMessage());
            }
        });
    }

    @Override public void getAllContacts(String uid, DbHelper.OnContactRetrieveListener listener) {

    }
}
