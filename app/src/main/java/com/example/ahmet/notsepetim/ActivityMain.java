package com.example.ahmet.notsepetim;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ahmet.notsepetim.adapter.AdapterNotlarListesi;
import com.example.ahmet.notsepetim.data.Notes;
import com.example.ahmet.notsepetim.data.NotlarProvider;

import java.util.ArrayList;


public class ActivityMain extends AppCompatActivity {

    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;

    Toolbar mToolbar;
    Button button;

    RecyclerView recyclerView;
    AdapterNotlarListesi adapterNotlarListesi;

    ArrayList<Notes> notesList = new ArrayList<Notes>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        backgroundres();

        refreshRecyclerView();

        button=findViewById(R.id.btn_sepete_not_ekle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkleDialogGöster();
            }
        });

    }

    private ArrayList<Notes> getNotesList(){
        Cursor cursor =getContentResolver().query(CONTENT_URI,new String[]{"id","notIcerik"},null,null,null );

        if(cursor != null){
            while (cursor.moveToNext()){
                Notes temporaryNotes = new Notes();
                temporaryNotes.setId(cursor.getInt(cursor.getColumnIndex("id")));
                temporaryNotes.setNotedescription(cursor.getString(cursor.getColumnIndex("notIcerik")));
                Log.i("NERDE BUNLAR", temporaryNotes.getNotedescription());
                notesList.add(temporaryNotes);
                Log.i("NERDE BUNLAR",notesList.get(0).getNotedescription());


            }
            /*for (int i=0;i<cursor.getCount();i++){
                Notes temporaryNotes = new Notes();
                cursor.moveToPosition(i);
                temporaryNotes.setId(cursor.getInt(cursor.getColumnIndex("id")));
                temporaryNotes.setNotedescription(cursor.getString(cursor.getColumnIndex("notIcerik")));
                notesList.add(i,temporaryNotes);
                Log.i("NERDE BUNLAR",notesList.get(0).getNotedescription());
            }*/
            cursor.moveToFirst();
            cursor.close();
        }
        return notesList;
    }

    private void notEkleDialogGöster() {
        FragmentDialogYeniNot dialogYeniNot = new FragmentDialogYeniNot();
        dialogYeniNot.show(getSupportFragmentManager(),"DialogYeniNot");
    }

    private void backgroundres() {
        ImageView imageView =(ImageView) findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.main_background)
                .into(imageView);

    }
    public void refreshRecyclerView(){
        notesList.clear();
        notesList = getNotesList();
        recyclerView = findViewById(R.id.rv_not_listesi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapterNotlarListesi = new AdapterNotlarListesi(this,notesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNotlarListesi);


    }

}