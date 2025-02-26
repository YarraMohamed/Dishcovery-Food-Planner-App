package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.home.view.HomeFragmentDirections;
import com.example.foodplanner.home.view.HomeListAdapter;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;
import com.example.foodplanner.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment  implements SearchViewInterface, FiltersClickListener{

    private RecyclerView list;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private Chip categoryChip,countryChip,IngredientChip;
    private static final String TAG = "SearchFragment";

    public SearchFragment() {

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

        categoryChip = view.findViewById(R.id.categoryChip);
        countryChip = view.findViewById(R.id.countryChip);
        IngredientChip = view.findViewById(R.id.IngredientChip);
        list = view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        list.setLayoutManager(manager);

        searchPresenter = new SearchPresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealLocalDataSource(requireContext())));

        searchAdapter = new SearchAdapter(getContext(),this);
        list.setAdapter(searchAdapter);

        categoryChip.setOnClickListener(v -> {
            searchPresenter.getCategories();;
        });

        IngredientChip.setOnClickListener(v -> {
            searchPresenter.getIngredients();
        });

        countryChip.setOnClickListener(v -> {
            searchPresenter.getCountries();
        });

    }

    @Override
    public void showList(List data) {
        if(isAdded()&&getActivity()!=null){
            searchAdapter.updateList(data);
            list.scrollToPosition(0);
        }
    }

    @Override
    public void showError(String err) {
        Log.i(TAG, "showError: " + err);
    }

    @Override
    public void onFilterCategoryImgClick(String name) {
        searchPresenter.getMealsByCategory(name);
    }

    @Override
    public void onFilterAreaImgClick(String name) {
        searchPresenter.getMealsByCountry(name);
    }

    @Override
    public void onFilterIngredientImgClick(String name) {
        searchPresenter.getMealsByIngredient(name);
    }

    @Override
    public void onItemIgmClick(String mealName) {
        SearchFragmentDirections.ActionSearchFragmentToItemFragment action =
                SearchFragmentDirections.actionSearchFragmentToItemFragment();
        action.setMealName(mealName);
        Navigation.findNavController(requireView()).navigate(action);
    }
}