package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Api.DetailsServiceImpl;
import com.example.myapplication.databinding.FragmentDetailsBinding;


public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;
    DetailsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        viewModel.setId(getArguments().getLong("id"));
        DetailsServiceImpl service = new DetailsServiceImpl();

        service.request(viewModel.getId(), data -> {
            binding.tvId.setText(viewModel.getId().toString());
            binding.tvEmail.setText(data.getEmail());
            binding.tvName.setText(data.getName());
        });

    }
}