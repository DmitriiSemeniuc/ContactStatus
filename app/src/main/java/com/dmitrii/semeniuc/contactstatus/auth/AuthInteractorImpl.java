package com.dmitrii.semeniuc.contactstatus.auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dmitrii.semeniuc.contactstatus.App;
import com.dmitrii.semeniuc.contactstatus.R;
import com.dmitrii.semeniuc.contactstatus.base.Authentication;
import com.dmitrii.semeniuc.contactstatus.base.GoogleAuthenticator;
import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.models.User;
import com.dmitrii.semeniuc.contactstatus.repository.UserRepository;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import javax.inject.Inject;

public class AuthInteractorImpl implements AuthInteractor {

    private static final String TAG = AuthInteractorImpl.class.getSimpleName();
    private Context context;
    @Inject User user;
    @Inject UserRepository userRepository;

    AuthInteractorImpl(Context context) {
        App.getInstance().getComponent().inject(this);
        this.context = context;
    }

    @Override public void initAuth(Context context, FragmentActivity fragmentActivity) {
        App.getAuth().setGoogleApiClient(new GoogleAuthenticator(context, fragmentActivity).getApiClient());
        App.getAuth().setFirebaseAuth(FirebaseAuth.getInstance());
    }

    @Override public void onSignInWithGoogle(Intent data, OnGoogleLoginListener listener) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            listener.onGoogleLoginSuccess(result.getSignInAccount());
        } else {
            // Google Sign In failed, update UI appropriately
            listener.onGoogleLoginFailed(context.getString(R.string.auth_failed));
        }
    }

    @Override public void firebaseAuthWithGoogle(GoogleSignInAccount acct, final OnLoginListener listener) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        App.getAuth().getFirebaseAuth().signInWithCredential(credential)
                .addOnCompleteListener((AuthActivity) context, task -> {
                    if (task.isSuccessful()) {
                        App.getAuth().setUserType(Authentication.UserType.GOOGLE.toString(), context);
                        listener.onLoginSuccess();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Exception ex = task.getException();
                        if (ex != null) listener.onLoginFailed(ex.getMessage());
                    }
                });
    }

    @Override public void createAccount(String email, String password, final OnLoginListener listener) {
        Task<AuthResult> authResultTask = App.getAuth().getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((AuthActivity) context, new OnCompleteListener<AuthResult>() {
                    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
                    @Override public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            App.getAuth().setUserType(Authentication.UserType.EMAIL.toString(), context);
                            listener.onLoginSuccess();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Exception ex = task.getException();
                            if (ex != null) listener.onLoginFailed(ex.getMessage());
                        }
                    }
                });
    }

    @Override public void loginWithEmail(String email, String password, final OnLoginListener listener) {
        App.getAuth().getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((AuthActivity) context, task -> {
                    if (task.isSuccessful()) {
                        App.getAuth().setUserType(Authentication.UserType.EMAIL.toString(), context);
                        listener.onLoginSuccess();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "loginWithEmail:failure", task.getException());
                        Exception ex = task.getException();
                        if (ex != null) listener.onLoginFailed(ex.getMessage());
                    }
                });
    }

    @Override public void verifyIfUserSignedIn(OnLoginListener listener) {
        if (App.getAuth().getFirebaseAuth().getCurrentUser() != null) {
            listener.onLoginSuccess();
        }
    }

    @Override public void setCurrentUser() {
        FirebaseUser currentUser = App.getAuth().getFirebaseAuth().getCurrentUser();
        if (currentUser != null) {
            String displayName = currentUser.getDisplayName();
            Uri profileUri = currentUser.getPhotoUrl();

            for (UserInfo userInfo : currentUser.getProviderData()) {
                if (displayName == null && userInfo.getDisplayName() != null) {
                    displayName = userInfo.getDisplayName();
                }
                if (profileUri == null && userInfo.getPhotoUrl() != null) {
                    profileUri = userInfo.getPhotoUrl();
                }
            }
            if(user != null){
                user.setUid(currentUser.getUid());
                user.setName(displayName);
                user.setEmail(currentUser.getEmail());
                if(profileUri != null){
                    user.setPhotoUrl(profileUri.toString());
                }
            }
        }
    }

    @Override public void createUser(DbHelper.OnUserChangeListener listener) {
        userRepository.save(user, listener);
    }
}
