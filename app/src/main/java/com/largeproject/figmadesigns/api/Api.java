package com.largeproject.figmadesigns.api;

import com.largeproject.figmadesigns.model.CoinModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("v2/coins")
    Call<List<CoinModel>> coinList(@Query("list") String list);
}
