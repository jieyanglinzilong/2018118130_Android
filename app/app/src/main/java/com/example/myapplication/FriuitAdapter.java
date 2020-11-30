package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriuitAdapter  extends RecyclerView.Adapter<FriuitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> myfruitList;

    public FriuitAdapter(List<Fruit> fruitList) {
        myfruitList=fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=myfruitList.get(position);
        holder.textView.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return myfruitList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            imageView=itemView.findViewById(R.id.fruit_image);
            textView=itemView.findViewById(R.id.fruit_name);

        }
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Fruit> getMyfruitList() {
        return myfruitList;
    }
}
