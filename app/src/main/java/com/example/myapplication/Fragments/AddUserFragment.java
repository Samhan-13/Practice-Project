package com.example.myapplication.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddUserBinding;

import java.util.ArrayList;
import java.util.List;

public class AddUserFragment extends Fragment {
    FragmentAddUserBinding binding;
    private AddUserViewModel viewModel ;


    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddUserViewModel.class);


        binding.ivUploadUserImage.setOnClickListener(v -> {
            openGallery();
        });

//        binding.btnCamera.setOnClickListener(v -> {
//            openCamera();
//        });


        binding.btnSubmit.setOnClickListener(v -> {
            viewModel.setName(binding.etName.getText().toString());
            viewModel.setMobileNumber(binding.etMobileNumber.getText().toString());

            AppDatabase db = AppDatabase.getInstance(getContext());
            viewModel.setList(db.userDao().getAllUser());
            if (!viewModel.getList().isEmpty()) {
                viewModel.setCode((viewModel.getList().get(viewModel.getList().size() - 1).getCode()) + 1);
            } else {
                viewModel.setCode(1);
            }
//            db.userDao().insertUser(new User(name, mobileNumber, code));
            Log.d("asdUserData", "name: " + viewModel.getName() + " number: " + viewModel.getMobileNumber() + " code: " + viewModel.getCode());

            User user = new User();
            user.setName(viewModel.getName());
            user.setMobileNumber(viewModel.getMobileNumber());
            user.setCode(viewModel.getCode());
            user.setFilePath(viewModel.getFilePath());

            db.userDao().insertUser(user);

            binding.etName.getText().clear();
            binding.etMobileNumber.getText().clear();
//            binding.ivUploadUserImage.setImageResource(R.drawable.add_image);

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addUserFragment_to_userListFromDbFragment);

        });
    }

//    private void openCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_REQUEST);
//    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("asdAdd","onActivityResult");

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Log.d("asdAdd","result okay");
            Uri imageUri = data.getData();
            viewModel.setFilePath(getPath(imageUri));
            if(imageUri!=null){
                binding.ivUploadUserImage.setImageURI(imageUri);
            }
            else{
                Log.d("asdAdd","null");
            }

        }
        else{
            Log.d("asdAdd","result not okay");
        }

//        if (requestCode == CAMERA_REQUEST  && resultCode  == RESULT_OK) {
//            Uri imageUri = data.getData();
//            filePath = getPath(imageUri);
//            binding.ivUploadUserImage.setImageURI(imageUri);
//        }


    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }
}