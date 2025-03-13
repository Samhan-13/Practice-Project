package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Api.DetailsServiceImpl;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailsBinding;


public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;
    Long id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id = getArguments().getLong("id");
//        binding.tvId.setText(id.toString());

        DetailsServiceImpl service = new DetailsServiceImpl();

        service.request(id, data -> {
            binding.tvId.setText(id.toString());
            binding.tvEmail.setText(data.getEmail());
            binding.tvName.setText(data.getName());

            Log.d("asdName", "name: "+data.getName()+" address: "+data.getEmail());
        });

//        service.request(id);

    }
}