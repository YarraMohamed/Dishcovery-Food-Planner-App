package com.example.foodplanner.auth.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.auth.presenter.SignupPresenter;

public class SignupFragment extends Fragment implements authInterface {

    private TextView logintxt;
    private EditText email, password;
    private Button signUpBtn;
    private SignupPresenter signupPresenter;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        signupPresenter = new SignupPresenter(this);

        logintxt = view.findViewById(R.id.logintxt);
        logintxt.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
        });

        signUpBtn.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            signupPresenter.signUp(userEmail,userPassword);
        });
    }

    @Override
    public void onAuthSuccess() {
        Toast.makeText(getContext(),"Signed Successfully",Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_homeFragment);
    }

    @Override
    public void onAuthFailure(String errMsg) {
        Toast.makeText(getContext(),errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wrongEmailInput() {
        email.setError("Email cannot be Empty");
    }

    @Override
    public void wrongPasswordInput() {
        password.setError("Password cannot be Empty");
    }
}