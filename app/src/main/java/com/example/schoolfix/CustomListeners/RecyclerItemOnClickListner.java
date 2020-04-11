package com.example.schoolfix.CustomListeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemOnClickListner implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private  OnItemClickListener mListener;

    public RecyclerItemOnClickListner(Context context, final RecyclerView recyclerView, final OnItemClickListener mListener) {
        this.mListener = mListener;
        this.gestureDetector=new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (child != null && mListener != null) {
                    mListener.onItemLongClick(child, recyclerView.getChildAdapterPosition(child));
                }

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && mListener != null && gestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
