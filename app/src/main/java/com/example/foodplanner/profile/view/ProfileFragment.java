package com.example.foodplanner.profile.view;

import android.content.Context;
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

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealCloudDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.presenter.NetworkConnection;
import com.example.foodplanner.presenter.NetworkPresenter;
import com.example.foodplanner.profile.presenter.ProfilePresenter;
import com.example.foodplanner.view.NetworkInterface;

public class ProfileFragment extends Fragment implements ProfileInterface, NetworkInterface {
    private Button logoutBtn,loginBtn;
    private TextView nameTxt,textView5,connectionTxt;
    private ProfilePresenter profilePresenter;
    private String username;
    private LottieAnimationView lottieAnimationView,noConnection;
    private NetworkPresenter networkPresenter;

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

        setView(view);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        loginBtn = view.findViewById(R.id.loginBtn);
        nameTxt = view.findViewById(R.id.nameTxt);
        profilePresenter = new ProfilePresenter(this,
                requireContext().getSharedPreferences("credentials",Context.MODE_PRIVATE),
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealCloudDataSource(),
                        new MealLocalDataSource(requireContext())));

        networkPresenter = new NetworkPresenter(this,
                new NetworkConnection(requireContext()));
        networkPresenter.startListening();

        logoutBtn.setOnClickListener(v -> {
           profilePresenter.logout();
        });

        loginBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_loginFragment);
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkPresenter.stopListening();
    }

    public void setView(View view){
        textView5= view.findViewById(R.id.textView5);
        noConnection = view.findViewById(R.id.noConnection);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        connectionTxt=view.findViewById(R.id.connectionTxt);
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

    @Override
    public void onSuccessBackup() {
        Toast.makeText(getContext(),"Backup Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFailureBackup() {
        Toast.makeText(getContext(),"Backup Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkAvaliable() {
        if(profilePresenter.checkAuth()){
            logoutBtn.setVisibility(Button.GONE);
            loginBtn.setVisibility(Button.VISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);
            nameTxt.setVisibility(View.VISIBLE);
            nameTxt.setText("Guest");
            noConnection.setVisibility(View.GONE);
            connectionTxt.setVisibility(View.GONE);
        }else{
            logoutBtn.setVisibility(Button.VISIBLE);
            loginBtn.setVisibility(Button.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);
            nameTxt.setVisibility(View.VISIBLE);
            noConnection.setVisibility(View.GONE);
            connectionTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNetworkLost() {
        logoutBtn.setVisibility(Button.GONE);
        loginBtn.setVisibility(Button.GONE);
        lottieAnimationView.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);
        nameTxt.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
        connectionTxt.setVisibility(View.VISIBLE);

    }
}