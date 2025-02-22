package com.example.foodplanner.favourites.presenter;

import com.google.firebase.auth.FirebaseAuth;

public class FavouritesPresenter {
   private FirebaseAuth auth;

   public FavouritesPresenter(){
       this.auth=FirebaseAuth.getInstance();
   }
    public Boolean checkAuth() {
        if(auth.getCurrentUser()!=null){
            return true;
        }
        return false;
    }

}
