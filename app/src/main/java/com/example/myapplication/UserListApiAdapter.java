package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

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
            holder.tvName.setText(infolist.get(position).getName());
            holder.tvDesignation.setText(infolist.get(position).getDesignation());
            holder.tvMobileNumber.setText(infolist.get(position).getMobileNumber());


            if(infolist.get(position).getMaidenName()!=null && !infolist.get(position).getMaidenName().isEmpty()){
                holder.tvMaidenName.setText("Maiden Name: "+infolist.get(position).getMaidenName());
                holder.tvMaidenName.setVisibility(VISIBLE);

            }else{
                holder.tvMaidenName.setVisibility(GONE);
            }

//            holder.tvMaidenName.setVisibility(GONE);
        }

        UserListApiModel info = infolist.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", info.getId());

                Navigation.findNavController(v).navigate(R.id.action_dest_list_api_to_detailsFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return infolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvDesignation, tvMobileNumber, tvMaidenName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
            tvMobileNumber = itemView.findViewById(R.id.tvMobileNumber);
            tvMaidenName = itemView.findViewById(R.id.tvMaidenName);
        }
    }
}
