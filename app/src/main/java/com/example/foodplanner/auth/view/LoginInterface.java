package com.example.foodplanner.auth.view;

public interface LoginInterface {
    public void onLoginSuccess();
    public void onLoginFailure(String errMsg);
    public void wrongEmailInput();
    public void wrongPasswordInput();
}
