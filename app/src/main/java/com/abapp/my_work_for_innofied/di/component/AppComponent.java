package com.abapp.my_work_for_innofied.di.component;

import android.app.Application;

import com.abapp.my_work_for_innofied.MyApplication;
import com.abapp.my_work_for_innofied.di.builder.ActivityBuilderModule;
import com.abapp.my_work_for_innofied.di.module.AppModule;
import com.abapp.my_work_for_innofied.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class, ViewModelModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
    void inject(MyApplication myApplication);
}