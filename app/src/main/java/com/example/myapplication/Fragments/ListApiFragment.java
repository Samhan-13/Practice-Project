package com.example.myapplication.Fragments;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
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

//    private List<UserListApiModel> list = new ArrayList<>();
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

        List<String> list1= null;

        List<String> list2 = list1;



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserListApiAdapter(getContext());
        binding.recyclerView.setAdapter(adapter);

        showUserList();
        subscribeUserList(viewModel.getList());

    }

    private void subscribeUserList(LiveData<List<UserListApiModel>>liveData) {
        liveData.observe(getViewLifecycleOwner(), this::updateList);

    }

    private void updateList(List<UserListApiModel> userListApiModels){
        adapter.setList(userListApiModels);
    }



    private void showUserList() {
        UsersServiceImpl service = new UsersServiceImpl();

        service.request(data -> {
//            viewModel.getList().getValue().addAll(data);
              viewModel.setList(data);
//            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "method called "+data.size(), Toast.LENGTH_SHORT).show();
        });
    }
}