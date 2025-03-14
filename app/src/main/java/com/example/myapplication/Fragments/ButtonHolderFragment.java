package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentButtonHolderBinding;

public class ButtonHolderFragment extends Fragment {

    FragmentButtonHolderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentButtonHolderBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnToUsersFromApi.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_buttonHolderFragment_to_dest_list_api);
        });

        binding.btnToUsersFromDB.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_buttonHolderFragment_to_userListFromDbFragment);
        });
    }
}