package com.example.ahmet.notsepetim.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ahmet.notsepetim.R;

public class Divider extends RecyclerView.ItemDecoration{

    private Drawable mdivider;
    private int mOrientation;

    public Divider(Context context, int orientation){

        mdivider = ContextCompat.getDrawable(context, R.drawable.divider);
        if (orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("Bu dekorasyon burada kullanılamaz");
        }
        mOrientation  = orientation;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation==LinearLayoutManager.VERTICAL){
            drawHorizontalDivider(c, parent, state);
        }
    }

    public void drawHorizontalDivider(Canvas c ,RecyclerView parent, RecyclerView.State state){
        int left, top, right, bottom;
        left=parent.getPaddingLeft();
        right=parent.getWidth()-parent.getPaddingRight();
        int recyclerCount = parent.getChildCount();

        for (int i =0 ;i <recyclerCount; i++ ){

            if (AdapterNotlarListesi.FOOTER!=parent.getAdapter().getItemViewType(i)){

                View view = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
                top = view.getTop() - params.topMargin;

                bottom = top + mdivider.getIntrinsicHeight();

                mdivider.setBounds(left,top,right,bottom);
                mdivider.draw(c);}
        }


    }
}
