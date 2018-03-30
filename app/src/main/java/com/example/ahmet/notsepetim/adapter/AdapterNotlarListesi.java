package com.example.ahmet.notsepetim.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmet.notsepetim.R;
import com.example.ahmet.notsepetim.data.Notes;

import java.util.ArrayList;

/**
 * Created by ahmet on 29.03.2018.
 */

public class AdapterNotlarListesi extends RecyclerView.Adapter<AdapterNotlarListesi.NotHolder> {

    // LayoutInflater bize gelen tek_satır view javaya döndürmek için lazım ve context istiyor burada construct kullanarak activiyde kullanıldığında
    // construct kullanarak activiyde kullanıldığında context i göndermesini istedik ama bu işlem burada NotHolder üzerinden de yapılabilirdi,
    // orada view üzerinden gelen textview imageview vs tanımlarken bir context nesnesi tanımlayarak ve view cağrıldığı contexti getcontext ile ,
    // ona atayarak daha sonrada bunu onCreateViewHolder da çağırarakta yapabilirdik
    LayoutInflater layoutInflater;
    ArrayList<Notes> notesList;

    public AdapterNotlarListesi (Context context, ArrayList<Notes> notes){
        layoutInflater = LayoutInflater.from(context);
        notesList=notes;
    }

    @Override
    public void onBindViewHolder(NotHolder holder, int position) {

        holder.mTextNote.setText(notesList.get(position).getNotedescription());
        holder.mTextDate.setText(""+notesList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    @Override
    public NotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Tek satırı java koduna dönüştürüyoruz.
        View view = layoutInflater.inflate(R.layout.tek_satir_not,parent,false);
        // daha sonra bu nu NotHolder a veriyoruzki değişkenlerini tanımlayabilsin ve bunlarda onBindViewHolder da doldurabilirsin
        NotHolder notHolder = new NotHolder(view);
        return notHolder;
    }
    public static class NotHolder extends RecyclerView.ViewHolder{

        private TextView mTextNote;
        private TextView mTextDate;

        public NotHolder(View itemView) {
            super(itemView);

            mTextNote = (TextView) itemView.findViewById(R.id.et_not_icerik);
            mTextDate = (TextView) itemView.findViewById(R.id.et_not_tarih);

        }
    }
}
