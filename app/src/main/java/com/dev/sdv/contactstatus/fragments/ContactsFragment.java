package com.dev.sdv.contactstatus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.sdv.contactstatus.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends Fragment {

    private Unbinder unbinder;

    public ContactsFragment(){
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) unbinder.unbind();
    }
}
