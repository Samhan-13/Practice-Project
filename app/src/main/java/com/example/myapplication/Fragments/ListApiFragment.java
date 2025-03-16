package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Api.UsersServiceImpl;
import com.example.myapplication.ListFromApi.UserListApiAdapter;
import com.example.myapplication.ListFromApi.UserListApiModel;
import com.example.myapplication.databinding.FragmentListApiBinding;

import java.util.List;


public class ListApiFragment extends Fragment {
    UserListApiAdapter adapter;
    ListApiViewModel viewModel;


    private FragmentListApiBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListApiBinding.inflate(inflater, container, false);
        return binding.getRoot();
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ListApiViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserListApiAdapter(getContext());
        binding.recyclerView.setAdapter(adapter);

        showUserList();
        subscribeUserList(viewModel.getList());

    }

    private void subscribeUserList(LiveData<List<UserListApiModel>> liveData) {
        liveData.observe(getViewLifecycleOwner(), this::updateList);
    }

    private void updateList(List<UserListApiModel> userListApiModels) {
        adapter.setList(userListApiModels);
    }

    private void showUserList() {
        UsersServiceImpl service = new UsersServiceImpl();

        service.request(data -> {
            viewModel.setList(data);
        });
    }
}