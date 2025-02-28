package com.example.foodplanner.meal.view;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealCloudDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.meal.presenter.ItemPresenter;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.IngredientDetails;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealConverter;
import com.example.foodplanner.model.PlanMeals;
import com.example.foodplanner.presenter.UserPresenter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ItemFragment extends Fragment implements ItemViewInterface {

    private ItemsAdpater itemsAdpater;
    private Meal currentMeal;
    private UserPresenter userPresenter;
    private List<IngredientDetails> ing;
    private RecyclerView ingredientsRecycle;
    private ItemPresenter itemPresenter;
    private TextView itemName,itemCategory, itemCountry,instructionsTxt;
    private ImageView itemImg,favIcon,calenderIcon;
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
        calenderIcon = view.findViewById(R.id.calenderIcon);

        itemPresenter = new ItemPresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealCloudDataSource(),
                        new MealLocalDataSource(requireContext())));
        userPresenter = new UserPresenter();

        String mealName = ItemFragmentArgs.fromBundle(getArguments()).getMealName();
        Log.i("Item", "onViewCreated: " + mealName);
        if(!mealName.isEmpty() || !mealName.equals("") || mealName!=null){
            itemPresenter.getMeal(mealName);
        }

        favIcon.setOnClickListener(v -> {
            if(currentMeal!=null && userPresenter.checkAuth()){
                onClickFav(currentMeal);
                Toast.makeText(requireContext(),"Added to Favourites",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(requireContext(),"You should login first",Toast.LENGTH_SHORT).show();
            }
        });

        calenderIcon.setOnClickListener(v -> {
            if(userPresenter.checkAuth()){
                onClickCalender();
            }else{
                Toast.makeText(requireContext(),"You should login first",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getMeal(Meal meal) {
        if(isAdded() && meal!=null){
            currentMeal = meal;
            itemName.setText(meal.getMealName());
            itemCategory.setText(meal.getCategory());
            itemCountry.setText(meal.getArea());
            instructionsTxt.setText(itemPresenter.getInstructions(meal.getInstructions()));
            String videoURL = meal.getYoutubeLink();

            if(!videoURL.equals("")){
                videoURL = videoURL.replace("watch?v=", "embed/");
                videoWebView.getSettings().setJavaScriptEnabled(true);
                videoWebView.loadUrl(videoURL);
            }

            Glide.with(requireContext())
                    .load(meal.getMealThumb())
                    .apply(new RequestOptions().override(150,150))
                    .into(itemImg);

            itemsAdpater.setIng(meal.getIngredientsList());

        }

    }

    @Override
    public void showError(String err) {
        Log.i("TAG", "showError: " + err);
    }

    private void onClickFav(Meal meal){
        FavMeals favMeal = MealConverter.mealToFavMeal(meal);
        itemPresenter.addToFav(favMeal);
        itemPresenter.uploadFavMeal(userPresenter.getUserID(),favMeal);
        Log.i("TAG", "Item added");
    }

    private void onClickCalender() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String formattedDate = dateFormat.format(selectedDate.getTime());
                        PlanMeals planMeal= MealConverter.mealToPlanMeal(currentMeal);
                        planMeal.setDate(formattedDate);
                        itemPresenter.addToPlan(planMeal);
                        itemPresenter.uploadPlanMeal(userPresenter.getUserID(),planMeal);

                        Toast.makeText(getContext(),"Added to plan", Toast.LENGTH_SHORT).show();
                    }
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}