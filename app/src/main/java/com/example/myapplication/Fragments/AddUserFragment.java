package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.Database.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddUserBinding;

import java.util.ArrayList;
import java.util.List;

public class AddUserFragment extends Fragment {
    private String name;
    private String mobileNumber;
    private  int code;
    private List<User> list = new ArrayList<>();
    FragmentAddUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.btnSubmit.setOnClickListener(v-> {
            name = binding.etName.getText().toString();
            mobileNumber = binding.etMobileNumber.getText().toString();

            AppDatabase db = AppDatabase.getInstance(getContext());
            list = db.userDao().getAllUser();
            if (!list.isEmpty()) {
                code = (list.get(list.size() - 1).getCode()) + 1;
            } else {
                code = 1;
            }
            db.userDao().insertUser(new User(name, mobileNumber, code));
            Log.d("asdUserData", "name: " + name + " number: " + mobileNumber + " code: " + code);

            binding.etName.getText().clear();
            binding.etMobileNumber.getText().clear();

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addUserFragment_to_userListFromDbFragment);
        });
    }
}