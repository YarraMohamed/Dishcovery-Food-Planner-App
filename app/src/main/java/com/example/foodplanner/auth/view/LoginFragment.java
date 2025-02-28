package com.example.foodplanner.auth.view;

import android.content.Context;
import android.content.Intent;
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

public class LoginFragment extends Fragment implements AuthInterface {

    private TextView signUpTxt;
    private Button Loginbtn, signInWithGoogleBtn,Skip;
    private EditText emailtxt,passwordTxt;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private AuthPresenter authPresenter;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailtxt = view.findViewById(R.id.emailtxt);
        passwordTxt = view.findViewById(R.id.passwordTxt);
        signUpTxt = view.findViewById(R.id.signUpTxt);
        signInWithGoogleBtn = view.findViewById(R.id.signInWithGoogleBtn);
        Loginbtn = view.findViewById(R.id.Loginbtn);
        Skip = view.findViewById(R.id.Skip);

        authPresenter = new AuthPresenter(this,
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE),
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealCloudDataSource(),
                        new MealLocalDataSource(getContext())));

        setGoogleOptions();

        Skip.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
        });

        signUpTxt.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
        });

        signInWithGoogleBtn.setOnClickListener(v -> {
            googleSignInClient.revokeAccess().addOnCompleteListener(task -> {
                Intent signinIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signinIntent,RC_SIGN_IN);
            });
        });

        Loginbtn.setOnClickListener(v -> {
            String email = emailtxt.getText().toString().trim();
            String password = passwordTxt.getText().toString().trim();
            authPresenter.Login(email,password);
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
        Toast.makeText(getContext(),"Logged in Successfully",Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onAuthFailure(String errMsg) {
        Toast.makeText(getContext(),errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wrongEmailInput() {
        emailtxt.setError("Wrong email input");
    }

    @Override
    public void wrongPasswordInput() {
        emailtxt.setError("Wrong password input");
    }
}