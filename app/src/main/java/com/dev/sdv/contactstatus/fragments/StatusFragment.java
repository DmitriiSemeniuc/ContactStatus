package com.dev.sdv.contactstatus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.sdv.contactstatus.R;

public class StatusFragment extends Fragment {

    private SwitchCompat autoChangeStatusSwitch;

    public StatusFragment() {
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoChangeStatusSwitch = (SwitchCompat) view.findViewById(R.id.auto_change_status_switch);
        autoChangeStatusSwitch.setChecked(true);
        autoChangeStatusSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getActivity(), "Auto change status ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Auto change status OFF", Toast.LENGTH_SHORT).show();
            }
        });
        //check the current state before we display the screen
        if (autoChangeStatusSwitch.isChecked()) {
            Toast.makeText(getActivity(), "Auto change status ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Auto change status OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
