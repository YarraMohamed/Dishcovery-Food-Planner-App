package com.example.foodplanner.meal.view;

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
import com.example.foodplanner.model.IngredientDetails;
import com.example.foodplanner.model.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdpater extends RecyclerView.Adapter<ItemsAdpater.ViewHolder>{
    private Context context;
    private List<IngredientDetails> ing;


    public ItemsAdpater(Context context){
        this.context=context;
        ing = new ArrayList<>();
    }

    public List<IngredientDetails> getIng() {
        return ing;
    }

    public void setIng(List<IngredientDetails> ing) {
        this.ing = ing;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        View v = inflater.inflate(R.layout.item_card,recycleView,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ItemSamllTitle.setText(ing.get(position).getName());
        holder.itemMeseaure.setText(ing.get(position).getMeasures());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" +ing.get(position).getName() + "-Small.png")
                .apply(new RequestOptions().optionalCircleCrop().override(80,80))
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return ing.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView ItemSamllTitle,itemMeseaure;
        View layout;
        ConstraintLayout itemView;
        public ViewHolder(@NonNull View v) {
            super(v);
            layout=v;
            itemImage = v.findViewById(R.id.itemImage);
            ItemSamllTitle = v.findViewById(R.id.ItemSamllTitle);
            itemMeseaure = v.findViewById(R.id.itemMeseaure);
        }
    }
}
