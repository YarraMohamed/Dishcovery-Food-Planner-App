package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealCloudDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;
import com.example.foodplanner.presenter.NetworkConnection;
import com.example.foodplanner.presenter.NetworkPresenter;
import com.example.foodplanner.view.NetworkInterface;

import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface, ItemClickListener, NetworkInterface {

    private static final String TAG = "HomeFragment";
    private HomePresenter homePresenter;
    private HomeListAdapter homeListAdapter;
    private List<Test> meals;
    private RecyclerView recyclerView;
    private ImageView MealPhoto;
    private TextView MealName,textView2,textView9,connectionTxt;
    private CardView MealCard;
    private View divider;
    private LottieAnimationView noConnection;
    private NetworkPresenter networkPresenter;

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

        setData(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        homeListAdapter = new HomeListAdapter(getActivity(),this);
        MealPhoto = view.findViewById(R.id.MealPhoto);

        networkPresenter = new NetworkPresenter(this,
                new NetworkConnection(requireContext()));
        networkPresenter.startListening();

        MealName = view.findViewById(R.id.MealName);
        homePresenter = new HomePresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealCloudDataSource(),
                        new MealLocalDataSource(requireContext())));

        MealPhoto.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToItemFragment action =
                    HomeFragmentDirections.actionHomeFragmentToItemFragment();
            action.setMealName(MealName.getText().toString());
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkPresenter.stopListening();
    }

    private void setData(View v){
        textView2 = v.findViewById(R.id.textView2);
        textView9 = v.findViewById(R.id.textView9);
        connectionTxt = v.findViewById(R.id.connectionTxt);
        MealCard = v.findViewById(R.id.MealCard);
        divider = v.findViewById(R.id.divider2);
        noConnection = v.findViewById(R.id.noConnection);
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

    @Override
    public void onNetworkAvaliable() {
        homePresenter.getRandomMeal();
        homePresenter.getSuggestedMeals();

        noConnection.setVisibility(View.GONE);
        connectionTxt.setVisibility(View.GONE);
        textView2.setVisibility(View.VISIBLE);
        textView9.setVisibility(View.VISIBLE);
        MealCard.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetworkLost() {
        noConnection.setVisibility(View.VISIBLE);
        connectionTxt.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.GONE);
        textView9.setVisibility(View.GONE);
        MealCard.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

}