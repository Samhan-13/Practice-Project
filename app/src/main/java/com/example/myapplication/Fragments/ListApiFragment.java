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
import android.widget.Toast;

import com.example.myapplication.Api.UserListResponse;
import com.example.myapplication.Api.UsersServiceImpl;
import com.example.myapplication.ListFromApi.UserListApiModel;
import com.example.myapplication.R;
import com.example.myapplication.ListFromApi.UserListApiAdapter;
import com.example.myapplication.databinding.FragmentListApiBinding;

import java.util.ArrayList;
import java.util.List;


public class ListApiFragment extends Fragment {

    private List<UserListApiModel> list = new ArrayList<>();
    UserListApiAdapter adapter;

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



        adapter = new UserListApiAdapter(getContext(), list);
        binding.recyclerView.setAdapter(adapter);

        showUserList();
    }

    private void showUserList() {
        UsersServiceImpl service = new UsersServiceImpl();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        service.request(data -> {
            list.addAll(data);
            Log.d("asdData", ""+list.get(0).getName());
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "method called "+data.size(), Toast.LENGTH_SHORT).show();
        });
    }
}