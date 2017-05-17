package com.dev.sdv.contactstatus.main.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.R;
import com.dev.sdv.contactstatus.main.status.MainStatusPresenter;
import com.dev.sdv.contactstatus.main.status.MainStatusPresenterImpl;
import com.dev.sdv.contactstatus.main.status.MainStatusView;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;
import com.dev.sdv.contactstatus.utils.PrefsImpl;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends Fragment implements MainContactsView {

    private Unbinder unbinder;
    private MainContactsPresenter presenter;

    @Inject User user;
    @Inject Status status;
    @Inject PrefsImpl prefs;

    public ContactsFragment(){
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new MainContactsPresenterImpl(this, getContext()));
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.registerOnStatusChangeListener(user.getUid());
        return view;
    }

    @Override public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) unbinder.unbind();
    }

    @Override public void setPresenter(MainContactsPresenter presenter) {
        this.presenter = presenter;
    }
}
