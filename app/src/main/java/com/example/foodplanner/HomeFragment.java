package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    HomeListAdapter homeListAdapter;
    List<Test> meals;
    RecyclerView recyclerView;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meals = new ArrayList<>();
        meals.add(new Test("test meal 1"));
        meals.add(new Test("test meal 2"));
        meals.add(new Test("test meal 3"));
        meals.add(new Test("test meal 4"));
        meals.add(new Test("test meal 5"));
        meals.add(new Test("test meal 6"));
        meals.add(new Test("test meal 7"));
        meals.add(new Test("test meal 8"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        homeListAdapter = new HomeListAdapter(getActivity(),meals);
        recyclerView.setAdapter(homeListAdapter);


    }
}