package com.largeproject.figmadesigns.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.largeproject.figmadesigns.R;
import com.largeproject.figmadesigns.databinding.FragmentForgotBinding;

import java.util.Objects;

import io.reactivex.annotations.NonNull;

public class ForgetPasswordFragment extends Fragment {
    private FragmentForgotBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        forgotPassword();
        alreadyHavAccount();
        return view;
    }

    public void forgotPassword() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(binding.edtEmail.getText());
                if (!email.isEmpty()) {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                binding.edtEmail.setText(null);
                                Log.d("forgotPassword", "Email sent.");
                                MainActivity.makeToast("Email sent", requireActivity().getApplicationContext());
                            } else {
                                Log.d("forgotPassword", "Email not found.");
                                MainActivity.makeToast("Email not found", requireActivity().getApplicationContext());
                            }
                        }
                    });
                }else {
                    MainActivity.makeToast("Email is empty", requireActivity().getApplicationContext());
                }
            }
        });
    }
    public void alreadyHavAccount(){
        binding.txtOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_forgetPasswordFragment_to_loginFragment);
            }
        });
    }
}
