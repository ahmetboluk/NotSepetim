package com.example.ahmet.notsepetim;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ahmet.notsepetim.adapter.AdapterNotlarListesi;
import com.example.ahmet.notsepetim.adapter.Divider;
import com.example.ahmet.notsepetim.adapter.SimpleTouchCallback;
import com.example.ahmet.notsepetim.data.DataEvent;
import com.example.ahmet.notsepetim.data.FragmentDialogNoteComplete;
import com.example.ahmet.notsepetim.data.Notes;
import com.example.ahmet.notsepetim.data.NotesRecyclerView;
import com.example.ahmet.notsepetim.data.NotlarProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public class ActivityMain extends AppCompatActivity {

    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;

    View bosListe;
    Toolbar mToolbar;
    Button button;

    NotesRecyclerView recyclerView;
    AdapterNotlarListesi adapterNotlarListesi;

    ArrayList<Notes> notesList = new ArrayList<Notes>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bosListe= (View) findViewById(R.id.bos_liste);
        mToolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        backgroundres();

        recyclerView = (NotesRecyclerView) findViewById(R.id.rv_not_listesi);
        recyclerView.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL));
        recyclerView.notesIsEmpty(mToolbar);
        recyclerView.notesIsNotesList(bosListe);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapterNotlarListesi = new AdapterNotlarListesi(this,notesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNotlarListesi);

        refreshRecyclerView();

        SimpleTouchCallback simpleTouchCallback = new SimpleTouchCallback();
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchCallback);
        helper.attachToRecyclerView(recyclerView);

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

    private void backgroundres() {
        ImageView imageView =(ImageView) findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.main_background)
                .into(imageView);

    }

    public void refreshRecyclerView(){
        notesList.clear();
        notesList = getNotesList();
        adapterNotlarListesi.update(notesList);


    }

    private void notEkleDialogGöster() {
        FragmentDialogYeniNot dialogYeniNot = new FragmentDialogYeniNot();
        dialogYeniNot.show(getSupportFragmentManager(),"DialogYeniNot");
    }

    private void fragmentDialogNoteComleteShow(int position) {
        EventBus.getDefault().postSticky(new DataEvent.NoteCompleteShowPosition(position));
        FragmentDialogNoteComplete dialogshow = new FragmentDialogNoteComplete();
        dialogshow.show(getSupportFragmentManager(),"note Show");

    }

    @Subscribe
    public void onDialogNoteCompleteShow (DataEvent.NoteCompleteShowFragment event){

            fragmentDialogNoteComleteShow(event.getFire());

    }

    @Subscribe
    public void onAddNoteDialogShow(DataEvent.AddNoteDialogShow event){

            notEkleDialogGöster();

    }

    @Subscribe
    public void onNDataRefreshFire(DataEvent.DataRefreshFire event){
        if (event.getFire()==1){
            refreshRecyclerView();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}