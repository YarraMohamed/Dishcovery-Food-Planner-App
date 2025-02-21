package com.example.foodplanner.profile.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.search.presenter.ProfilePresenter;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment implements ProfileInterface {
    Button backupBtn,logoutBtn,loginBtn;
    TextView nameTxt;
    ProfilePresenter profilePresenter;
    String username;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backupBtn = view.findViewById(R.id.backupBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        loginBtn = view.findViewById(R.id.loginBtn);
        nameTxt = view.findViewById(R.id.nameTxt);
        profilePresenter = new ProfilePresenter(this,
                requireContext().getSharedPreferences("credentials",Context.MODE_PRIVATE));

        if(profilePresenter.checkAuth()){
            backupBtn.setVisibility(Button.GONE);
            logoutBtn.setVisibility(Button.GONE);
            loginBtn.setVisibility(Button.VISIBLE);
            nameTxt.setText("Guest");
        }else{
            backupBtn.setVisibility(Button.VISIBLE);
            logoutBtn.setVisibility(Button.VISIBLE);
            loginBtn.setVisibility(Button.GONE);
        }

        logoutBtn.setOnClickListener(v -> {
           profilePresenter.logout();
        });

        loginBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_loginFragment);
        });

    }

    @Override
    public void onLogout() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_homeFragment);
        Toast.makeText(getContext(),"Logged out Successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsername(String username) {
        nameTxt.setText(username);
    }
}