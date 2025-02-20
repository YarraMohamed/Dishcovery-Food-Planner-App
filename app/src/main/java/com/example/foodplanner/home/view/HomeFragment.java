package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.db.Repository;
import com.example.foodplanner.db.remote.MealRemoteDataSource;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface {

    private static final String TAG = "HomeFragment";
    private HomePresenter homePresenter;
    private HomeListAdapter homeListAdapter;
    private List<Test> meals;
    private RecyclerView recyclerView;
    private ImageView MealPhoto;
    private TextView MealName;


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
        homeListAdapter = new HomeListAdapter(getActivity());
        MealPhoto = view.findViewById(R.id.MealPhoto);

        MealName = view.findViewById(R.id.MealName);
        homePresenter = new HomePresenter(this, Repository.getRepoInstance(new MealRemoteDataSource()));
        homePresenter.getRandomMeal();
        homePresenter.getSuggestedMeals();
    }

    @Override
    public void showRandomMeal(MealResponse mealResponse) {
        Log.i(TAG, "showSuggestedMeal: " + mealResponse.getMeals().size());
        MealName.setText(mealResponse.getMeals().get(0).getMealName());
        Glide.with(getContext()).load(mealResponse.getMeals().get(0).getMealThumb())
                .apply(new RequestOptions().override(200,200))
                .into(MealPhoto);
    }

    @Override
    public void showSuggestedMeal(MealResponse mealResponse) {
        Log.i(TAG, "showSuggestedMeal: " + mealResponse.getMeals().size());
        homeListAdapter.setMealResponse(mealResponse);
        recyclerView.setAdapter(homeListAdapter);
        homeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String err) {

    }
}