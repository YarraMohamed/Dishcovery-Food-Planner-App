package com.example.foodplanner.favourites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.favourites.presenter.FavouritesPresenter;
import com.example.foodplanner.model.Test;
import com.example.foodplanner.presenter.Presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private FavouritesListAdapter favouritesListAdapter;
    private List<Test> favs;
    private RecyclerView Favourites;
    private FavouritesPresenter favouritesPresenter;
    private Presenter presenter;
    private LottieAnimationView loginAnim;
    private TextView loginAlert;

    public FavouritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         favs = new ArrayList<>(Arrays.asList(
                new Test("test meal 1","categoty 1","country 1"),
                new Test("test meal 2","categoty 2","country 2"),
                new Test("test meal 3","categoty 3","country 3"),
                new Test("test meal 4","categoty 4","country 4"),
                new Test("test meal 5","categoty 5","country 5"),
                new Test("test meal 6","categoty 6","country 6"),
                new Test("test meal 7","categoty 7","country 7")
        ));

         loginAnim = view.findViewById(R.id.loginAnim);
         loginAlert = view.findViewById(R.id.loginAlert);

        Favourites = view.findViewById(R.id.Favourites);
        Favourites.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        Favourites.setLayoutManager(manager);
        favouritesListAdapter = new FavouritesListAdapter(getContext(),favs);
        Favourites.setAdapter(favouritesListAdapter);

        presenter = new Presenter();
        if(presenter.checkAuth()){
            Favourites.setVisibility(View.VISIBLE);
            loginAnim.setVisibility(View.GONE);
            loginAlert.setVisibility(View.GONE);
        }else{
            Favourites.setVisibility(View.GONE);
            loginAnim.setVisibility(View.VISIBLE);
            loginAlert.setVisibility(View.VISIBLE);
        }

    }
}