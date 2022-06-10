package com.largeproject.figmadesigns.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.largeproject.figmadesigns.api.RetrofitBuilder;
import com.largeproject.figmadesigns.api.Api;
import com.largeproject.figmadesigns.model.CoinModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class BaseViewModel extends ViewModel {
    Api api;
    private final MutableLiveData<List<CoinModel>> liveDataForCoinList = new MutableLiveData<>();

    public MutableLiveData<List<CoinModel>> getLiveData() {
        return liveDataForCoinList;
    }

    public void getCoins(String coins) {
        api = RetrofitBuilder.getInstance().getAPI();
        try {
            Response<List<CoinModel>> response = api.coinList(coins).execute();
            if (response.isSuccessful() && response.code() == 200) {
                if (liveDataForCoinList.getValue() != null && liveDataForCoinList.getValue().size() > 0) {
                    liveDataForCoinList.postValue(null);
                }
                liveDataForCoinList.postValue(response.body());
            } else {
                liveDataForCoinList.postValue(null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
