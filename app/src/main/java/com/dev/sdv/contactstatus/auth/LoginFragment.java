package com.dev.sdv.contactstatus.auth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dev.sdv.contactstatus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends Fragment implements AuthView.LoginView{

    public static final String FRAGMENT_TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.login_email_et)
    EditText loginEmailEditText;
    @BindView(R.id.login_password_et)
    EditText loginPasswordEditText;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login_btn)
    @Override public void loginWithEmail() {
        if (validateForm()) {
            ((AuthActivity) getActivity()).loginWithEmail(loginEmailEditText.getText().toString(), loginPasswordEditText.getText().toString());
        }
    }

    @OnClick(R.id.login_with_google_btn)
    @Override public void loginWithGoogle() {
        ((AuthActivity) getActivity()).signInWithGoogle();
    }

    @OnClick(R.id.login_sign_in_rl)
    @Override public void goToSignInScreen() {
        ((AuthActivity) getActivity()).displaySignInForm();
    }

    @Override public boolean validateForm() {
        boolean valid = true;

        String email = loginEmailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            loginEmailEditText.setError(getString(R.string.field_required));
            valid = false;
        } else {
            loginEmailEditText.setError(null);
        }

        String password = loginPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            loginPasswordEditText.setError(getString(R.string.field_required));
            valid = false;
        } else {
            loginPasswordEditText.setError(null);
        }

        return valid;
    }
}
