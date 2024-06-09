package com.mdsadman.project.expandablerv.components;

import android.content.Context;

import androidx.viewbinding.ViewBinding;

import java.util.List;

public interface ICreateViewHolder<B extends ViewBinding> {

    B call(Context context);
}
