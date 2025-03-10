package com.example.myapplication.Fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UserListApiModel;

import java.util.ArrayList;
import java.util.List;

public class UserListApiAdapter extends RecyclerView.Adapter<UserListApiAdapter.ViewHolder> {

    private Context context;
    List<UserListApiModel> infolist;

    public UserListApiAdapter(Context context, List<UserListApiModel> infolist) {
        this.context = context;
        this.infolist = infolist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(infolist!= null){
            if(holder.tvName==null){
                Log.d("asdnullCheck"," tvname null");
            }
            if(infolist.get(position).getName()==null)
            {
                Log.d("asdnullCheck","infolist getname tvname null");
            }


            if(infolist.get(position).getDesignation()==null)
            {
                Log.d("asdnullCheck","infolist getDesignation tvname null");
            }


            if(infolist.get(position).getMobileNumber()==null)
            {
                Log.d("asdnullCheck","infolist getMobileNumber tvname null");
            }
            holder.tvName.setText(infolist.get(position).getName());
            holder.tvDesignation.setText(infolist.get(position).getDesignation());
            holder.tvMobileNumber.setText(infolist.get(position).getMobileNumber());
        }

    }

    @Override
    public int getItemCount() {
        return infolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvDesignation, tvMobileNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
            tvMobileNumber = itemView.findViewById(R.id.tvMobileNumber);
        }
    }
}
