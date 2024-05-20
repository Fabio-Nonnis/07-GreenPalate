package it.unimib.greenpalate.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FoodFactsVewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;

    public FoodFactsVewModelFactory(Application application) {

        this.mApplication = application;
    }

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FoodFactsViewModel(mApplication);
    }
}