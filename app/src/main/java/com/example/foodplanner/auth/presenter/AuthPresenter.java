package com.example.foodplanner.auth.presenter;

import android.content.SharedPreferences;

import com.example.foodplanner.auth.view.AuthInterface;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthPresenter {
    private FirebaseAuth auth;
    private AuthInterface authInterface;
    private SharedPreferences sharedPreferences;

    public AuthPresenter(AuthInterface authInterface,SharedPreferences sharedPreferences){
        this.auth=FirebaseAuth.getInstance();
        this.authInterface=authInterface;
        this.sharedPreferences = sharedPreferences;
    }

    public void Login(String email,String password){
        if(email.isEmpty()){
            authInterface.wrongEmailInput();
        }else if(password.isEmpty()) {
            authInterface.wrongPasswordInput();
        }else{
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userID",task.getResult().getUser().getUid());
                            editor.commit();
                            authInterface.onAuthSuccess();
                        }else{
                            authInterface.onAuthFailure(task.getException().getMessage());
                        }
                    });
        }
    }

    public void signUp(String email,String password){
        if(email.isEmpty()){
            authInterface.wrongEmailInput();
            return;
        } else if(password.isEmpty()){
            authInterface.wrongPasswordInput();
            return;
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userID",task.getResult().getUser().getUid());
                            editor.commit();
                            authInterface.onAuthSuccess();
                        }else{
                            authInterface.onAuthFailure(task.getException().getMessage());
                        }
                    });
        }
    }

    public void signInWithGoogle(Task<GoogleSignInAccount> task){
        try{
            GoogleSignInAccount account = task.getResult(ApiException.class);
            AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
            auth.signInWithCredential(authCredential)
                    .addOnCompleteListener(task1 -> {
                        if(task1.isSuccessful()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userID",task1.getResult().getUser().getUid());
                            editor.commit();
                            authInterface.onAuthSuccess();
                        } else {
                            authInterface.onAuthFailure("Error Signing in");
                        }
                    });
        } catch (ApiException e) {
            authInterface.onAuthFailure("Error Signing in");
        }

    }

}
