package com.mdsadman.project.expandablerv.components;

import android.content.Context;

import androidx.viewbinding.ViewBinding;

import java.util.List;

public interface IBindViewHolder<T, B extends ViewBinding> {

    void call(Context context, ExpandableRecyclerViewHolder<B> holder, int position, List<T> data);
}
