package com.example.foodplanner.favourites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.favourites.presenter.FavouritesPresenter;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.presenter.UserPresenter;

import java.util.List;

public class FavouritesFragment extends Fragment implements  FavouritesViewInterface , RemoveClickListener{

    private FavouritesListAdapter favouritesListAdapter;
    private RecyclerView Favourites;
    private FavouritesPresenter favouritesPresenter;
    private UserPresenter userPresenter;
    private LottieAnimationView loginAnim,emptyAnim;
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

         loginAnim = view.findViewById(R.id.loginAnim);
         loginAlert = view.findViewById(R.id.loginAlert);
         emptyAnim = view.findViewById(R.id.emptyAnim);

        Favourites = view.findViewById(R.id.Favourites);
        Favourites.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        Favourites.setLayoutManager(manager);
        favouritesListAdapter = new FavouritesListAdapter(getContext(),this);
        Favourites.setAdapter(favouritesListAdapter);

        userPresenter = new UserPresenter();

        favouritesPresenter = new FavouritesPresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealLocalDataSource(getContext())));

        favouritesPresenter.getStoredFav();
        setView();

    }

    @Override
    public void showFavourites(List<FavMeals> favMeals) {
        favouritesListAdapter.setFavs(favMeals);
        favouritesListAdapter.notifyDataSetChanged();

         if(favMeals.isEmpty() && userPresenter.checkAuth()){
            Favourites.setVisibility(View.GONE);
            emptyAnim.setVisibility(View.VISIBLE);
        }else if(!userPresenter.checkAuth() && favMeals.isEmpty()){
            Favourites.setVisibility(View.GONE);
            emptyAnim.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(requireContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClick(FavMeals favMeal) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Remove Favorite");
        builder.setMessage("Are you sure you want to remove this meal from favorites?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            favouritesPresenter.removeFromFav(favMeal);
            Toast.makeText(getContext(),"Deleted from favourites",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void setView(){
        if(userPresenter.checkAuth()){
            Favourites.setVisibility(View.VISIBLE);
            loginAnim.setVisibility(View.GONE);
            loginAlert.setVisibility(View.GONE);
            emptyAnim.setVisibility(View.GONE);
        }else{
            Favourites.setVisibility(View.GONE);
            loginAnim.setVisibility(View.VISIBLE);
            loginAlert.setVisibility(View.VISIBLE);
            emptyAnim.setVisibility(View.GONE);
        }

    }
}