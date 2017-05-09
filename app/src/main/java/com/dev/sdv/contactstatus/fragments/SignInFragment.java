package com.dev.sdv.contactstatus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.dev.sdv.contactstatus.R;
import com.dev.sdv.contactstatus.auth.AuthActivity;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignInFragment extends Fragment {

    public static final String FRAGMENT_TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.sign_in_email_et)
    EditText signInEmailEditText;
    @BindView(R.id.sign_in_password_et)
    EditText signInPasswordEditText;
    @BindView(R.id.sign_in_confirm_password_et)
    EditText signInConfirmPasswordEditText;
    @BindView(R.id.sign_in_btn)
    AppCompatButton signInBtn;
    @BindView(R.id.sign_in_login_rl)
    RelativeLayout loginLinkRl;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.sign_in_btn)
    public void signIn() {
        if (validateForm()) {
            ((AuthActivity) getActivity()).createAccount(signInEmailEditText.getText().toString(), signInPasswordEditText.getText().toString());
        }
    }

    @OnClick(R.id.sign_in_login_rl)
    public void goToLoginScreen() {
        ((AuthActivity) getActivity()).displayLoginForm();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = signInEmailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            signInEmailEditText.setError(getString(R.string.field_required));
            valid = false;
        } else {
            signInEmailEditText.setError(null);
        }

        String password = signInPasswordEditText.getText().toString();
        String confirmPassword = signInConfirmPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            signInPasswordEditText.setError(getString(R.string.field_required));
            valid = false;
        } else if(TextUtils.isEmpty(confirmPassword)){
            signInConfirmPasswordEditText.setError(getString(R.string.field_required));
            valid = false;
        } else if(!password.equals(confirmPassword)) {
            signInConfirmPasswordEditText.setError(getString(R.string.passwords_do_not_match));
            valid = false;
        } else {
            signInPasswordEditText.setError(null);
            signInConfirmPasswordEditText.setError(null);
        }

        return valid;
    }
}
