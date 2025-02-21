package com.example.foodplanner.auth.presenter;

import com.example.foodplanner.auth.view.authInterface;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPresenter {
    private FirebaseAuth auth;
    private authInterface signupInterface;

    public SignupPresenter(authInterface signupInterface){
        this.signupInterface = signupInterface;
        auth = FirebaseAuth.getInstance();
    }

    public void signUp(String email,String password){
      if(email.isEmpty()){
          signupInterface.wrongEmailInput();
          return;
      } else if(password.isEmpty()){
          signupInterface.wrongPasswordInput();
          return;
      } else {
          auth.createUserWithEmailAndPassword(email, password)
                  .addOnCompleteListener(task -> {
                      if(task.isSuccessful()){
                          signupInterface.onAuthSuccess();
                      }else{
                          signupInterface.onAuthFailure(task.getException().getMessage());
                      }
                  });
      }
    }
}
