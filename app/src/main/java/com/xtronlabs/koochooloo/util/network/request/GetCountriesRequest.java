package com.xtronlabs.koochooloo.util.network.request;


import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Countries;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCountriesRequest extends AbstractRequest implements Callback<Countries> {

    ProcessResponseInterface<Countries> mProcessProcessResponseInterface;

    @SuppressWarnings("unchecked")
    public GetCountriesRequest(Context mContext, ProcessResponseInterface<Countries> responseHandler) {
        super(mContext);
        mProcessProcessResponseInterface = responseHandler;
        Call<Countries> getCountriesCall = mNetworkInterface.getCountries();
        getCountriesCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Countries> call, Response<Countries> response) {
        Countries countries = response.body();
        mProcessProcessResponseInterface.processResponse(countries);
    }

    @Override
    public void onFailure(Call<Countries> call, Throwable t) {
        mProcessProcessResponseInterface.processResponse(null);
    }
}
