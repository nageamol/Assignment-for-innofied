package com.abapp.my_work_for_innofied.view.activity;

import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abapp.my_work_for_innofied.R;
import com.abapp.my_work_for_innofied.base.BaseActivity;
import com.abapp.my_work_for_innofied.databinding.ActivityMainBinding;
import com.abapp.my_work_for_innofied.view.fragments.MainFragment;

import java.util.Arrays;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView listView;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;
    private String[] screenTitles;
    private Drawable[] screenIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTablet(this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setup();
    }

    private void setup() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance());
        transaction.commit();
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.container, fragment).addToBackStack(fragment.getClass().getName());
        tx.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void popUpFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public Fragment peek() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    public <T extends Fragment> boolean isFragmentAlreadyAdded(@NonNull Class<T> fragmentClass) {
        Fragment top = peek();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(
                fragmentClass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (Exception e) {
            }
        }
        return top != null && fragment != null && !(top instanceof MainFragment) && !(top.getClass().getCanonicalName().equalsIgnoreCase(fragment.getClass().getCanonicalName())) && top.getClass().getCanonicalName().equalsIgnoreCase(fragment.getClass().getCanonicalName());
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

}