package com.example.myapplication.ListFromDb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.R;
import com.example.myapplication.UserModel;
import com.example.myapplication.databinding.ListItemFromDbBinding;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<UserModel> list;


    public UserRecyclerViewAdapter(Context context, List<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        ListItemFromDbBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_from_db, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserModel userModel = list.get(position);
        holder.binding.setUserModel(userModel);



//        Picasso.get()
//                .load(new File(list.get(position).getFilePath()))
//                .into(holder.ivUser);

        Glide.with(context)
                .load(list.get(position).getFilePath())
                .into(holder.binding.ivUser);

        holder.itemView.setOnLongClickListener(v-> {
            showDialog(userModel.getId());
                return true;
        });
    }

    private void showDialog(long id) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase db =AppDatabase.getInstance(context);
                        db.userDao().deleteUser(id);
                        int pos = findPositionInList(id);
                        if(pos!=-1){
                            list.remove(pos);
                            notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private int findPositionInList(long id) {
        int position = -1;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId() == id){
                position = i;
                break;
            }
        }

        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ListItemFromDbBinding binding;
        public ViewHolder(@NonNull ListItemFromDbBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
