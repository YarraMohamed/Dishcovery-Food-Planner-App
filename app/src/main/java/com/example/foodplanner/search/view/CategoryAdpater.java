package com.example.foodplanner.search.view;

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

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class CategoryAdpater extends RecyclerView.Adapter<CategoryAdpater.ViewHolder> {
    private Context context;
    private List<Category> categories;

    public CategoryAdpater(Context context){
        this.context=context;
        categories=new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.home_meal,recycleView,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mealNameHome.setText(categories.get(position).getCategoryName());
        Glide.with(context).load(categories.get(position).getCategoryThumb())
                .apply(new RequestOptions().override(200,200))
                .into(holder.mealPhoto);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;

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
