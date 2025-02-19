package com.example.foodplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouritesListAdapter extends RecyclerView.Adapter<FavouritesListAdapter.ViewHolder> {
    private Context context;
    private List<Test> favs;

    public FavouritesListAdapter(Context context,List<Test> favs){
        this.context=context;
        this.favs=favs;
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
        holder.deleteAction.setImageResource(R.drawable.delete_logo);
        holder.favImg.setImageResource(R.drawable.fried_salmon_steak_cooked_green_600nw_2489026949);
        holder.favTitle.setText(favs.get(position).getName());
        holder.categoryTxt.setText(favs.get(position).getCategory());
        holder.countryTxt.setText(favs.get(position).getCountry());
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
