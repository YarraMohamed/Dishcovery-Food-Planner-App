package com.example.foodplanner.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAdapter<T> extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<T> searchList;
    private HashMap<String, String> countryThumbnail;
    private FiltersClickListener filtersClickListener;

    public SearchAdapter(Context context,FiltersClickListener filtersClickListener) {
        this.context = context;
        this.filtersClickListener=filtersClickListener;
        this.searchList = new ArrayList<>();
        this.countryThumbnail = new HashMap<>();
        Area.countryMap(countryThumbnail);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.home_meal, recycleView, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(searchList.isEmpty() || searchList == null) return;

        T item = searchList.get(position);

        if (item instanceof Category) {
            holder.mealNameHome.setText(((Category) item).getCategoryName());
            Glide.with(context).load(((Category) item).getCategoryThumb())
                    .apply(new RequestOptions().override(200, 200))
                    .into(holder.mealPhoto);
            holder.mealPhoto.setOnClickListener(v -> {
                filtersClickListener.onFilterCategoryImgClick(((Category) item).getCategoryName());
            });
        } else if (item instanceof Ingredient) {
            holder.mealNameHome.setText(((Ingredient) item).getIngredientName());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + ((Ingredient) item).getIngredientName() + ".png")
                    .apply(new RequestOptions().override(200, 200))
                    .into(holder.mealPhoto);
            holder.mealPhoto.setOnClickListener(v -> {
                filtersClickListener.onFilterIngredientImgClick(((Ingredient) item).getIngredientName());
            });
        } else if (item instanceof Area) {
            holder.mealNameHome.setText(((Area) item).getCountryName());
            String thumbnail = countryThumbnail.get(((Area) item).getCountryName());
            Glide.with(context).load(thumbnail)
                    .apply(new RequestOptions().optionalFitCenter().optionalCircleCrop().override(150, 150))
                    .into(holder.mealPhoto);
            holder.mealPhoto.setOnClickListener(v -> {
                filtersClickListener.onFilterAreaImgClick(((Area) item).getCountryName());
            });
        } else if (item instanceof Meal){
            holder.mealNameHome.setText(((Meal) item).getMealName());
            Glide.with(context)
                    .load(((Meal) item).getMealThumb())
                    .apply(new RequestOptions().override(200,200))
                    .into(holder.mealPhoto);
        }else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void updateList(List<T> newSearchList) {
        searchList.clear();
        searchList.addAll(newSearchList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout meal_item;
        ImageView mealPhoto;
        TextView mealNameHome;

        public ViewHolder(View v) {
            super(v);
            mealNameHome = v.findViewById(R.id.mealNameHome);
            mealPhoto = v.findViewById(R.id.mealPhoto);
            meal_item = v.findViewById(R.id.meal_item);
        }
    }
}