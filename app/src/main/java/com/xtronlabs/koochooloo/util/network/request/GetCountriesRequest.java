package com.xtronlabs.koochooloo.util.network.request;


import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCountriesRequest extends AbstractRequest implements Callback<Country[]> {

    ProcessResponseInterface<Country[]> mProcessProcessResponseInterface;

    @SuppressWarnings("unchecked")
    public GetCountriesRequest(Context mContext, ProcessResponseInterface<Country[]> responseHandler) {
        super(mContext);
        mProcessProcessResponseInterface = responseHandler;
        Call<Country[]> getCountriesCall = mNetworkInterface.getCountries();
        getCountriesCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Country[]> call, Response<Country[]> response) {
        Country[] countries = response.body();
        mProcessProcessResponseInterface.processResponse(countries);
    }

    @Override
    public void onFailure(Call<Country[]> call, Throwable t) {
        mProcessProcessResponseInterface.processResponse(null);
    }
}
