package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Api.UsersServiceImpl;
import com.example.myapplication.R;
import com.example.myapplication.UserListApiAdapter;
import com.example.myapplication.databinding.FragmentListApiBinding;


public class ListApiFragment extends Fragment {

    private FragmentListApiBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListApiBinding.inflate(inflater, container, false);

//        binding.fabAddUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_dest_list_api_to_detailsFragment);
//            }
//        });

        return binding.getRoot();
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showUserList();
//        Toast.makeText(getContext(), "called", Toast.LENGTH_SHORT).show();
    }

    private void showUserList() {
        UsersServiceImpl service = new UsersServiceImpl();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        List<UserListApiModel>list =service.request();
        service.request(data -> {
            UserListApiAdapter adapter = new UserListApiAdapter(getContext(), data);
            binding.recyclerView.setAdapter(adapter);
//            if(adapter.infolist.isEmpty()){
//                Toast.makeText(getContext(), "empty "+data.size(), Toast.LENGTH_SHORT).show();
//            }
            Toast.makeText(getContext(), "method called "+data.size(), Toast.LENGTH_SHORT).show();
        });

//
    }
}