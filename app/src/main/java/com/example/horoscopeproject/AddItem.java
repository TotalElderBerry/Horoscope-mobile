package com.example.horoscopeproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddItem extends AppCompatActivity {
    ImageView img;
    static final int PICK_IMAGE = 1, SELECT_PICTURE = 200;
    ArrayList<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_form);
        img = findViewById(R.id.listImage);

        img.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                    }
                }
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == PICK_IMAGE){
                Uri selectedImageUri = data.getData();
                Log.e("image",selectedImageUri.toString());
                if(selectedImageUri != null){
                    img.setImageURI(selectedImageUri);
                }
            }
        }
    }
}
