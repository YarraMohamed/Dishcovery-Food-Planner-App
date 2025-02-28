package com.example.foodplanner.auth.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.auth.presenter.AuthPresenter;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealCloudDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class SignupFragment extends Fragment implements AuthInterface {

    private TextView logintxt;
    private EditText email, password;
    private Button signUpBtn, GoogleBtn;
    private AuthPresenter authPresenter;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    public SignupFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        logintxt = view.findViewById(R.id.logintxt);
        GoogleBtn = view.findViewById(R.id.GoogleBtn);

        authPresenter = new AuthPresenter(this,
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE),
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealCloudDataSource(),
                        new MealLocalDataSource(getContext())));

        setGoogleOptions();

        logintxt.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
        });

        signUpBtn.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            authPresenter.signUp(userEmail,userPassword);
        });

        GoogleBtn.setOnClickListener(v -> {
            googleSignInClient.revokeAccess().addOnCompleteListener(task -> {
                Intent signUpIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signUpIntent,RC_SIGN_IN);
            });
        });
    }

    public void setGoogleOptions(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(),gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            authPresenter.signInWithGoogle(task);
        }
    }

    @Override
    public void onAuthSuccess() {
        Toast.makeText(getContext(),"Signed Successfully",Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_homeFragment);
    }

    @Override
    public void onAuthFailure(String errMsg) {
        Toast.makeText(getContext(),errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wrongEmailInput() {
        email.setError("Email cannot be Empty");
    }

    @Override
    public void wrongPasswordInput() {
        password.setError("Password cannot be Empty");
    }

}