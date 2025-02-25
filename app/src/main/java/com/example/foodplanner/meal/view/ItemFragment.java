package com.example.foodplanner.meal.view;

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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.meal.presenter.ItemPresenter;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.IngredientDetails;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealConverter;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;
import com.example.foodplanner.presenter.Presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemFragment extends Fragment implements ItemViewInterface {

    private ItemsAdpater itemsAdpater;
    private Meal currentMeal;
    private Presenter presenter;
    private List<IngredientDetails> ing;
    private RecyclerView ingredientsRecycle;
    private ItemPresenter itemPresenter;
    private TextView itemName,itemCategory, itemCountry,instructionsTxt;
    private ImageView itemImg,favIcon;
    private WebView videoWebView;

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


        ingredientsRecycle = view.findViewById(R.id.ingredientsRecycle);
        ingredientsRecycle.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ingredientsRecycle.setLayoutManager(manager);

        itemsAdpater = new ItemsAdpater(getContext());
        ingredientsRecycle.setAdapter(itemsAdpater);

        itemName = view.findViewById(R.id.itemName);
        itemCategory = view.findViewById(R.id.itemCategory);
        itemCountry = view.findViewById(R.id.itemCountry);
        itemImg = view.findViewById(R.id.itemImg);
        instructionsTxt = view.findViewById(R.id.instructionsTxt);
        videoWebView = view.findViewById(R.id.videoWebView);
        favIcon = view.findViewById(R.id.favIcon);

        itemPresenter = new ItemPresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealLocalDataSource(requireContext())));
        presenter = new Presenter();

        String mealName = ItemFragmentArgs.fromBundle(getArguments()).getMealName();
        Log.i("Item", "onViewCreated: " + mealName);
        if(!mealName.isEmpty() || !mealName.equals("") || mealName!=null){
            itemPresenter.getMeal(mealName);
        }

        favIcon.setOnClickListener(v -> {
            if(currentMeal!=null &&presenter.checkAuth()){
                onClickFav(currentMeal);
                Toast.makeText(requireContext(),"Added to Favourites",Toast.LENGTH_SHORT).show();
            }else{
                Log.i("TAG", "CurrentMeal: " + currentMeal +"CheckAuth: "+ !presenter.checkAuth());
                Toast.makeText(requireContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getMeal(MealResponse mealResponse) {
        if(isAdded() && mealResponse!=null && mealResponse.getMeals()!=null && !mealResponse.getMeals().isEmpty()){
            currentMeal = mealResponse.getMeals().get(0);

            itemName.setText(mealResponse.getMeals().get(0).getMealName());
            itemCategory.setText(mealResponse.getMeals().get(0).getCategory());
            itemCountry.setText(mealResponse.getMeals().get(0).getArea());
            instructionsTxt.setText(itemPresenter.getInstructions(mealResponse.getMeals().get(0).getInstructions()));

            String videoUrl = mealResponse.getMeals().get(0).getYoutubeLink();
            if (!videoUrl.equals("")) {
                videoUrl = videoUrl.replace("watch?v=", "embed/");
                videoWebView.getSettings().setJavaScriptEnabled(true);
                videoWebView.loadUrl(videoUrl);
            }

            Glide.with(requireContext())
                    .load(mealResponse.getMeals().get(0).getMealThumb())
                    .apply(new RequestOptions().override(150,150))
                    .into(itemImg);

            itemsAdpater.setIng(mealResponse.getMeals().get(0).getIngredientsList());
        }
    }

    @Override
    public void showError(String err) {
        Log.i("TAG", "showError: " + err);
    }

    public void onClickFav(Meal meal){
        FavMeals favMeal = MealConverter.mealToFavMeal(meal);
        itemPresenter.addToFav(favMeal);
    }
}