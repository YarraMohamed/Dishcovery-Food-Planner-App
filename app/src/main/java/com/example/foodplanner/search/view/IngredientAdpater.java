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
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdpater extends RecyclerView.Adapter<IngredientAdpater.ViewHolder> {
    private Context context;
    private List<Ingredient> ingredients;

    public IngredientAdpater(Context context){
        this.context=context;
        ingredients=new ArrayList<>();
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
        holder.mealNameHome.setText(ingredients.get(position).getIngredientName());
        Glide.with(context).load( "https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getIngredientName()+".png")
                .apply(new RequestOptions().override(200,200))
                .into(holder.mealPhoto);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;

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
