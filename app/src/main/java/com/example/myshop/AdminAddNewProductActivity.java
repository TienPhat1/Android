package com.example.myshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

public class AdminAddNewProductActivity extends AppCompatActivity {
    private String Category;
    private ImageView inputProductImage;
    private Uri ImageUri;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        inputProductImage = (ImageView) findViewById(R.id.p_image);

        inputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("/image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(galleryIntent,"Select picture"),SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            inputProductImage.setImageURI(ImageUri);
        }
    }
}