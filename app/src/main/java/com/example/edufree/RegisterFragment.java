package com.example.edufree;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText userName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText createPassword;
    EditText reinputPassword;
    Button confirmButton;
    String userNameStr, phoneNumberStr, emailAddressStr, createPasswordStr, reinputPasswordStr;
    String alert = "";

    View.OnClickListener doRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userNameStr = userName.getText().toString();
            phoneNumberStr = phoneNumber.getText().toString();
            emailAddressStr = emailAddress.getText().toString();
            createPasswordStr = createPassword.getText().toString();
            reinputPasswordStr = reinputPassword.getText().toString();

            if(validator(userNameStr, createPasswordStr, reinputPasswordStr, emailAddressStr)){
                mAuth.createUserWithEmailAndPassword(emailAddressStr, createPasswordStr)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    UserProfileChangeRequest insertName = new UserProfileChangeRequest.Builder().setDisplayName(userNameStr).build();

                                    task.getResult().getUser().updateProfile(insertName);
                                    Toast.makeText(requireActivity(), "Register success, returning to Login Page", Toast.LENGTH_SHORT).show();
                                    getParentFragmentManager().popBackStack();
                                }else{
                                    Toast.makeText(requireActivity(), "Register Error due to exception: \n" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(requireActivity(), alert, Toast.LENGTH_SHORT).show();
                alert = "";
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        userName = view.findViewById(R.id.fragment_register_username_et);
        phoneNumber = view.findViewById(R.id.fragment_register_phone_et);
        emailAddress = view.findViewById(R.id.fragment_register_email_et);
        createPassword = view.findViewById(R.id.fragment_register_password_et);
        reinputPassword = view.findViewById(R.id.fragment_register_repassword_et);
        confirmButton = view.findViewById(R.id.fragment_register_register_bt);

        confirmButton.setOnClickListener(doRegister);
    }

        public boolean validator(String name, String password, String repassword, String email){
        if(!name.isEmpty()){
            if(name.length()>=5){
                if(!email.isEmpty()){
                    if(email.contains("@")){
                        if(email.endsWith(".com")){
                            if((!password.isEmpty())&&(!repassword.isEmpty())){
                                if(password.equals(repassword)){
                                    return true;
                                }else {
                                    alert += "Password and Reenter password must be same\n";
                                }
                            }else {
                                alert+="Password and Re-enter password cannot be empty!\n";
                            }
                        }else{
                            alert += "Email must ends with '.com'\n";
                        }
                    }else{
                        alert += "Email must contain '@' character\n";
                    }
                }else{
                    alert+="Email cannot be empty!\n";
                }
            }else {
                alert += "Name must be at least 5 characters\n";
            }
        }else {
            alert+="Name cannot be empty!\n";
        }
        return  false;
    }
}