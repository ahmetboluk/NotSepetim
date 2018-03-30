package com.example.ahmet.notsepetim;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ahmet.notsepetim.data.NotlarProvider;

/**
 * Created by ahmet on 26.03.2018.
 */

public class FragmentDialogYeniNot extends android.support.v4.app.DialogFragment{

    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;

    private ImageButton mbtnKapat;
    private Button mbtnNotEkle;
    private EditText mNotIcerik;
    private DatePicker mNotTarih;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_yeni_not,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbtnKapat=view.findViewById(R.id.btn_dialog_kapat);
        mbtnNotEkle=view.findViewById(R.id.btn_not_ekle);
        mNotIcerik=view.findViewById(R.id.et_not);
        mNotTarih=view.findViewById(R.id.dp_tarih);

        mbtnNotEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("notIcerik",mNotIcerik.getText().toString());
                values.put("notTarih",mNotIcerik.getText().toString());
                Uri uri =getActivity().getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(getContext(), ""+uri, Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        mbtnKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
