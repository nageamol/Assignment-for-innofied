package com.abapp.my_work_for_innofied.base;


import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends PagedListAdapter<D, T> {

    protected BaseAdapter(@NonNull DiffUtil.ItemCallback<D> diffCallback) {
        super(diffCallback);
    }

    public abstract void setData(List<D> data);
}