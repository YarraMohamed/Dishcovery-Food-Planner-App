package com.example.foodplanner.auth.view;

public interface AuthInterface {
    public void onAuthSuccess();
    public void onAuthFailure(String errMsg);
    public void wrongEmailInput();
    public void wrongPasswordInput();

}
