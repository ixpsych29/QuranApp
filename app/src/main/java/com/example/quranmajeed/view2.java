package com.example.qurandatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class view2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);

        Intent intent=getIntent();
        int surahId,paraId;
        DBHelper dbHelper=new DBHelper(this);
        ArrayList<String> data=new ArrayList<>();
        if(intent.hasExtra("surahId")){
            surahId=intent.getIntExtra("surahId",0)+1;
            data =dbHelper.getSurah(surahId);
        }
        else if (intent.hasExtra("paraId"))
        {
            paraId=intent.getIntExtra("paraId",0);
            data =dbHelper.getPara(paraId);
        }



        ListView view2ListView=findViewById(R.id.view2ListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, data);

        view2ListView.setAdapter(arrayAdapter);

    }
}