package com.abapp.my_work_for_innofied.di.module;



import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {
    @SuppressWarnings("unused")
    Class<? extends ViewModel> value();
}