package com.example.a04;

import android.annotation.SuppressLint;
import android.icu.util.ValueIterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolders> {
    List<Msg> msgList=new ArrayList<Msg>();

    public WechatAdapter(List<Msg> msgList) {
        this.msgList=msgList;
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightlayout;
        TextView left_msg;
        TextView right_msg;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            leftLayout=(LinearLayout)itemView.findViewById(R.id.left);
            rightlayout=(LinearLayout)itemView.findViewById(R.id.right);
            left_msg=(TextView)itemView.findViewById(R.id.leftmsg);
            right_msg=(TextView)itemView.findViewById(R.id.rightmsg);

        }
    }
    @NonNull
    @Override
    public WechatAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatitem,parent,false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WechatAdapter.ViewHolders holder, int position) {
        Msg msg=msgList.get(position);
        if(msg.getType()==Msg.Type_Received){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightlayout.setVisibility(View.GONE);
            holder.left_msg.setText(msg.getContent());
        }else if(msg.getType()==Msg.Type_send){
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.right_msg.setText(msg.getContent());

        }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
