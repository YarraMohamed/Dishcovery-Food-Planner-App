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
import com.example.foodplanner.auth.presenter.LoginPresenter;

public class LoginFragment extends Fragment implements authInterface{

    TextView signUpTxt;
    Button Loginbtn;
    EditText emailtxt,passwordTxt;
    LoginPresenter loginPresenter;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailtxt = view.findViewById(R.id.emailtxt);
        passwordTxt = view.findViewById(R.id.passwordTxt);
        loginPresenter = new LoginPresenter(this);

        signUpTxt = view.findViewById(R.id.signUpTxt);
        signUpTxt.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
        });

        Loginbtn = view.findViewById(R.id.Loginbtn);
        Loginbtn.setOnClickListener(v -> {
            String email = emailtxt.getText().toString().trim();
            String password = passwordTxt.getText().toString().trim();
            loginPresenter.Login(email,password);

        });
    }

    @Override
    public void onAuthSuccess() {
        Toast.makeText(getContext(),"Logged in Successfully",Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onAuthFailure(String errMsg) {
        Toast.makeText(getContext(),errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wrongEmailInput() {
        emailtxt.setError("Wrong email input");
    }

    @Override
    public void wrongPasswordInput() {
        emailtxt.setError("Wrong password input");
    }
}