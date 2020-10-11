package com.example.a04;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolders> {
    static class ViewHolders extends RecyclerView.ViewHolder {

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public WechatAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WechatAdapter.ViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
