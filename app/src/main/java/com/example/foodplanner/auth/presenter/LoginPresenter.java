package com.example.foodplanner.auth.presenter;

import com.example.foodplanner.auth.view.authInterface;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginPresenter {
    private FirebaseAuth auth;
    private authInterface loginInterface;

    public LoginPresenter(authInterface loginInterface){
        this.loginInterface=loginInterface;
        this.auth = FirebaseAuth.getInstance();
    }

    public void Login(String email,String password){
        if(email.isEmpty()){
            loginInterface.wrongEmailInput();
        }else if(password.isEmpty()) {
            loginInterface.wrongPasswordInput();
        }else{
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                       if(task.isSuccessful()) {
                           loginInterface.onAuthSuccess();
                       }else{
                           loginInterface.onAuthFailure(task.getException().getMessage());
                       }
                    });
        }
    }
}
