package com.example.foodplanner.meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemFragment extends Fragment {

    com.example.foodplanner.meal.view.itemsAdpater itemsAdpater;
    List<Test> ing;
    RecyclerView ingredientsRecycle;
    public ItemFragment() {
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
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ing = new ArrayList<>(Arrays.asList(
                new Test("test meal 1","2kg "),
                new Test("test meal 2","2kg "),
                new Test("test meal 3","2kg "),
                new Test("test meal 4","2kg "),
                new Test("test meal 5","2kg "),
                new Test("test meal 6","2kg "),
                new Test("test meal 7","2kg "),
                new Test("test meal 8","2kg "),
                new Test("test meal 9","2kg "),
                new Test("test meal 10","2kg ")
        ));

        ingredientsRecycle = view.findViewById(R.id.ingredientsRecycle);
        ingredientsRecycle.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ingredientsRecycle.setLayoutManager(manager);

        itemsAdpater = new itemsAdpater(getContext(),ing);
        ingredientsRecycle.setAdapter(itemsAdpater);
    }
}