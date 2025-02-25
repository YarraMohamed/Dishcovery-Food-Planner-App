package com.example.foodplanner.favourites.view;

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
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.Test;

import java.util.ArrayList;
import java.util.List;

public class FavouritesListAdapter extends RecyclerView.Adapter<FavouritesListAdapter.ViewHolder> {
    private Context context;
    private List<FavMeals> favs;
    private RemoveClickListener removeClickListener;

    public FavouritesListAdapter(Context context,RemoveClickListener removeClickListener){
        this.context=context;
        this.favs = new ArrayList<>();
        this.removeClickListener = removeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.fav_meal,recycleView,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavMeals favMeal = favs.get(position);
        holder.favTitle.setText(favMeal.getMealName());
        holder.countryTxt.setText(favMeal.getMealCountry());
        holder.categoryTxt.setText(favMeal.getMealCategory());
        Glide.with(context).load(favMeal.getMealThumb())
                .apply(new RequestOptions().override(150,150))
                .error(R.drawable.error_photo)
                .into(holder.favImg);

        holder.deleteAction.setImageResource(R.drawable.delete_logo);
        holder.deleteAction.setOnClickListener(v -> {
            removeClickListener.onRemoveClick(favMeal);
        });
    }

    public List<FavMeals> getFavs() {
        return favs;
    }

    public void setFavs(List<FavMeals> favs) {
        this.favs = favs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView favImg;
        ImageView deleteAction;
        TextView favTitle,categoryTxt,countryTxt;
        View layout;
        ConstraintLayout fav_item;
        public ViewHolder(@NonNull View v) {
            super(v);
            layout=v;
            fav_item = v.findViewById(R.id.fav_item);
            favImg = v.findViewById(R.id.favImg);
            deleteAction = v.findViewById(R.id.deletAction);
            favTitle = v.findViewById(R.id.favTitle);
            categoryTxt = v.findViewById(R.id.categoryTxt);
            countryTxt = v.findViewById(R.id.countryTxt);
        }
    }
}
