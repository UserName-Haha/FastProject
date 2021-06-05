package com.haha.fastproject.base.widget.countrypicker;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LetterHolder extends RecyclerView.ViewHolder {
    public final TextView textView;
    public LetterHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}
