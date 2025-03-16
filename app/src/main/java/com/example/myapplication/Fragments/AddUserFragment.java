package com.example.myapplication.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddUserBinding;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddUserFragment extends Fragment {
    FragmentAddUserBinding binding;
    private AddUserViewModel viewModel;
    private int PICK_IMAGE_REQUEST = 1;
    private int CAMERA_REQUEST = 2;
    private Uri imageUri;

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

        binding.btnCamera.setOnClickListener(v -> {
            captureImage();
        });

        binding.btnSubmit.setOnClickListener(v -> {
            viewModel.setName(binding.etName.getText().toString());
            viewModel.setMobileNumber(binding.etMobileNumber.getText().toString());
            viewModel.addUser();
            binding.etName.getText().clear();
            binding.etMobileNumber.getText().clear();
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
        Log.d("asdAdd", "onActivityResult");

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Log.d("asdAdd", "result okay");
            imageUri = data.getData();
//            viewModel.setFilePath(getPath(imageUri));
            viewModel.setFilePath(imageUri.toString());

            if (imageUri != null) {
                binding.ivUploadUserImage.setImageURI(imageUri);
            } else {
                Log.d("asdAdd", "null");
            }
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }

    ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    if (imageUri != null) {
                        binding.ivUploadUserImage.setImageURI(imageUri);
                        viewModel.setFilePath(imageUri.toString());// Display Image in ImageView
                    } else {
                        Toast.makeText(getContext(), "Image URI is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private void captureImage() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 101);
            return;
        }

        File imageFile = createImageFile();
        if (imageFile != null) {
            imageUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".provider", imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraLauncher.launch(intent);
        }
    }

    // Function to Create Image File
    private File createImageFile() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "IMG_" + timeStamp + ".jpg";
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            return new File(storageDir, imageFileName);
        } catch (Exception e) {
            Log.e("FileCreationError", "Error creating image file", e);
            return null;
        }
    }
}