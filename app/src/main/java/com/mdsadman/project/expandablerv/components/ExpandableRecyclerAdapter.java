package com.mdsadman.project.expandablerv.components;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

public class ExpandableRecyclerAdapter<T, B extends ViewBinding>
        extends RecyclerView.Adapter<ExpandableRecyclerViewHolder<B>>
{
    private ICreateViewHolder<B> iCreateViewHolder;
    private IBindViewHolder<T, B> iBindViewHolder;
    private IGetItemCount iGetItemCount;
    private final List<T> data;
    private final Context context;

    private final String ERROR_MESSAGE_ACTION = "The action for '%s' must be initialized";

    public ExpandableRecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;

        this.iGetItemCount = null;
    }

    public ExpandableRecyclerAdapter<T, B> setActionOnCreateViewHolder(ICreateViewHolder<B> iCreateViewHolder) {
        this.iCreateViewHolder = iCreateViewHolder;
        return this;
    }

    public ExpandableRecyclerAdapter<T, B> setActionOnBindViewHolder(IBindViewHolder<T, B> iBindViewHolder) {
        this.iBindViewHolder = iBindViewHolder;
        return this;
    }

    public ExpandableRecyclerAdapter<T, B> setActionGetItemCount(IGetItemCount iGetItemCount) {
        this.iGetItemCount = iGetItemCount;
        return this;
    }

    @NonNull
    @Override
    public ExpandableRecyclerViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (iCreateViewHolder == null)
            throw new RuntimeException(String.format(ERROR_MESSAGE_ACTION, "onCreateViewHolder"));

        B fragment = iCreateViewHolder.call(context);
        return new ExpandableRecyclerViewHolder<>(fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpandableRecyclerViewHolder<B> holder, int position) {
        if (iBindViewHolder == null)
            throw new RuntimeException(String.format(ERROR_MESSAGE_ACTION, "onBindViewHolder"));

        iBindViewHolder.call(context, holder, position, data);
    }

    @Override
    public int getItemCount() {
        if (iGetItemCount == null) return data.size();
        return iGetItemCount.call();
    }
}
