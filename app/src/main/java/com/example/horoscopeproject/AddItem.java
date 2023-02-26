package com.example.horoscopeproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddItem extends AppCompatActivity{
    ImageView img;
    static final int PICK_IMAGE = 1, SELECT_PICTURE = 200;
    ArrayList<Item> items;
    Button submitBtn;
    EditText editText;
    ItemInterface inter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_form);
        img = findViewById(R.id.listImage);
        submitBtn = findViewById(R.id.addButton);
        items = getIntent().getParcelableArrayListExtra("list");
        Log.e("item",items.get(items.size() - 1).getDescription());
        editText = findViewById(R.id.descriptionTxt);
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


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    Log.d("debug",editText.getText().toString());
//                Log.e("str",editText.getText().toString());
//                inter.onClick(new Item(R.drawable.aries,editText.getText().toString()));
                Item i = new Item(R.drawable.aries,editText.getText().toString());
                items.add(i);

                Intent intent = new Intent(AddItem.this, MainActivity.class);
                intent.putExtra("items", items);
                startActivity(intent);
            }
        });

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
