package com.example.foodplanner.plannedMeals.view;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.favourites.view.FavouritesListAdapter;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;

import java.util.ArrayList;
import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.ViewHolder>{
    private Context context;
    private OnRemoveClick removeClick;
    private List<PlanMeals> planMeals;
    public PlansAdapter(Context context,OnRemoveClick removeClick){
        this.context=context;
        this.removeClick=removeClick;
        this.planMeals=new ArrayList<>();
    }

    public List<PlanMeals> getPlanMeals() {
        return planMeals;
    }

    public void setPlanMeals(List<PlanMeals> planMeals) {
        this.planMeals = planMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.plan_meal,recycleView,false);
        ViewHolder vh = new PlansAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanMeals planMeal = planMeals.get(position);
        holder.plannedName.setText(planMeal.getMealName());
        Glide.with(context).load(planMeal.getMealThumb())
                        .apply(new RequestOptions().override(150,150))
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(holder.plannedImg);
        holder.deleteLogo.setImageResource(R.drawable.delete_logo);
        holder.deleteLogo.setOnClickListener(v -> {
            removeClick.onRemoveItemClick(planMeal);
        });

    }

    @Override
    public int getItemCount() {
        return planMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView deleteLogo, plannedImg;
        TextView plannedName;
        View layout;
        ConstraintLayout MealPlan;
        public ViewHolder(@NonNull View v) {
            super(v);
            layout=v;
           deleteLogo = v.findViewById(R.id.deleteLogo);
           plannedImg = v.findViewById(R.id.plannedImg);
           plannedName = v.findViewById(R.id.plannedName);
           MealPlan = v.findViewById(R.id.MealPlan);
        }
    }
}
