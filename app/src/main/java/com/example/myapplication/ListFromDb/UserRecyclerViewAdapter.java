package com.example.myapplication.ListFromDb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.R;

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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_from_db, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvCode.setText(Integer.toString(list.get(position).getCode()));
        holder.tvMobileNumber.setText(list.get(position).getMobileNumber());

        UserModel userModel = list.get(position);



        holder.itemView.setOnLongClickListener(v-> {
//                AppDatabase db =AppDatabase.getInstance(context);
//                db.userDao().deleteUser(userModel.getId());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCode, tvMobileNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvMobileNumber = itemView.findViewById(R.id.tvMobileNumber);
        }
    }
}
