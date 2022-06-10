package com.largeproject.figmadesigns.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.largeproject.figmadesigns.R;
import com.largeproject.figmadesigns.databinding.FragmentLoginBinding;

import io.reactivex.annotations.NonNull;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        login();
        forgotPassword();
        return view;
    }



    public void login() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(binding.edtEmail.getText());
                String password = String.valueOf(binding.edtPassword.getText());
                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Login", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment);
                            } else {
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                MainActivity.makeToast("Authentication failed.",getActivity().getApplicationContext());
                            }
                        }
                    });
                } else {
                    MainActivity.makeToast("Email or Password is empty",getActivity().getApplicationContext());

                }
            }
        });
    }

    public void forgotPassword() {
        binding.linkForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgetPasswordFragment);
            }
        });
    }

}