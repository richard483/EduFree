package com.example.edufree;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WelcomeFragment extends Fragment {

    Button loginButton;
    View.OnClickListener toHomeMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toHome = new Intent(requireActivity(), HomeActivity.class);
            startActivity(toHome);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = view.findViewById(R.id.fragment_welcome_login_bt);
        loginButton.setOnClickListener(toHomeMenu);
    }
}