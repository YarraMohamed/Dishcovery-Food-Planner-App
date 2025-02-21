package com.example.foodplanner.auth.presenter;

import com.example.foodplanner.auth.view.SignupInterface;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPresenter {
    private FirebaseAuth auth;
    private SignupInterface signupInterface;

    public SignupPresenter(SignupInterface signupInterface){
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
                  .addOnCompleteListener(command -> {
                      if(command.isSuccessful()){
                          signupInterface.onSignupSuccess();
                      }else{
                          signupInterface.onSignupFailure(command.getException().getMessage());
                      }
                  });
      }
    }
}
