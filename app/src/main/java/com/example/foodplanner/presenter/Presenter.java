package com.example.foodplanner.presenter;

import com.google.firebase.auth.FirebaseAuth;

public class Presenter {
    private FirebaseAuth auth;

    public Presenter(){
        this.auth=FirebaseAuth.getInstance();
    }
    public Boolean checkAuth() {
        if(auth.getCurrentUser()!=null){
            return true;
        }
        return false;
    }
}
