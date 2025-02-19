package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    HomeListAdapter homeListAdapter;
    List<Test> meals;
    RecyclerView list;

    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
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

        list = view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        list.setLayoutManager(manager);
        homeListAdapter = new HomeListAdapter(getContext(),meals);
        list.setAdapter(homeListAdapter);

    }
}