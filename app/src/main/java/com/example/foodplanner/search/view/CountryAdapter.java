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

import com.example.foodplanner.R;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private Context context;
    private List<Area> countries;
    public CountryAdapter(Context context){
        this.context=context;
        countries=new ArrayList<>();
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
        holder.mealNameHome.setText(countries.get(position).getCountryName());
        holder.mealPhoto.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public List<Area> getCountries() {
        return countries;
    }

    public void setCountries(List<Area> countries) {
        this.countries = countries;
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
