package com.example.edufree;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText userName;
    EditText password;
    Button confirm;
    FirebaseAuth mAuth;
    String userNameStr, passwordStr;

    View.OnClickListener toHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userNameStr = userName.getText().toString();
            passwordStr = password.getText().toString();
            mAuth.signInWithEmailAndPassword(userNameStr, passwordStr)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(requireActivity(), "Login success, redirecting to home", Toast.LENGTH_SHORT).show();

                                Intent toHome = new Intent(requireActivity(), HomeActivity.class);
                                startActivity(toHome);
                            }else{
                                Toast.makeText(requireActivity(), "Register unsucessfull due to exception:\n" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        userName = view.findViewById(R.id.fragment_login_username_et);
        password = view.findViewById(R.id.fragment_login_password_et);
        confirm = view.findViewById(R.id.fragment_login_login_bt);

        confirm.setOnClickListener(toHome);
    }
}