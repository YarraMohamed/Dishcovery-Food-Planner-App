package com.example.foodplanner.auth.view;

public interface SignupInterface {
    public void onSignupSuccess();
    public void onSignupFailure(String errMsg);
    public void wrongEmailInput();
    public void wrongPasswordInput();
}
