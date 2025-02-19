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

public class itemsAdpater extends RecyclerView.Adapter<itemsAdpater.ViewHolder>{
    private Context context;
    private List<Test> ing;


    public itemsAdpater(Context context,List<Test> ing){
        this.context=context;
        this.ing=ing;
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
        holder.itemImage.setImageResource(R.drawable.tomato);
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
