package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchFragment extends Fragment  implements SearchViewInterface, FiltersClickListener{

    private RecyclerView list;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private Chip categoryChip,countryChip,IngredientChip;
    private EditText searchTxt;
    private static final String TAG = "SearchFragment";
    private boolean isCategoryChecked;
    private boolean isCountryChecked;
    private boolean isIngredientChecked;
    private String selectedFilterName;
    private Disposable disposable;
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

        searchTxt = view.findViewById(R.id.searchTxt);
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
            selectedFilterName=null;
            isCategoryChecked=false;
            isIngredientChecked=false;
            isCountryChecked=false;
            searchPresenter.getCategories();;
        });

        IngredientChip.setOnClickListener(v -> {
            selectedFilterName=null;
            isCategoryChecked=false;
            isIngredientChecked=false;
            isCountryChecked=false;
            searchPresenter.getIngredients();
        });

        countryChip.setOnClickListener(v -> {
            selectedFilterName=null;
            isCategoryChecked=false;
            isIngredientChecked=false;
            isCountryChecked=false;
            searchPresenter.getCountries();
        });

        Observable.create((ObservableEmitter<String> emitter) -> {
                    searchTxt.addTextChangedListener(new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void afterTextChanged(Editable s) {}

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                emitter.onNext(s.toString());
                        }
                    });
                })
                .debounce(350, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        text->viewSearchResults(text)
                );
    }

    private void viewSearchResults(String key){
         if(isCategoryChecked && categoryChip.isChecked()) searchPresenter.searchForMealInCategory(selectedFilterName,key);
         else if(isCountryChecked && countryChip.isChecked()) searchPresenter.searchForMealInCountry(selectedFilterName,key);
         else if(isIngredientChecked && IngredientChip.isChecked()) searchPresenter.searchForMealInIngredient(selectedFilterName,key);
         else if(categoryChip.isChecked() && !isCountryChecked) searchPresenter.searchForCategory(key);
         else if(countryChip.isChecked()) searchPresenter.searchForCountry(key);
         else if(IngredientChip.isChecked()) searchPresenter.searchForIngredient(key);
         else searchPresenter.searchForMeal(key);
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
        selectedFilterName=name;
        isCategoryChecked=true;
        isCountryChecked=false;
        isIngredientChecked=false;
    }

    @Override
    public void onFilterAreaImgClick(String name) {
        searchPresenter.getMealsByCountry(name);
        selectedFilterName=name;
        isCategoryChecked=false;
        isCountryChecked=true;
        isIngredientChecked=false;
    }

    @Override
    public void onFilterIngredientImgClick(String name) {
        searchPresenter.getMealsByIngredient(name);
        selectedFilterName=name;
        isCategoryChecked=false;
        isCountryChecked=false;
        isIngredientChecked=true;
    }

    @Override
    public void onItemIgmClick(String mealName) {
        SearchFragmentDirections.ActionSearchFragmentToItemFragment action =
                SearchFragmentDirections.actionSearchFragmentToItemFragment();
        action.setMealName(mealName);
        Navigation.findNavController(requireView()).navigate(action);
    }
}