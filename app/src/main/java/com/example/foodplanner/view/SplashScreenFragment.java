package com.example.foodplanner.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class SplashScreenFragment extends Fragment {

    private static final int SPLASH_SCREEN_TIME_OUT = 1500;
    private LottieAnimationView lottieAnimationView;
    private SharedPreferences preferences;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigateRunnable = new Runnable() {
        @Override
        public void run() {
            if (isAdded()) {
                preferences = requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE);
                String userdID = preferences.getString("userID","");
                lottieAnimationView.pauseAnimation();
                if(userdID.equals("") || userdID.isEmpty()){
                    Navigation.findNavController(requireView()).navigate(R.id.action_splashScreenFragment_to_loginFragment);
                }else {
                    Navigation.findNavController(requireView()).navigate(R.id.action_splashScreenFragment_to_homeFragment);
                }
            }
        }
    };
    public SplashScreenFragment() {
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
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.postDelayed(navigateRunnable, SPLASH_SCREEN_TIME_OUT);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(navigateRunnable);
    }

}