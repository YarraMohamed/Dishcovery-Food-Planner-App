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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;

import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface, ItemClickListener {

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
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        homeListAdapter = new HomeListAdapter(getActivity(),this);
        MealPhoto = view.findViewById(R.id.MealPhoto);

        MealName = view.findViewById(R.id.MealName);
        homePresenter = new HomePresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealLocalDataSource(requireContext())));

        homePresenter.getRandomMeal();
        homePresenter.getSuggestedMeals();

        MealPhoto.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToItemFragment action =
                    HomeFragmentDirections.actionHomeFragmentToItemFragment();
            action.setMealName(MealName.getText().toString());
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public void showRandomMeal(Meal meal) {
        if(isAdded()&&getActivity()!=null){
            MealName.setText(meal.getMealName());
            Glide.with(requireContext()).load(meal.getMealThumb())
                    .apply(new RequestOptions().override(200,200))
                    .into(MealPhoto);
        }
    }

    @Override
    public void showSuggestedMeal(List<Meal> meals) {
        homeListAdapter.setMeals(meals);
        recyclerView.setAdapter(homeListAdapter);
        homeListAdapter.notifyDataSetChanged();

    }


    @Override
    public void showError(String err) {
        Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImgClick(String mealName) {
        HomeFragmentDirections.ActionHomeFragmentToItemFragment action =
                HomeFragmentDirections.actionHomeFragmentToItemFragment();
        action.setMealName(mealName);
        Navigation.findNavController(requireView()).navigate(action);
    }
}