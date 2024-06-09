package com.mdsadman.project.expandablerv.components;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class ExpandableRecyclerViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {

    private final T fragment;

    public ExpandableRecyclerViewHolder(@NonNull T fragment) {
        super(fragment.getRoot());
        this.fragment = fragment;
    }

    public T getFragment() {
        return fragment;
    }
}