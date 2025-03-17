package com.example.myapplication.ListFromApi;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ListFromDb.UserRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.UserListApiModel;
import com.example.myapplication.databinding.ListItemFromApiBinding;
import com.example.myapplication.databinding.ListItemFromDbBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListApiAdapter extends RecyclerView.Adapter<UserListApiAdapter.ViewHolder> {

    private Context context;
    private List<UserListApiModel> infolist = new ArrayList<>();

    public UserListApiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemFromApiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_from_api, parent, false);

        return new UserListApiAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserListApiModel userListApiModel = infolist.get(position);

        if(infolist.get(position).getMaidenName()!=null && !infolist.get(position).getMaidenName().isEmpty()){
            holder.binding.tvMaidenName.setVisibility(VISIBLE);
        }else{
            holder.binding.tvMaidenName.setVisibility(GONE);
        }

        holder.binding.setUserListApiModel(userListApiModel);
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

        ListItemFromApiBinding binding;
        public ViewHolder(@NonNull ListItemFromApiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setList(List<UserListApiModel> infolist) {
        this.infolist = infolist;

        if (this.infolist == null) {
            this.infolist = new ArrayList<>();
        }

        notifyDataSetChanged();
    }
}
