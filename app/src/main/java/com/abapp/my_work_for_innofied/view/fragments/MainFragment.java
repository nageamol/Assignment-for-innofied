package com.abapp.my_work_for_innofied.view.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.abapp.my_work_for_innofied.R;
import com.abapp.my_work_for_innofied.adapter.RecyclerAdapter;
import com.abapp.my_work_for_innofied.base.BaseFragment;
import com.abapp.my_work_for_innofied.databinding.MainFragmentBinding;
import com.abapp.my_work_for_innofied.interfaces.PaginationListener;
import com.abapp.my_work_for_innofied.network.Status;
import com.abapp.my_work_for_innofied.view.activity.MainActivity;
import com.abapp.my_work_for_innofied.viewmodels.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.abapp.my_work_for_innofied.interfaces.PaginationListener.PAGE_START;

public class MainFragment extends BaseFragment<MainViewModel, MainFragmentBinding> implements MainViewModel.ViewModelListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerAdapter adaper;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    MainActivity mainActivity;
    int pageSize=5;
    private LinearLayoutManager layoutManager;
    private boolean isSwipe;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main_fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setViewModel(viewModel);
        viewModel.viewModelListener=this;
        layoutManager=new LinearLayoutManager(getActivity());
        binding.swipeView.setOnRefreshListener(this);
        adaper = new RecyclerAdapter(new ArrayList<>(), getActivity());
        binding.recyclerMain.setLayoutManager(layoutManager);
        binding.recyclerMain.setAdapter(adaper);
        getData();
        setPagination();
    }

    private void getData() {
        onStarted();
        if (!isNetworkAvailable()){
            showSnackBar(getActivity().getString(R.string.networkError));
            binding.layoutError.setVisibility(View.VISIBLE);
            onComplete();
            return;
        }
        viewModel.observeDataFromVM(currentPage,pageSize).observe(getViewLifecycleOwner(),data->{
            binding.swipeView.setRefreshing(false);
            if (data.status== Status.SUCCESS){
                binding.layoutError.setVisibility(View.GONE);
                onComplete();
                adaper.setmDataset(data.data.getData(),isSwipe);
                isSwipe=false;
            }else {
                binding.layoutError.setVisibility(View.VISIBLE);
                onComplete();
                showSnackBar(getActivity().getString(R.string.networkError));
            }
        });
    }

    private void setPagination() {

        binding.recyclerMain.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                getData();
            }
            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void onStarted() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRetry:
                getData();
                break;
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onComplete() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(mainActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(binding.getRoot(), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onRefresh() {
        isSwipe=true;
        isLoading=false;
        currentPage=1;
        getData();
        binding.swipeView.setRefreshing(true);
    }
}