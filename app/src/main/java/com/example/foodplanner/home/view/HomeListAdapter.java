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
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Test;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private Context context;
    private MealResponse mealResponse;
    private static final String TAG = "HomeListAdapter";
    public HomeListAdapter(Context context){
        this.context=context;
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
        holder.mealNameHome.setText(mealResponse.getMeals().get(position).getMealName());
        Glide.with(context).load(mealResponse.getMeals().get(position).getMealThumb())
                .apply(new RequestOptions().override(200,200))
                .into(holder.mealPhoto);
    }

    @Override
    public int getItemCount() {
        return mealResponse.getMeals().size();
    }

    public MealResponse getMealResponse() {
        return mealResponse;
    }

    public void setMealResponse(MealResponse mealResponse) {
        this.mealResponse = mealResponse;
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
