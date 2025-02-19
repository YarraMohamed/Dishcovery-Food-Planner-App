package com.example.foodplanner;

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

public class SplashScreenFragment extends Fragment {

    private static final int SPLASH_SCREEN_TIME_OUT = 1500;
    private LottieAnimationView lottieAnimationView;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigateRunnable = new Runnable() {
        @Override
        public void run() {
            if (isAdded()) {
                lottieAnimationView.pauseAnimation();
                Navigation.findNavController(requireView()).navigate(R.id.action_splashScreenFragment_to_loginFragment);
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
        handler.postDelayed(navigateRunnable, SPLASH_SCREEN_TIME_OUT);
    }

    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(navigateRunnable);
    }
}