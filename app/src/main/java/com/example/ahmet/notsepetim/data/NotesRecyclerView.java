package com.example.ahmet.notsepetim.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotesRecyclerView extends RecyclerView{

    List<View> isEmptyInVisibleViews = Collections.emptyList();
    List<View> isEmptyVisibleViews = Collections.emptyList();

    public AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            viewVisibleOrInvisible();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            viewVisibleOrInvisible();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            viewVisibleOrInvisible();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            viewVisibleOrInvisible();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            viewVisibleOrInvisible();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            viewVisibleOrInvisible();
        }
    };

    private void viewVisibleOrInvisible() {
        if(getAdapter()!=null && !isEmptyInVisibleViews.isEmpty() && !isEmptyVisibleViews.isEmpty()){
            if(getAdapter().getItemCount()==0){
                for(View view :isEmptyInVisibleViews){
                    view.setVisibility(GONE);
                }
                setVisibility(View.GONE);
                for(View view : isEmptyVisibleViews){
                    view.setVisibility(VISIBLE);
                }
            }else {
                for(View view :isEmptyInVisibleViews){
                    view.setVisibility(VISIBLE);
                }
                setVisibility(View.VISIBLE);
                for(View view : isEmptyVisibleViews){
                    view.setVisibility(GONE);
                }


            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
         if(adapter != null){
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public NotesRecyclerView(Context context) {
        super(context);
    }

    public NotesRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void notesIsEmpty(View... inVisibleViews) {
        isEmptyInVisibleViews = Arrays.asList(inVisibleViews);
    }

    public void notesIsNotesList(View... visibleViews) {
        isEmptyVisibleViews = Arrays.asList(visibleViews);
    }
}
