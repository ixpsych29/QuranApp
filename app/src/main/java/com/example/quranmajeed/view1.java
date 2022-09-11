package com.example.quranmajeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class view1 extends AppCompatActivity {
    ArrayList<String> displayData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        DBHelper db=new DBHelper(this);
        Intent intent = new Intent(view1.this, view2.class);

        if(getIntent().getStringExtra("type").equals("surah"))
        {
            displayData=db.getSurahList();
        }
        else if(getIntent().getStringExtra("type").equals("para"))
        {
            displayData=db.getParaList();
        }
        ListView view1ListView=findViewById(R.id.view1ListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, displayData);

        view1ListView.setAdapter(arrayAdapter);
        view1ListView.setOnItemClickListener((adapterView, view, i, l) -> {
            if(getIntent().getStringExtra("type").equals("surah"))
            {
                intent.putExtra("surahId",i);
            }
            else if(getIntent().getStringExtra("type").equals("para"))
            {
                intent.putExtra("paraId",i);
            }
            startActivity(intent);
        });

        EditText searchBar=findViewById(R.id.searchBar);

        searchBar.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
                ArrayList<String> rtn=new ArrayList<>();
                for(int i=0;i<displayData.size();i++)
                {
                    if (displayData.get(i).toLowerCase(Locale.ROOT).contains(mEdit.toString().toLowerCase(Locale.ROOT)))
                    {
                        rtn.add(displayData.get(i));
                    }
                }
                if(rtn.size()>0)
                {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view1.this, android.R.layout.simple_list_item_1, rtn);
                    view1ListView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }
}
