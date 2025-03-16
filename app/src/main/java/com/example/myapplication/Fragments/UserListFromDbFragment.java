package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.UserModel;
import com.example.myapplication.ListFromDb.UserRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentUserListFromDbBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListFromDbFragment extends Fragment {

    List<UserModel> userList = new ArrayList<>();
    List<User> userListFromDb = new ArrayList<>();
    FragmentUserListFromDbBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserListFromDbBinding.inflate(inflater, container, false);

        binding.fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userListFromDbFragment_to_addUserFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showListFromDb();

//        requireActivity().getOnBackPressedDispatcher().addCallback(
//                getViewLifecycleOwner(), new OnBackPressedCallback(true) {
//                    @Override
//                    public void handleOnBackPressed() {
////                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userListFromDbFragment_to_buttonHolderFragment);
//                    }
//                }
//        );
    }



    private void showListFromDb() {
        AppDatabase db= AppDatabase.getInstance(getContext());
        userListFromDb = db.userDao().getAllUser();

        if(!userListFromDb.isEmpty())
        {
            for (User user : userListFromDb) {
                if(user!=null)
                {
                    userList.add(new UserModel(user.getName(), user.getMobileNumber(), user.getCode(), user.getId(), user.getFilePath()));
                } else{
                    Log.d("asdDB","user null");
                }
            }
        }
        else{
            Log.d("asdDB","userlistfromdb null");
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(getContext(), userList);
        binding.recyclerView.setAdapter(adapter);
    }
}