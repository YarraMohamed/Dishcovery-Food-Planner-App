package com.example.foodplanner.home.view;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private Context context;
    private List<Meal> Meals;
    private ItemClickListener itemClickListener;
    private static final String TAG = "HomeListAdapter";
    public HomeListAdapter(Context context,ItemClickListener itemClickListener){
        this.context=context;
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater =LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.home_meal,recycleView,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mealNameHome.setText(Meals.get(position).getMealName());
        Glide.with(context).load(Meals.get(position).getMealThumb())
                .apply(new RequestOptions().override(200,200))
                .into(holder.mealPhoto);
        holder.mealPhoto.setOnClickListener(v -> {
            itemClickListener.onImgClick(Meals.get(position).getMealName());
        });
    }

    @Override
    public int getItemCount() {
        return Meals.size();
    }

    public List<Meal> getMeals() {
        return Meals;
    }

    public void setMeals(List<Meal> meals) {
        Meals = meals;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout meal_item;
        ImageView mealPhoto;
        TextView mealNameHome;
        View layout;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            mealNameHome = v.findViewById(R.id.mealNameHome);
            mealPhoto = v.findViewById(R.id.mealPhoto);
            meal_item = v.findViewById(R.id.meal_item);
        }
    }
}
