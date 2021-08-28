package com.abapp.my_work_for_innofied.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abapp.my_work_for_innofied.R;
import com.abapp.my_work_for_innofied.databinding.RowRecyclerBinding;
import com.abapp.my_work_for_innofied.model.Data;
import com.abapp.my_work_for_innofied.model.RecylerModel;
import com.bumptech.glide.Glide;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {


    public RecyclerAdapter(List<Data> urls, Context context) {
        this.mDataset = urls;
        this.context = context;

        setHasStableIds(true);
        this.RecyclerAdapter = RecyclerAdapter;
    }


    List<Data> mDataset;
    RecyclerAdapter RecyclerAdapter;

    Context context;


    public void setmDataset(List<Data> mDataset,boolean isSwipe) {
        if (!isSwipe)
        this.mDataset.addAll(mDataset);
        else {
            this.mDataset.clear();
            this.mDataset.addAll(mDataset);
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RowRecyclerBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_recycler, viewGroup, false);
        return new CustomViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder customViewHolder, final int i) {
        customViewHolder.binding.txtName.setText(mDataset.get(i).getFirstName()+" "+mDataset.get(i).getLastName());
        customViewHolder.binding.txtEmail.setText(mDataset.get(i).getEmail());
        Glide.with(context)
                .load(mDataset.get(i).getAvatar())
                .centerCrop().circleCrop()
                .placeholder(R.drawable.loading)
                .into(customViewHolder.binding.imgAvtar);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }




    class CustomViewHolder extends RecyclerView.ViewHolder {
        RowRecyclerBinding binding;

        public CustomViewHolder(@NonNull View itemView, RowRecyclerBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
