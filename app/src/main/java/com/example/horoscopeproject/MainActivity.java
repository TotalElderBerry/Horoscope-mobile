package com.example.horoscopeproject;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable, ItemInterface {

     ArrayList<Item> list = new ArrayList<Item>();
     ListView listView;
     EditText searchText;
     ListAdapter adapter;
     Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        searchText = findViewById(R.id.searhTextField);
        btnAdd = findViewById(R.id.addBtn);
        adapter = new ListAdapter(list,this, this);

        ArrayList<Item> temp = getIntent().getParcelableArrayListExtra("items");
        if(temp != null){
            for(Item t : temp){
                Log.e("item",t.getDescription());
                adapter.setLists(temp);
                adapter.notifyDataSetChanged();
            }
        }

        list.add(new Item(R.drawable.aquarius,"Aquarius"));
        list.add(new Item(R.drawable.aries,"Aries"));
        list.add(new Item(R.drawable.gemini,"Gemini"));
        list.add(new Item(R.drawable.taurus,"Taurus"));
        list.add(new Item(R.drawable.leo,"Leo"));
        list.add(new Item(R.drawable.pisces,"Pisces"));
        list.add(new Item(R.drawable.libra,"Libra"));
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                list.add(new Item(R.drawable.libra,"test"));
//                adapter.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                intent.putExtra("list", adapter.getLists());
                startActivity(intent);
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchText.getText().toString().toLowerCase();
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(Item value) {
        list.add(value);
        adapter.notifyDataSetChanged();
        Log.d("v",value.getDescription());
    }

}