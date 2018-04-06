package com.example.ahmet.notsepetim.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.ahmet.notsepetim.data.DataEvent;

import org.greenrobot.eventbus.EventBus;

public class SimpleTouchCallback extends ItemTouchHelper.Callback{



    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType()==0){
            return makeMovementFlags(0,ItemTouchHelper.END);
        } else {
            return makeMovementFlags(0,0);
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //swipeListener.onSwipe(viewHolder.getAdapterPosition());
        EventBus.getDefault().post( new DataEvent.SwipeFire(viewHolder.getAdapterPosition()));
    }
}
