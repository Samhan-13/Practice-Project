package com.example.myapplication.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
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
    private int PICK_IMAGE_REQUEST = 1;
    private String filePath;
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

        binding.ivUploadUserImage.setOnClickListener(v ->{
            openGallery();
        } );

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
//            db.userDao().insertUser(new User(name, mobileNumber, code));
            Log.d("asdUserData", "name: " + name + " number: " + mobileNumber + " code: " + code);

            User user = new User();
            user.setName(name);
            user.setMobileNumber(mobileNumber);
            user.setCode(code);
            user.setFilePath(filePath);
            db.userDao().insertUser(user);

            binding.etName.getText().clear();
            binding.etMobileNumber.getText().clear();
            binding.ivUploadUserImage.setImageResource(R.drawable.add_image);

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addUserFragment_to_userListFromDbFragment);
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST  && resultCode  == RESULT_OK) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            binding.ivUploadUserImage.setImageURI(imageUri);
        }


    }


    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }


}