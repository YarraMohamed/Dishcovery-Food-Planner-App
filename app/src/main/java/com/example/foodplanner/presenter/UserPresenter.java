package com.example.foodplanner.presenter;

import com.google.firebase.auth.FirebaseAuth;

public class UserPresenter {
    private FirebaseAuth auth;

    public UserPresenter(){
        this.auth=FirebaseAuth.getInstance();
    }
    public Boolean checkAuth() {
        if(auth.getCurrentUser()!=null){
            return true;
        }
        return false;
    }
}
