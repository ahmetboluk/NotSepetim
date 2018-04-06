package com.example.ahmet.notsepetim.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ahmet.notsepetim.R;
import com.example.ahmet.notsepetim.data.DataEvent;
import com.example.ahmet.notsepetim.data.Notes;
import com.example.ahmet.notsepetim.data.NotlarProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by ahmet on 29.03.2018.
 */

public class AdapterNotlarListesi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int ITEM = 0 ;
    static final int FOOTER = 1 ;
    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;
    // LayoutInflater bize gelen tek_satır view javaya döndürmek için lazım ve context istiyor burada construct kullanarak activiyde kullanıldığında
    // construct kullanarak activiyde kullanıldığında context i göndermesini istedik ama bu işlem burada NotHolder üzerinden de yapılabilirdi,
    // orada view üzerinden gelen textview imageview vs tanımlarken bir context nesnesi tanımlayarak ve view cağrıldığı contexti getcontext ile ,
    // ona atayarak daha sonrada bunu onCreateViewHolder da çağırarakta yapabilirdik
    LayoutInflater layoutInflater;
    ArrayList<Notes> notesList;
    ContentResolver contentResolver;


     public AdapterNotlarListesi (Context context, ArrayList<Notes> notes){
        contentResolver = context.getContentResolver();
        layoutInflater = LayoutInflater.from(context);
        notesList=notes;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NotHolder) {
            NotHolder notHolder = (NotHolder) holder;
            notHolder.mTextNote.setText(notesList.get(position).getNotedescription());
            notHolder.mTextDate.setText("" + notesList.get(position).getId());
        }
    }

    @Override
    public int getItemCount() {
        if (notesList.size() == 0 || notesList.isEmpty()){
            return 0;
        }else {
            return notesList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(notesList==null || notesList.size()==0){
            return ITEM;
        }
        else if(position<notesList.size()){
            return ITEM;
        }
        else {return FOOTER;}
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==ITEM) {
            // Tek satırı java koduna dönüştürüyoruz.
            View view = layoutInflater.inflate(R.layout.tek_satir_not, parent, false);
            // daha sonra bu nu NotHolder a veriyoruzki değişkenlerini tanımlayabilsin ve bunlarda onBindViewHolder da doldurabilirsin
            NotHolder notHolder = new NotHolder(view);
            return notHolder;
        }
        else if(viewType==FOOTER){
            View view = layoutInflater.inflate(R.layout.footer_button,parent,false);
            FooterHolder footerHolder = new FooterHolder(view);
            return footerHolder;
        }
        else return null; 
    }

    public void update(ArrayList<Notes> notesList) {
        this.notesList=notesList;
        notifyDataSetChanged();
    }


    public static class NotHolder extends RecyclerView.ViewHolder{

        private TextView mTextNote;
        private TextView mTextDate;

        public NotHolder(final View itemView) {
            super(itemView);

            mTextNote = (TextView) itemView.findViewById(R.id.et_not_icerik);
            mTextDate = (TextView) itemView.findViewById(R.id.et_not_tarih);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DataEvent.NoteCompleteShowFragment(getAdapterPosition()));
                }
            });

        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder{

        Button button;

        public FooterHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.btn_footer);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DataEvent.AddNoteDialogShow(getAdapterPosition()));
                }
            });

        }

    }

    @Subscribe
    public void onSwipe(DataEvent.SwipeFire event) {
         //bu posizyonu getirir
         int position = event.getFire();
         if (position<notesList.size()) {
            Notes deletenotes = notesList.get(position);

            int deletenotescount = contentResolver.delete(CONTENT_URI, "id=?", new String[]{String.valueOf(notesList.get(position).getId())});
            if (deletenotescount != 0) {
                notesList.remove(deletenotes);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }
}
