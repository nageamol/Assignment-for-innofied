package com.abapp.my_work_for_innofied.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by Amol Nage 17,October,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */
public abstract class BaseDialogFragment< D extends ViewDataBinding> extends DialogFragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;



    protected D binding;



    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return binding.getRoot();
    }



}
