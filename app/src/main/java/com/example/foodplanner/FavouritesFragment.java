package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FavouritesFragment extends Fragment {

    FavouritesListAdapter favouritesListAdapter;
    List<Test> favs;
    RecyclerView Favourites;

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

        Favourites = view.findViewById(R.id.Favourites);
        Favourites.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        Favourites.setLayoutManager(manager);
        favouritesListAdapter = new FavouritesListAdapter(getContext(),favs);
        Favourites.setAdapter(favouritesListAdapter);

    }
}