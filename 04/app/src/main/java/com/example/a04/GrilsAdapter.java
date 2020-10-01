package com.example.a04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GrilsAdapter extends RecyclerView.Adapter<GrilsAdapter.ViewHolder> {
  private List<Grils> grilsList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grilitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    public GrilsAdapter(List<Grils> grilsList) {
        this.grilsList = grilsList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grils grils=grilsList.get(position);
        holder.imageView.setImageResource(grils.getImageId());
        holder.textView.setText(grils.getName());


    }

    @Override
    public int getItemCount() {
        return grilsList.size();
    }

    static class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image1);
            textView=(TextView)itemView.findViewById(R.id.textview1);
        }
    }
}
