package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.ListFromDb.UserRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.UserModel;
import com.example.myapplication.databinding.FragmentUserListFromDbBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListFromDbFragment extends Fragment {
    private UserListFromDbViewModel viewModel;
    private List<UserModel> userList = new ArrayList<>();

    FragmentUserListFromDbBinding binding;
    private UserRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserListFromDbBinding.inflate(inflater, container, false);

        binding.fabAddUser.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userListFromDbFragment_to_addUserFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserListFromDbViewModel.class);
        showListFromDb();
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userListFromDbFragment_to_buttonHolderFragment);
                    }
                }
        );
    }

    private void showListFromDb() {
        AppDatabase db = AppDatabase.getInstance(getContext());

        if (!userList.isEmpty()) {
            userList.clear();
            adapter.notifyDataSetChanged();
        }

        viewModel.setUserListFromDb(db.userDao().getAllUser());

        if (!viewModel.getUserListFromDb().isEmpty()) {
            for (User user : viewModel.getUserListFromDb()) {
                if (user != null) {
                    userList.add(new UserModel(user.getName(), user.getMobileNumber(), user.getCode(), user.getId(), user.getFilePath()));
                }
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserRecyclerViewAdapter(getContext(), userList);
        binding.recyclerView.setAdapter(adapter);
    }
}